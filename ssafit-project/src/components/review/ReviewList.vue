<template>
    <h3>댓글: {{ store.reviews.length }}개</h3>
    <h3>별점: ⭐{{ rating.averageRating }}점</h3>
    <div>
     
        <div class="buttons">
        <form action="#">
          <button v-if="loginID" @click="openModal" type="button" class="shadow btn btn-outline-primary">댓글 작성</button>
        </form>
        <ReviewCreate v-if="store.showModal" @closeModal="closeModal" />
        <ReviewUpdate v-if="store.showUpdate" @closeModal="closeUpdate" />
    </div>
    <hr>
    <table class="table table-striped">

            <th>제목</th>
            <th>내용</th>
            <th>별점</th>
            <th>글쓴이</th>
           <th>작성일</th>
            <th>수정</th>
            <th>삭제</th>

            <tr v-for="(item, index) in store.reviews.slice(10*currentPage-10, 10*currentPage)" :key="index">
        <td>{{ item.title }}</td>
        <td>{{ item.content }}</td>
        <td>
          <span v-for="i in item.reviewCnt" :key="i">⭐</span>
        </td>
        <td>{{ item.userID }}</td>
        <td>{{today.getMonth()+1+'-'+today.getDate()==item.regDate.slice(5,10)?item.regDate.slice(11,16):item.regDate.slice(5,10) }}</td>
        <td>
          <button v-if="userID==item.userID"  @click="openUpdate(item.reviewID)" type="button" class="shadow btn btn-outline-primary">수정</button>
        </td>
        <td>
          <button v-if="userID==item.userID" @click="clickDelete(item.reviewID)" type="button" class="shadow btn btn-outline-danger">삭제</button>
        </td>
      </tr>     
     
    </table>

    <!-- 페이지네이션 -->
    <nav aria-label="Page navigation example">
  <ul class="pagination d-flex justify-content-center">
    <li class="page-item"><a class="page-link" :class="{disabled: currentPage <=1}" href="#" @click.prevent="currentPage--">&lt;</a></li>
    <li :class="{active: currentPage===page}" v-for="page in pageRange" class="page-item">
      <a class="page-link" href="#" @click.prevent="currentPage=page">{{ page }}</a></li>

    <li class="page-item"><a class="page-link" :class="{disabled: currentPage >= pageCount}" href="#" @click.prevent="currentPage++">&gt;</a></li>
  </ul>
</nav>

    </div>
</template>

<script setup>
import {onMounted, ref} from 'vue'
import {useReviewStore} from '@/stores/review'
import ReviewCreate from '@/components/review/ReviewCreate.vue';
import ReviewUpdate from '@/components/review/ReviewUpdate.vue';
import { useUserStore } from '@/stores/user';
import {useVideoStore} from '@/stores/video';
import axios from 'axios';
import { watch } from 'vue';
import { computed } from '@vue/reactivity';

const userStore = useUserStore();
const loginID = ref(userStore.user.name);
const userID = ref(userStore.user.userID);

const store = useReviewStore()
const videoStore = useVideoStore();
const rating = ref('');
const today = new Date();

//페이지네이션
const perReview = 10;
const currentPage = ref(1);
const pageCount = computed(() => {
  return Math.ceil(store.reviews.length / perReview)
})
const pageRange = computed(() => {
  const num1 = ref(Math.max(1,currentPage.value-2));
  const num2 = ref(Math.min(num1.value+4, pageCount.value));
  if(num2.value-num1.value+1 < perReview){
    num1.value = Math.max(1, num2.value - 4);
  }

  return Array.from({length:num2.value-num1.value+1}, (_,index) => num1.value + index);
})




const clickDelete = (reviewID) => {
  store.deleteReview(reviewID);
  setTimeout(() => {
    store.updateRating(store.videoID);
  }, 50);
  setTimeout(() => {
    getVideoRating(store.videoID);
    if(store.reviews.length==0){
    setTimeout(() => {
    store.updateRatingByZero(store.videoID);
  }, 50);
  setTimeout(() => {
    getVideoRating(store.videoID);
  }, 100);
    return;
  }
  }, 100);
}

//영상 별점 가져오는 메서드
const getVideoRating = function () {
  axios.get(`http://localhost:8080/api-video/rating?videoID=${store.videoID}`)
  .then((res) => {
    console.log(res)
    rating.value = res.data
    if(rating.value==''){
    rating.value = {averageRating:'0.0'}
  }
  })
}

watch(() => store.showModal, (neww, old) => {
  console.log(123)
  setTimeout(() => {
    getVideoRating(store.videoID);
  }, 200);
});


watch(() => store.showUpdate, (neww, old) => {
  console.log(222)
  setTimeout(() => {
    getVideoRating(store.videoID);
  }, 200);
});


onMounted(() => {
  store.getReviewList()
  getVideoRating(store.videoID)
})

//댓글 작성창
const openModal = () => {
  store.showModal = true;
};
const closeModal = () => {
  store.showModal = false;
};
//댓글 수정창
const openUpdate = (reviewID) => {
  store.reviewID = reviewID
  store.showUpdate = true;
};
const closeUpdate = () => {
  store.showUpdate = false;
};

</script>

<style scoped>

span{
  padding: 0px;
}
</style>