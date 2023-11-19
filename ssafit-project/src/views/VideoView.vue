<template>
    <div>
        <h2>비디오</h2>
        <h5> 트레이너 선택</h5>
        <input @click="changeTrainer('ThankyouBuBu')" type="radio" name="partInfo" id="ThankyouBUBU"><label for="ThankyouBUBU"> ThankyouBUBU </label> |
        <input @click="changeTrainer('GYM%EC%A2%85%EA%B5%AD')" type="radio" name="partInfo" id="GYM종국"><label for="GYM종국"> GYM종국 </label> |
        <input @click="changeTrainer('SomiFit')" type="radio" name="partInfo" id="SomiFit"><label for="SomiFit"> SomiFit </label>
        <h5> 운동 부위 선택 </h5>
        <div id="radio-btn">
            <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                <input @click="changePart('%EC%A0%84%EC%8B%A0')" type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked
                   >
                <label class="btn btn-outline-primary" for="btnradio1"> 전신 </label>

                <input @click="changePart('%EC%83%81%EC%B2%B4')" type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off"
                   >
                <label class="btn btn-outline-primary" for="btnradio2"> 상체 </label>

                <input @click="changePart('%ED%95%98%EC%B2%B4')" type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off"
                    >
                <label class="btn btn-outline-primary" for="btnradio3"> 하체 </label>

                <input @click="changePart('%EB%B3%B5%EB%B6%80')" type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off"
                   >
                <label class="btn btn-outline-primary" for="btnradio4"> 복부 </label>
            </div>
        </div>
        정렬방법
        <input @click="changeSort('vr.averageRating')" type="radio" name="sort" id="averageRating"><label for="averageRating"> 평점 </label> |
        <input @click="changeSort('v.viewCnt')" type="radio" name="sort" id="viewCnt"><label for="viewCnt"> 조회수 </label> |
        <input @click="changeSort('favorite')" type="radio" name="sort" id="favorite"><label for="favorite"> 찜 </label>
        <table>

            <tr>
                <th>제목</th>
                <th>트레이너</th>
                <th>운동부위</th>
                <th>조회수</th>
                <th>평점</th>
                <th> --- 찜하기 --- </th>
            </tr>

            <tr v-for="(video, index) in videos.slice(0, 10)" :key="index">
                <td @click="goToVideo(video.videoID)">{{ video.title }}</td>
                <td>{{ video.channelName }}</td>
                <td>{{ video.partInfo }}</td>
                <td>{{ video.viewCnt }}</td>
                <td>{{ video.averageRating }}({{ video.totalReviews }})</td>
                <td>{{ video.favorite }}</td>
            </tr>

        </table>

    </div>
</template>

<script setup>
import { onMounted, onUpdated, ref, watch } from 'vue';
import { useVideoStore } from '../stores/video';
import { useReviewStore } from '../stores/review';
import { useFavoriteStore } from '../stores/favorite';
import router from '../router';
import { computed } from '@vue/reactivity';


//
import axios from 'axios';
import { useUserStore } from '@/stores/user';

const userID = useUserStore().user.userID;
const videos = ref([]);
const trainer = ref('ThankyouBuBu')
const part = ref('전신')
const sort = ref('vr.averageRating')

const ultraPunch = function () {
        axios.get(`http://localhost:8080/api-video/ultra?userID=${userID}&channelName=${trainer.value}&partInfo=${part.value}&sort=${sort.value}`)
        .then((res) => {
          console.log(res.data)
          videos.value = res.data;
        })
        .catch((err) => console.log(err));
    }

const changeTrainer = ((newTrainer) => {
    trainer.value = newTrainer;
    ultraPunch()
})

const changePart = ((newPart) => {
    part.value = newPart;
    ultraPunch()
})

const changeSort = ((newSort) => {
    sort.value = newSort;
    ultraPunch()
})


//







// const store = useVideoStore()
// const reviewStore = useReviewStore()
// // const favStore = useFavoriteStore();
// const changeWord = (word) => {
//     store.word = word
//     // store.getVideoList()
//     store.getFavPartList();
// };

// const goToVideo = (id) => {
//     reviewStore.videoID = id
//     reviewStore.selectedYoutube = `https://www.youtube.com/embed/${id}`
//     router.push('/review')
// }

// onMounted(() => {
//     // store.getVideoList();
//     // favStore.getAllVideos();
//     store.getFavPartList();
// })

// const toggleFav = (video) => {
//     // favStore.setFavVideo(video)
//     store.setFavVideo(video)

//     if (video.userID !== null) {
//         video.userID = null;
//     } else {
//         // video.userID = favStore.user.userID;
//         video.userID = store.user.userID;
//     }
// }



</script>

<style scoped></style>