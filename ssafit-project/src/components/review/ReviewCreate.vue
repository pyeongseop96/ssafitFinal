<template>
     <div class="modal" tabindex="-1" role="dialog" style="display: block;">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
    <div>

      댓글입력
        <br>

    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="제목" v-model="title" required>
        <label for="floatingInput">제목</label>
      </div>

      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInputGrid" placeholder="내용" v-model="content" >
        <label for="floatingInputGrid">내용</label>
      </div>
  
 
        별점:<select v-model="rating">
    <option v-for="option in options" :value="option.value">
      {{ option.text }}
    </option>
  </select>
    <span class="border-bottom"></span>
    <div>

            <br>
            <form action="#">
            <button @click="clickRegister()" type="button" class="shadow btn btn-outline-primary">등록</button>
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
const rating = ref(5)
const options = ref([
  { text: '⭐', value: 1 },
  { text: '⭐⭐', value: 2 },
  { text: '⭐⭐⭐', value: 3 },
  { text: '⭐⭐⭐⭐', value: 4 },
  { text: '⭐⭐⭐⭐⭐', value: 5 },
])

const handleClose = () => {
  store.showModal=false
};

const clickRegister = () => {
  if(title.value==''){
    alert('제목을 입력해주세요.')
    return;
  }
  if(content.value==''){
    alert('내용을 입력해주세요.')
    return;
  }
  store.createReview(content, title, userStore.user.userID, store.videoID, rating);
  store.updateRating(store.videoID);
  store.showModal = false;
}
</script>

<style scoped>
p{ margin-top: 120px; }
      .form-floating { margin: 25px 35px 0px; }
      .btn-outline-primary {margin-left: 35px;}   
</style>