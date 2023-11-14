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

    <span class="border-bottom"></span>
    <div>
        <form action="#">
            <br>
            <button @click="store.updateReview(content, title, store.reviewID)" type="button" class="shadow btn btn-outline-primary">수정</button>
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
const handleClose = () => {
  store.showUpdate=false
};
</script>

<style scoped>
p{ margin-top: 120px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;}
</style>