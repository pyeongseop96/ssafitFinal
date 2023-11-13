<template>
    <div id="container">
        <header>



            <nav>임시헤더
                <RouterLink to="/">home</RouterLink> | 
                <RouterLink to="/video">video</RouterLink> | 
                <RouterLink to="/video">video</RouterLink> | 
                <RouterLink to="/video">video</RouterLink> | 
                <RouterLink to="/video">video</RouterLink> 
            </nav>
        </header>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useReviewStore } from '../../stores/review';

</script>

<style  scoped>
#container {
    text-align: center;
}

nav {
    padding: 30px
}

nav a {
    font-weight: bold;
    text-decoration: none;
    color: black;
}

nav a.router-link-exact-active {
    color: #42b983
}
</style>