<template>
    <div>
        <h2>비디오</h2>
        <h5> 트레이너 선택(미구현) </h5>
    <input type="radio" name="partInfo" id="ThankyouBUBU"><label for="ThankyouBUBU"> ThankyouBUBU </label> |
    <input type="radio" name="partInfo" id="GYM종국"><label for="GYM종국"> GYM종국 </label> |
    <input type="radio" name="partInfo" id="SomiFit"><label for="SomiFit"> SomiFit </label>
        <h5> 운동 부위 선택 </h5>
    <div id="radio-btn">
        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
            <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked @click="changeWord('%EC%A0%84%EC%8B%A0')">
            <label class="btn btn-outline-primary" for="btnradio1"> 전신 </label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" @click="changeWord('%EC%83%81%EC%B2%B4')" >
            <label class="btn btn-outline-primary" for="btnradio2"> 상체 </label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off" @click="changeWord('%ED%95%98%EC%B2%B4')">
            <label class="btn btn-outline-primary" for="btnradio3"> 하체 </label>

            <input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off" @click="changeWord('%EB%B3%B5%EB%B6%80')">
            <label class="btn btn-outline-primary" for="btnradio4"> 복부 </label>
        </div>
    </div>

    <table>

        <tr>
            <th>제목</th>
            <th>트레이너</th>
            <th>운동부위</th>
        </tr>

        <tr v-for="(video, index) in store.videos.slice(1, 11)" :key="index">
            <td @click="goToVideo(video.videoID)">{{ video.title }}</td>
            <td>{{ video.channelName }}</td>
            <td>{{ video.partInfo }}</td>
        </tr>

    </table>

   </div>
</template>

<script setup>
import {onMounted, onUpdated, ref, watch } from 'vue';
import { useVideoStore } from '../stores/video';
import { useReviewStore } from '../stores/review';
import router from '../router';
import { computed } from '@vue/reactivity';

const store = useVideoStore()
const reviewStore = useReviewStore()
const changeWord = (word) => {
  store.word = word
  store.getVideoList()
};

const goToVideo = (id) => {
reviewStore.videoID = id
reviewStore.selectedYoutube = `https://www.youtube.com/embed/${id}`
router.push('/review')
}

onMounted(() => {
  store.getVideoList()
})




</script>

<style scoped>

</style>