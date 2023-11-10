<template>
    <div>
        <header>
            <nav>
                <RouterLink to="/">Home</RouterLink> | 
                <RouterLink :to="{name:'list'}">비디오</RouterLink> | 
                <RouterLink v-if="store.user == null" :to="{name:'login'}">로그인</RouterLink> 
                <RouterLink v-else :to="{name:'login'}">로그아웃</RouterLink> | 
                <RouterLink :to="{name:'regist'}">회원가입</RouterLink> | 
                <RouterLink :to="{name:'favorite'}">좋아요</RouterLink>
            </nav>
            <div>
                <h4>{{ store.user.name }}님 환영합니다.</h4>
            </div>
        </header>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import { useSsafitStore } from '../../stores/ssafit';

const store = useSsafitStore();

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