<script setup>

import {onMounted, onUpdated } from 'vue';
// import { useSsafitStore } from '../../stores/ssafitqwer';
import { useRoute, useRouter } from 'vue-router';

// const store = useSsafitStore();
const route = useRoute();

onMounted(() => {
    store.getVideo(route.params.id);
})


const review = () => {
// 리뷰 등록 구현
}

</script>

<template>
    <div>
        <h3>비디오 상세정보</h3>
        <!-- <p>ID : {{ store.video.videoID }}</p>
        <p>제목 : {{ store.video.title}}</p>
        <p>채널 : {{ store.video.channelName }}</p>
        <p>조회수 : {{ store.video.viewCnt }}</p>
        <p>운동부위 : {{ store.video.partInfo }}</p>
        <button @click="review">리뷰 등록하기</button> -->
    </div>
</template>

<style scoped>
</style>