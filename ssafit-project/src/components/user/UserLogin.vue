<script setup>
import { useSsafitStore } from '../../stores/ssafit';

const store = useSsafitStore();

const login = () => {
   store.loginUser();
}


</script>


<template>
    <div>
        <h2>로그인</h2>
        <div>
            <label for="floatingInput">🖊️아이디</label>
            <input type="text" id="floatingInput" placeholder="아이디"
            v-model="store.user.userID">
        </div>
        <div>
            <label for="floatingPassword">🔒비밀번호</label>
            <input type="password" id="floatingPassword" placeholder="Password"
            v-model="store.user.password">
        </div>
        <button @click="login">로그인</button>
    </div>
</template>

<style>

</style>
