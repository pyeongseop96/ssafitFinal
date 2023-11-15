<template>
    <div>
        <header>



            <nav>임시헤더
                <RouterLink to="/">home</RouterLink> | 
                <RouterLink to="/video">video</RouterLink> | 
                <RouterLink to="/record">record</RouterLink> | 
                <RouterLink to="/calorie">calorie</RouterLink> | 
                <RouterLink to="/video">video</RouterLink> 
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