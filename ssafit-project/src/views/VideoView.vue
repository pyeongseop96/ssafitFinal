<template>
    <div style="margin-left: 50px;">
        <v-row>
        <v-col class="pa-4 cell" cols="12" md="3">
            <v-card>
                <div style="margin: 20px;">
        <h5> 트레이너 선택</h5>
        <v-select style="width: 90%;"
        label="Select123213"

    v-model="trainer"
    :items="trainers"
    item-title="title"
    item-value="value"
    single-line
  ></v-select>
  <h5> 운동부위 선택</h5>
        <v-select style="width: 90%;"
        label="Select123213"
    v-model="part"
    :items="parts"
    item-title="title"
    item-value="value"
    single-line
  ></v-select>

  <h5> 정렬방법 선택</h5>
        <v-select style="width: 90%;"
        label="Select123213"
    v-model="sort"
    :items="sorts"
    item-title="title"
    item-value="value"
    single-line
  ></v-select>
  
        <v-btn @click="ultraPunch">검색</v-btn>
</div>
</v-card>
</v-col>

<v-col class="pa-4 cell" cols="12" md="9">
            <v-card style="margin-right: 30px;">

        <table style="text-align: center; width: 100%;">
            <br>
            <tr>
                <th>&nbsp;제목&nbsp;</th>
                <th>&nbsp;트레이너&nbsp;</th>
                <th>&nbsp;운동부위&nbsp;</th>
                <th>&nbsp;조회수&nbsp;</th>
                <th>&nbsp;별점&nbsp;</th>
                <th v-show="userID!=null">&nbsp;찜하기&nbsp;</th>
            </tr>

            <tr v-for="(video, index) in videos.slice(0, 10)" :key="index">
                <td @click="goToVideo(video.videoID)">{{ video.title }}</td>
                <td @click="goToChannel(video.channelName)">{{ video.channelName }}</td>
                <td>{{ video.partInfo }}</td>
                <td>{{ video.viewCnt }}</td>
                <td>⭐{{ video.averageRating !== null ? video.averageRating : '0.0' }}({{ video.totalReviews !== null ? video.totalReviews : '0' }})</td>
                <td v-show="userID!=null" @click="toggleFav(video.videoID, video.favorite)">{{ video.favorite=='yes'?'❤️':'🤍' }}</td>
            </tr>

        </table>

        </v-card>
        </v-col>
        
        </v-row>

    </div>
</template>

<script setup>
import { onMounted, ref} from 'vue';
import axios from 'axios';
import { useUserStore } from '@/stores/user';
import router from '@/router';
import { useReviewStore } from '@/stores/review';
import { useVideoStore } from '@/stores/video';

const userID = useUserStore().user.userID;
const videos = ref([]);
const trainer = ref('ThankyouBuBu')
const part = ref('%EC%A0%84%EC%8B%A0')
const sort = ref('vr.averageRating')

onMounted(() => {
    ultraPunch();
})

//영상 관련 정보 다 가져옵니다.
const ultraPunch = function () {
        axios.get(`http://localhost:8080/api-video/ultra?userID=${userID}&channelName=${trainer.value}&partInfo=${part.value}&sort=${sort.value}`)
        .then((res) => {
          console.log(res.data)
          videos.value = res.data;
        })
        .catch((err) => console.log(err));
    }




//동영상 클릭 -> 조회수+1 -> reviewView 이동
const reviewStore = useReviewStore();
const videoStore = useVideoStore();
const goToVideo = (videoID) => {
    console.log(videoID)
    videoStore.addViewCount(videoID)
    reviewStore.videoID = videoID
    reviewStore.selectedYoutube = `https://www.youtube.com/embed/${videoID}`
    router.push('/review')
}
// 채널명 클릭 -> 채널 페이지로 이동
const goToChannel = (channelName) => {
    router.push(`/channel/${channelName}`)
}


//찜 클릭 -> 토글
const toggleFav = (videoID, favorite) => {
    favorite=='yes'?videoStore.setFavVideo(videoID,true):videoStore.setFavVideo(videoID,false)
    setTimeout(() => {
        ultraPunch();
  }, 100);//db업데이트 시간 조금 기다리기
}

// const store = useVideoStore()
// const reviewStore = useReviewStore()
// // const favStore = useFavoriteStore();
// const changeWord = (word) => {
//     store.word = word
//     // store.getVideoList()
//     store.getFavPartList();
// };

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


const trainers = [
          { title: 'ThankyouBuBu', value: 'ThankyouBuBu' },
          { title: 'SomiFit', value: 'SomiFit' },
          { title: 'GYM종국', value: 'GYM%EC%A2%85%EA%B5%AD' },
        ]
const parts = [
          { title: '전신', value: '%EC%A0%84%EC%8B%A0' },
          { title: '상체', value: '%EC%83%81%EC%B2%B4' },
          { title: '하체', value: '%ED%95%98%EC%B2%B4' },
          { title: '복부', value: '%EB%B3%B5%EB%B6%80' },
        ]     
        
const sorts = [
{ title: '평점순', value: 'vr.averageRating' },
{ title: '조회수순', value: 'v.viewCnt' },
{ title: '찜순', value: 'favorite' },
]


</script>

<style scoped>
td{
    padding: 15px;
}
</style>