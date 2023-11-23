<template>
    <div>
        <h3>{{ channelName }} 채널의 동영상 목록</h3>
        <ul>
            <li v-for="video in channelStore.videoList">
                <div>
                    <iframe :src="`https://www.youtube.com/embed/${video.videoID}`"
                    width="400px" height="300px"></iframe>
                </div>
                <div>
                    <p>{{ video }}</p>
                </div>
                <hr>
            </li>
        </ul>
    </div>
</template>

<script setup>
import { onMounted } from 'vue';
import {useChannelStore} from '../../stores/channel.js';
import { useRoute, useRouter } from "vue-router";

const channelStore = useChannelStore();
const route = useRoute();
const channelName = route.params.channelName;

onMounted(() => {
    channelStore.getVideoList(channelName);
})

</script>

<style scoped></style>