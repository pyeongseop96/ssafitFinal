<script setup>
import { RouterLink } from 'vue-router'
import { ref, onMounted } from 'vue';
import { useFavoriteStore } from '../../stores/favorite';

const favStore = useFavoriteStore();


onMounted(() => {
    favStore.getFavChannels();
})


</script>

<template>
    <div>
        <h3>구독한 채널 목록</h3>
        <ul>
            <li v-for="channel in favStore.favChannels">
                <p>채널명 : {{ channel }}</p>
            </li>
        </ul>
    </div>
</template>

<style scoped></style>