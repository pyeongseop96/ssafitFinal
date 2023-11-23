<template>
    <div>
        <h3>찜한 동영상 목록</h3>
        <div id="app">
            <div v-for="video in favStore.favVideos" class="card-deck">
                <div class="card">
                    <div class="card-img-top" alt="...">
                        <img :src="`http://img.youtube.com/vi/${video.videoID}/0.jpg`" />
                    </div>
                    <div class="card-body">
                        <p class="card-title">{{ video.title }}</p>
                        <p> 채널명 : {{ video.channelName }}</p>
                        <p> 조회 수 : {{ video.viewCnt }}</p>
                        <p> 운동부위 : {{ video.partInfo }}</p>
                        <a href="#" class="btn btn-primary" @click="goToVideo(video.videoID)">보러 가기</a>
                        <a href="#" class="btn btn-danger" @click="unfollow(video), hideBtn(index)">좋아요 취소</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { ref, onMounted } from 'vue';
import { useFavoriteStore } from '../../stores/favorite';
import { useReviewStore } from '../../stores/review';
import { useRouter } from 'vue-router';

const router = useRouter();
const favStore = useFavoriteStore();
const reviewStore = useReviewStore()

const goToVideo = (id) => {
    reviewStore.videoID = id
    reviewStore.selectedYoutube = `https://www.youtube.com/embed/${id}`
    router.push('/review')
}

onMounted(() => {
    favStore.getFavVideos();
})

const unfollow = (video) => {
    favStore.setFavVideo(video)
}

const hideBtn = (index) => {
    favStore.favVideos.splice(index, 1);
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

img {
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

.btn {
    margin: 0 0.2rem;
}
</style>