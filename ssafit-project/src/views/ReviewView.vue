<template>
    <div>

        <p class="fw-bold fs-4 text-center" id="fontt"> ✏️ 운동영상 ✏️ </p>
        <h4 @click="toggleFavChan"> {{ videoStore.video.channelName }} 구독 상태 : {{ favStore.isFavChan }}</h4>
        <hr>
        <br>
        <div align="center">
            <iframe :src=reviewStore.selectedYoutube width="400px" height="300px"></iframe>
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
p {
    margin-top: 120px;
}

.form-floating {
    margin: 25px 35px 0px;
}

.btn-outline-primary {
    margin-left: 35px;
}</style>