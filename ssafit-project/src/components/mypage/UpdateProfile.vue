<template>
    <div>
        <h3>프로필 수정하기</h3>
        <div>
            <p>ID : {{ userStore.user.userID }}</p>
        </div>
        <div>
            <label for="name">비밀번호 : </label>
            <input id="name" v-model="userInfo.password">
        </div>
        <div>
            <label for="name">이름 : </label>
            <input id="name" v-model="userInfo.name" :placeholder="userStore.user.name">
        </div>
        <div>
            <label for="name">이메일 : </label>
            <input id="name" v-model="userInfo.email" :placeholder="userStore.user.email">
        </div>
        <div>
            <label for="name">나이 : </label>
            <input id="name" v-model="userInfo.age" :placeholder="userStore.user.age">
        </div>
    </div>
</template>

<script setup>

import { useUserStore } from '../../stores/user';
import { ref, onMounted, } from 'vue';

const userStore = useUserStore();
const userInfo = ref({});

onMounted(() => {
    userInfo = userStore.user;
})

</script>



<style scoped>
    label {
        width: 5rem;
    }
    div {
        padding: 3px;
    }

</style>
