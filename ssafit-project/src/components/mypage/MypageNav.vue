<template>
    <div>
        <header>
            <nav>
                <!-- <RouterLink to="/">Home</RouterLink> | 
                <RouterLink :to="{name:'list'}">비디오</RouterLink> | 
                <span v-if="userStore.user.name == ''">
                    <RouterLink :to="{name:'login'}">로그인</RouterLink> | 
                    <RouterLink :to="{name:'regist'}">회원가입</RouterLink> | 
                </span>
                <span v-else>
                    <RouterLink :to="{name:'favorite'}">좋아요</RouterLink> | 
                    <RouterLink :to="{name:'mypage'}">마이페이지</RouterLink> | 
                    <a @click="logout">로그아웃</a> | 
                </span>
                <RouterLink :to="{name:'map'}">주변 헬스장 찾기</RouterLink> |  -->
                <RouterLink :to="{name:'favoriteVideo'}">찜한 영상</RouterLink> | 
                <RouterLink :to="{name:'favoriteChannel'}">구독 채널</RouterLink> | 
                <RouterLink :to="{name:'updateProfile'}">프로필 변경</RouterLink> | 
            </nav>
        </header>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import { useUserStore } from '../../stores/user';

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
