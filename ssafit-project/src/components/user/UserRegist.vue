<script setup>

import { useUserStore } from '../../stores/user';

const userStore = useUserStore();

const regist = () => {
    userStore.registUser();
}

</script>


<template>
    <div>
        <h1>회원가입</h1>

        <div>
            <label for="floatingInput">🖊️아이디</label>
            <input type="text" id="floatingInput" placeholder="아이디" v-model="userStore.newUser.userID">
        </div>
        <div>
            <label for="floatingName">🖊️이름</label>
            <input type="text" id="floatingName" placeholder="이름" v-model="userStore.newUser.name">
        </div>
        <div>
            <label for="floatingEmail">📧이메일</label>
            <input type="email" id="floatingEmail" placeholder="name@example.com" v-model="userStore.newUser.email">
        </div>
        <div>
            <label for="floatingPassword">🔒비밀번호</label>
            <input type="password" id="floatingPassword" placeholder="Password" v-model="userStore.newUser.password">
        </div>
        <div>
            <label for="floatingAge">나이</label>
            <input type="Number" id="floatingAge" placeholder="Age" v-model="userStore.newUser.age">
        </div>
        <br>
        <div>
            <button @click="regist">가입</button>
            <button>취소</button>
        </div>
    </div>
</template>

<style></style>
