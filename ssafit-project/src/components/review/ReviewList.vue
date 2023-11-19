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
            <th>작성자</th>
           <th>작성일</th>
            <th>수정</th>
            <th>삭제</th>

            <tr v-for="(item, index) in store.reviews.slice(0, 10)" :key="index">
        <td>{{ item.title }}</td>
        <td>{{ item.content }}</td>
        <td>
          <span v-for="i in item.reviewCnt" :key="i">⭐</span>
        </td>
        <td>{{ item.userID }}</td>
        <td>{{ item.regDate.slice(5,10) }}</td>
        <td>
          <button v-if="loginID==item.userID"  @click="openUpdate(item.reviewID)" type="button" class="shadow btn btn-outline-primary">수정</button>
        </td>
        <td>
          <button v-if="loginID==item.userID" @click="store.deleteReview(item.reviewID)" type="button" class="shadow btn btn-outline-danger">삭제</button>
        </td>
      </tr>     
     
    </table>

    <footer>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              <li class="page-item disabled">
                <a class="page-link">Previous</a>
              </li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#">Next</a>
              </li>
            </ul>
          </nav>
    </footer>

    <span class="border-bottom"></span>
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

const userStore = useUserStore();
const loginID = ref(userStore.user.name);
const store = useReviewStore()
const videoStore = useVideoStore();
const rating = ref('');

//영상 별점 가져오는 메서드
const getVideoRating = function () {
  axios.get(`http://localhost:8080/api-video/rating?videoID=${videoStore.videoID}`)
  .then((res) => {
    rating.value = res.data
    if(rating.value==''){
    rating.value = {averageRating:'0.0'}
  }
  })
}

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