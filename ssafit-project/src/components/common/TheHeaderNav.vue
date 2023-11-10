<template>
    <div id="container">
        <header>

 <!-- 임시 태그 -->
 <div>
        <h2>(임시) 동영상 주소 입력 : </h2>
      <input type="text" v-model="keyword">
      <button @click="search">주소 변경</button>
      <router-link :to="{ name: 'reviewList'}"></router-link>
      <hr>
        </div>
        <!-- 임시 끝 -->

            <nav>

                <RouterLink to="/review">review</RouterLink> 
            </nav>
        </header>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useReviewStore } from '../../stores/review';
//임시
const keyword = ref('')
const store = useReviewStore()
const search = function() {
    store.selectedYoutube = `https://www.youtube.com/embed/${keyword.value}`
}
//임시 끝
</script>

<style  scoped>
#container {
    text-align: center;
}

nav {
    padding: 30px
}

nav a {
    font-weight: bold;
    text-decoration: none;
    color: black;
}

nav a.router-link-exact-active {
    color: #42b983
}
</style>