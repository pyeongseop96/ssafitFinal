<script setup>
import { useUserStore } from '../../stores/user';
import { ref } from 'vue'

const userStore = useUserStore();

const id = ref('')
const password = ref('')

const login = () => {
    userStore.loginUser(id.value, password.value);
}


</script>


<template>
    <form>
        <img class="mb-4" src="https://edu.ssafy.com/asset/images/header-logo.jpg" alt="" width="72" height="57">
        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="text" class="form-control" id="floatingInput" placeholder="아이디" v-model="id">
            <label for="floatingInput">ID</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호" v-model="password">
            <label for="floatingPassword">Password</label>
        </div>
        <br>

        <!-- <div class="form-check text-start my-3">
            <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
            <label class="form-check-label" for="flexCheckDefault">
                Remember me
            </label>
        </div> -->
        <button class="btn btn-primary w-100 py-2" type="submit" @click="login">로그인</button>
        <p class="mt-5 mb-3 text-body-secondary">© 2023–2024</p>
    </form>
</template>

<style>

form {
    width: 500px;
    text-align: center;
}
* {
    box-sizing: border-box;
}

</style>
