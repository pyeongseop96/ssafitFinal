<template>
    <div>
             <!-- 임시 태그 -->
     <div>
        <h2>(임시) 동영상 주소 입력 : </h2>
      <input type="text" v-model="keyword">
      <button @click="search">주소 변경</button>
      <router-link :to="{ name: 'reviewList'}"></router-link>
      <hr>
        </div>
        <!-- 임시 끝 -->
        <p class="fw-bold fs-4 text-center" id="fontt"> ✏️ 운동영상 리뷰 ✏️ </p>
    <hr>
    <br>
    <div align="center">
        <iframe :src=store.selectedYoutube width="400px" height="300px"></iframe>
    </div>
    <hr>
        <RouterView />
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useReviewStore } from '../stores/review';
const router = VueRouter()


//임시
const keyword = ref('')
const store = useReviewStore()
const search = function() {
    store.selectedYoutube = `https://www.youtube.com/embed/${keyword.value}`
}
//임시 끝



</script>



<style scoped>
p{ margin-top: 120px; }
      .form-floating { margin: 25px 35px 0px; }
      .btn-outline-primary {margin-left: 35px;}  
</style>