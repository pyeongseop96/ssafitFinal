<template>
    <div>
        <MypageNav/>
        <p>마이페이지입니다....</p>
        <RouterView/>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import MypageNav from '../components/mypage/MypageNav.vue';

import { useRouter, RouterView } from 'vue-router';

onMounted(() => {
    useRouter().push("/mypage/favvideo")
})

</script>



<style scoped>

</style>