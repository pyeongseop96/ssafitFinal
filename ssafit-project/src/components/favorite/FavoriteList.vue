<script setup>
import {RouterLink} from 'vue-router'
import {onMounted } from 'vue';
import { useSsafitStore } from '../../stores/ssafit';

const store = useSsafitStore();

onMounted(() => {
    store.getFavoriteList();
})


</script>

<template>
    <div>
        <h3>찜 목록</h3>
        <ul>
            <li v-for="video in store.favoriteList" :key="video.id">
                <p>번호 : {{ video.videoID }}</p>
                <RouterLink :to="`/video/${video.videoID}`"> 임시 제목 </RouterLink>
            </li>
        </ul>
    </div>
</template>

<style scoped>

</style>