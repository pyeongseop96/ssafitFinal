import {ref} from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useUserStore } from './user';

const API_FAVORITE = 'http://localhost:8080/api-favorite'

export const useFavoriteStore = defineStore('favorite', () => {

    const favChannels = ref([]);
    const getFavChannels = () => {
        const id = useUserStore().user.userID;
        console.log(id)
        axios.put(API_FAVORITE + '/channel', id)
        .then((res) => {
            favChannels.value = res.data
        })
        .catch((err) => console.log(err));
    }
    
    const favVideos = ref([]);
    const allVideos = ref([]);

    const getAllVideos = () => {
        const id = useUserStore().user.userID;
        axios.put(API_FAVORITE + '/video/all', id)
        .then((res) => {
            console.log(res)
            allVideos.value = res.data
        })
        .catch((err) => console.log(err));
    }

    const setFavVideo = (video) => {
        console.log(video)
        axios.post(API_FAVORITE + '/toggle/video', null, {
            params: {
                userID: useUserStore().user.userID, // 찜하지 않았면 null값을 전송하고 찜하면 로그인한 유저의 ID 전송
                videoID: video.videoID,
                isFavorite: video.userID !== null,
            }
        })
        .then((res) => {
            console.log(res);
        })
        .catch((err) => {
            console.log(err)
        });
    }

    const getFavVideos = () => {
        axios.put(API_FAVORITE + '/video/favorite', useUserStore().user.userID)
        .then((res) => {
            console.log(res)
            favVideos.value = res.data
        })
        .catch((err) => console.log(err));
    }


    return {favChannels, favVideos, getFavChannels, allVideos, getAllVideos, getFavVideos, setFavVideo,}
});