<template>
    <div>
      <div class="modal" tabindex="-1" role="dialog" style="display: block;">
    <div class="modal-dialog" role="document">
      <div class="modal-content">  
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="제목을 입력하세요" v-model="title">
        <label for="floatingInput">제목</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="content">
        <label for="floatingTextarea">내용</label>
      </div>
      별점:<select v-model="rating">
            <option value="1">⭐</option>
            <option value="2">⭐⭐</option>
            <option value="3">⭐⭐⭐</option>
            <option value="4">⭐⭐⭐⭐</option>
            <option value="5">⭐⭐⭐⭐⭐</option>
        </select>

    <span class="border-bottom"></span>
    <div>
        <form action="#">
            <br>
            <button @click="clickUpdate()" type="button" class="shadow btn btn-outline-primary">수정</button>
            <button @click="handleClose" type="button" class="shadow btn btn-outline-danger">취소</button>
        </form>
    </div>
    </div>
  </div>
  </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {useReviewStore} from '@/stores/review'
const store = useReviewStore()
const title = ref('')
const content = ref('')
const rating = ref('0')
const handleClose = () => {
  store.showUpdate=false
};

const clickUpdate = () => {
  store.updateReview(content, title, store.reviewID, rating);
  setTimeout(() => {
    store.updateRating(store.videoID);
  }, 700);//ssafy컴이 느려서 0.7초
  store.showUpdate = false;
}


</script>

<style scoped>
p{ margin-top: 120px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;}
</style>