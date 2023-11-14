<script setup>
import {RouterLink} from 'vue-router'
import {ref, onMounted } from 'vue';
import { useSsafitStore } from '../../stores/ssafit';

const store = useSsafitStore();

onMounted(() => {
    store.getVideoList();
})


const searchInfo = ref({
    key: 'none',
    word: '',
    orderBy: 'none',
    orderByDir: 'asc',
})
const searchList = () => {
    store.searchVideoList(searchInfo.value);
}


</script>

<template>
    <div>
        <!-- 비디오 목록 -->
        <h3>비디오 리스트</h3>
        <ul>
            <li v-for="video in store.videoList" :key="video.id">
                <p>번호 : {{ video.videoID }}</p>
                <p>제목 : {{ video.title}}</p>
                <p>채널 : {{ video.channelName }}</p>
                <p>조회수 : {{ video.viewCnt }}</p>
                <p>운동부위 : {{ video.partInfo }}</p>
                <RouterLink :to="`/video/${video.videoID}`">상세</RouterLink>
            </li>
        </ul>
    </div>
        <!-- 검색 기능 -->
    <div>
        <label>검색 기준 :</label>
        <select v-model="searchInfo.key">
            <option value='none'>없음</option>
            <option value="title">제목</option>
            <option value="channelName">채널</option>
            <option value="partInfo">운동부위</option>
        </select>
    </div>
    <div>
        <label>검색 내용 :</label>
        <input type="text" v-model="searchInfo.word" />
    </div>
    <div>
        <label>정렬 기준 :</label>
        <select v-model="searchInfo.orderBy">
            <option value='none'>없음</option>
            <option value="title">제목</option>
            <option value="viewCnt">조회수</option>
        </select>
    </div>
    <div>
        <label>정렬 방향 :</label>
        <select v-model="searchInfo.orderByDir">
            <option value="asc">오름차순</option>
            <option value="desc">내림차순</option>
        </select>
    </div>
    <div>
        <button @click="searchList">검색</button>
    </div>

</template>

<style scoped>

</style>