<template>
    <h3>{{ channelName }} 채널의 동영상 목록</h3>
    <div id="app">
        <div v-for="video in channelStore.videoList" class="card-deck">
            <div class="card">
                <div class="card-img-top" alt="...">
                    <iframe :src="`https://www.youtube.com/embed/${video.videoID}`" width="400px" height="300px"></iframe>
                </div>
                <div class="card-body">
                    <p class="card-title">{{ video.title }}</p>
                    <!-- <p> 채널명 : {{ video.channelName }}</p> -->
                    <p> 조회 수 : {{ video.viewCnt }}</p>
                    <p> 운동부위 : {{ video.partInfo }}</p>
                    <a href="#" class="btn btn-primary" @click="goToVideo(video.videoID)">보러 가기</a>
                    <!-- 
                    <a href="#" class="btn btn-danger" @click="unfollow(video), hideBtn(index)">좋아요 취소</a> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useChannelStore } from '../../stores/channel.js';
import { useReviewStore } from '../../stores/review';
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();
const channelStore = useChannelStore();
const channelName = route.params.channelName;
const reviewStore = useReviewStore();

onMounted(() => {
    channelStore.getVideoList(channelName);
})

const goToVideo = (id) => {
    reviewStore.videoID = id
    reviewStore.selectedYoutube = `https://www.youtube.com/embed/${id}`
    router.push('/review')
}

</script>

<style scoped>

#app {
    align-items: start;
    display: grid;
    grid-gap: 16px;
    grid-template-columns: repeat(auto-fit, 300px);
    justify-content: center;
}

iframe {
    width: 100%;
    height: 15vw;
    object-fit: cover;
    border-radius: 5px;
}

.card {
    width: 300px;
    margin-bottom: 10px;
    padding: 5px;
}

.card-title {
    font-size: 20px;
}

</style>
