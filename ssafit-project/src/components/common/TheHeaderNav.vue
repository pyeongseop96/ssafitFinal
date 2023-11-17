<template>
    <div>
        <header>
            <nav>임시헤더
                <RouterLink to="/">Home</RouterLink> | 
                <RouterLink to="/video">비디오</RouterLink> | 
                <RouterLink to="/record">운동기록</RouterLink> | 
                <RouterLink to="/calorie">칼로리계산기</RouterLink> | 
                <span v-if="userStore.user.name == ''"> <!-- userStore.loginUser.id == null -->
                    <RouterLink :to="{name:'userLogin'}">로그인</RouterLink> | 
                    <RouterLink :to="{name:'userRegist'}">회원가입</RouterLink> | 
                </span>
                <span v-else>
                    <RouterLink :to="{name:'mypageView'}">마이페이지</RouterLink> | 
                    <a @click="logout">로그아웃</a> | 
                </span>
                <RouterLink :to="{name:'mapView'}">주변 헬스장 찾기</RouterLink> | 
            </nav>
            <div v-if="userStore.user.name !== ''">
                <h4 >{{ userStore.user.name }}님 환영합니다.</h4>
            </div>
        </header>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();

</script>

<style scoped>
nav {
    padding: 30px;
}
nav a {
    font-weight: bold;
    text-decoration: none;
    color: black;
}
nav a.router-link-exact-active {
    color: green;
}

</style>