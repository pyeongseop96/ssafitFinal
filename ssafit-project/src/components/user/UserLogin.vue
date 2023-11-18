<script setup>
import { useUserStore } from '../../stores/user';
import {ref} from 'vue'

const userStore = useUserStore();

const id = ref('')
const password = ref('')

const login = () => {
    userStore.loginUser(id.value, password.value);
}


</script>


<template>
    <div>
        <h2>로그인</h2>
        <div>
            <label for="floatingInput">🖊️아이디</label>
            <input type="text" id="floatingInput" placeholder="아이디" v-model="id">
        </div>
        <div>
            <label for="floatingPassword">🔒비밀번호</label>
            <input type="password" id="floatingPassword" placeholder="Password" v-model="password">
        </div>
        <button @click="login">로그인</button>
    </div>
</template>

<style></style>
