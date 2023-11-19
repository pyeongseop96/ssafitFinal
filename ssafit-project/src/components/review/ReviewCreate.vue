<template>
     <div class="modal" tabindex="-1" role="dialog" style="display: block;">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
    <div>
      댓글입력
        <br>
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="제목" v-model="title">
        <label for="floatingInput">제목</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInputGrid" placeholder="내용" v-model="content">
        <label for="floatingInputGrid">내용</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInputGrid" placeholder="별점" v-model="rating">
        <label for="floatingInputGrid">별점</label>
      </div>

    <span class="border-bottom"></span>
    <div>
        <form action="#">
            <br>
            <button @click="store.createReview(content, title, loginID, store.videoID)" type="button" class="shadow btn btn-outline-primary">등록</button>
              <button @click="handleClose" type="button" class="shadow btn btn-outline-danger">취소</button>
        </form>
    </div>
    </div>
</div>
    </div>
  </div>
</template>

<script setup>
import {useReviewStore} from '@/stores/review'
import { ref } from 'vue';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();

const loginID = ref(userStore.user.name);

const store = useReviewStore()
const title = ref('')
const content = ref('')

const handleClose = () => {
  store.showModal=false
};
</script>

<style scoped>
p{ margin-top: 120px; }
      .form-floating { margin: 25px 35px 0px; }
      .btn-outline-primary {margin-left: 35px;}   
</style>