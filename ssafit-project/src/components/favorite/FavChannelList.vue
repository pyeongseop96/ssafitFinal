<script setup>
import { RouterLink } from 'vue-router'
import { ref, onMounted } from 'vue';
import { useFavoriteStore } from '../../stores/favorite';

const favStore = useFavoriteStore();


onMounted(() => {
    favStore.getFavChannels();
})

const unfollow = (channel) => {
    favStore.setFavChannel(channel, true)
}                    

const hideBtn = (index) => {
    favStore.favChannels.splice(index, 1);
}


</script>

<template>
    <div>
        <h3>구독한 채널 목록</h3>
        <ul>
            <li v-for="(channel, index) in favStore.favChannels">
                <RouterLink :to="`/channel/${channel}`">채널명 : {{ channel }}</RouterLink>
                <a class="btn btn-danger" @click="unfollow(channel), hideBtn(index)">구독 취소하기</a>
            </li>
        </ul>
    </div>
</template>

<style scoped>

</style>