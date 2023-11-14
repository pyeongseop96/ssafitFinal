<template>
    <div>

        <p class="fw-bold fs-4 text-center" id="fontt"> ✏️ 운동영상 ✏️ </p>
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
import {RouterLink} from 'vue-router'


const store = useReviewStore()



</script>



<style scoped>
p{ margin-top: 120px; }
      .form-floating { margin: 25px 35px 0px; }
      .btn-outline-primary {margin-left: 35px;}  
</style>