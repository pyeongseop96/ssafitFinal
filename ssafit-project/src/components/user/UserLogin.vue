<script setup>
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();

const login = () => {
    userStore.loginUser();
}


</script>


<template>
    <div>
        <h2>로그인</h2>
        <div>
            <label for="floatingInput">🖊️아이디</label>
            <input type="text" id="floatingInput" placeholder="아이디" v-model="userStore.user.userID">
        </div>
        <div>
            <label for="floatingPassword">🔒비밀번호</label>
            <input type="password" id="floatingPassword" placeholder="Password" v-model="userStore.user.password">
        </div>
        <button @click="login">로그인</button>
    </div>
</template>

<style></style>
