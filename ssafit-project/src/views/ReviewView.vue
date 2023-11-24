<template>
    <div>

        <p class="fw-bold fs-4 text-center" id="fontt"> ✏️ 운동영상 ✏️ </p>
        <h4 @click="toggleFavChan"> {{ videoStore.video.channelName }} 구독 상태 : {{ favStore.isFavChan==true?'❤️':'🤍' }}</h4>
        <hr>
        <br>
        <div id="area" >
            <iframe id="video" :src=reviewStore.selectedYoutube ></iframe>
        </div>
        <hr>
        <RouterView />
    </div>
</template>

<script setup>
import { onBeforeMount, onMounted, onUpdated ,ref, computed, onUnmounted } from 'vue';
import { useReviewStore } from '../stores/review';
import { RouterLink } from 'vue-router'
import { useFavoriteStore } from '../stores/favorite';
import { useVideoStore } from '../stores/video';

const reviewStore = useReviewStore()
const videoStore = useVideoStore()
const favStore = useFavoriteStore()
const isFavorite = ref(false)


onMounted(() => {
    if (sessionStorage.getItem("videoID") !== reviewStore.videoID && reviewStore.videoID !== '') {
        sessionStorage.setItem("videoID", reviewStore.videoID);
    }
    if(reviewStore.videoID == ''){
        reviewStore.videoID = sessionStorage.getItem("videoID");
    }
    reviewStore.selectedYoutube = `https://www.youtube.com/embed/${sessionStorage.getItem("videoID")}`
    favStore.isFavChannel(sessionStorage.getItem("videoID"));
    videoStore.getVideo(sessionStorage.getItem("videoID"));
})

const toggleFavChan = () => {
    favStore.setFavChannel(videoStore.video.channelName, favStore.isFavChan)
}
</script>



<style scoped>
#area {
    position: relative; /* absolute는 부모가 relative일 때 부모를 따라간다. */
    width: 60%;
 
    padding-bottom: 33.8%; /* 16:9 비율 */
  }
  
  #video {margin-left: 34%;
    position: absolute;
    width: 100%; /* 부모에 맞게 꽉 채운다. */
    height: 100%;
  }
p {
    margin-top: 120px;
}

.form-floating {
    margin: 25px 35px 0px;
}

.btn-outline-primary {
    margin-left: 35px;
}</style>
