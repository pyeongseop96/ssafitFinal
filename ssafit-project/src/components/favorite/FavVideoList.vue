<template>
    <div>
        <h3>찜한 동영상 목록</h3>
        <ul>
            <li v-for="video in favStore.favVideos">
                <p @click="goToVideo(video.videoID)">동영상ID : {{ video.videoID }}</p>
                <div>
        <!-- <iframe :src="`https://www.youtube.com/embed/${video.videoID}`"
         width="400px" height="300px"></iframe> -->
    </div>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { RouterLink } from 'vue-router'
import { ref, onMounted } from 'vue';
import { useFavoriteStore } from '../../stores/favorite';
import { useReviewStore } from '../../stores/review';
import router from '../../router';

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


</script>

<style scoped></style>