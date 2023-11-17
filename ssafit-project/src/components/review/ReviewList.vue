<template>
    <h3>댓글: {{ reviewStore.reviews.length }}개</h3>
    <div>
     
        <div class="buttons">
        <form action="#">
          <button @click="openModal" type="button" class="shadow btn btn-outline-primary">댓글 작성</button>
        </form>
        <ReviewCreate v-if="reviewStore.showModal" @closeModal="closeModal" />
        <ReviewUpdate v-if="reviewStore.showUpdate" @closeModal="closeUpdate" />
        <form action="#">
          <input class="form-control" placeholder="🔎제목, 내용으로 검색">
        </form>
    </div>
    <hr>
    <!--게시판 목록 작성 https://getbootstrap.kr/docs/5.2/content/tables/ 참고-->
    <table class="table table-striped">

            <th>제목</th>
            <th>작성자</th>
           <th>내용</th>
           <th>작성시간</th>
            <th>수정</th>
            <th>삭제</th>

            <tr v-for="(item, index) in reviewStore.reviews.slice(0, 10)" :key="index">
        <td>{{ item.title }}</td>
        <td>{{ item.userID }}</td>
        <td>{{ item.content }}</td>
        <td>{{ item.regDate }}</td>
        <td>
          <button v-if="id==item.userID"  @click="openUpdate(item.reviewID)" type="button" class="shadow btn btn-outline-primary">수정</button>
        </td>
        <td>
          <button v-if="id==item.userID" @click="store.deleteReview(item.reviewID)" type="button" class="shadow btn btn-outline-danger">삭제</button>
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
import {onMounted, onUpdated, ref, watchEffect} from 'vue'
import {useReviewStore} from '@/stores/review'
import ReviewCreate from './ReviewCreate.vue';
import { useUserStore } from './user';
import ReviewUpdate from './ReviewUpdate.vue';

const id = useUserStore().user.userID;
const reviewStore = useReviewStore()

onMounted(() => {
  reviewStore.getReviewList()
})




const openModal = () => {
  reviewStore.showModal = true;
};

const openUpdate = (reviewID) => {
  reviewStore.reviewID = reviewID
  reviewStore.showUpdate = true;
};

const closeModal = () => {
  reviewStore.showModal = false;
};

const closeUpdate = () => {
  reviewStore.showUpdate = false;
};




</script>

<style scoped>
.youtubev{
            display: flexbox;
            justify-content: center;
        }
        p{ margin-top: 40px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;}
        .btn-outline-danger {margin-right: 35px;}
        .buttons {display: flex; justify-content: space-between;}
        .form-control{    box-sizing: border-box; list-style: none;}
        .flex-container > div:nth-child(1){
            flex-grow: 1;
        }
        .flex-container > div:nth-child(2){
            flex-grow: 10;
        }
        .flex-container > div:nth-child(5){
            flex-grow: 1;
        }
</style>