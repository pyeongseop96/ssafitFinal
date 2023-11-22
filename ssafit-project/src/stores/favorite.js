import {ref} from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useVideoStore } from './video';
import { useUserStore } from './user';

const API_FAVORITE = 'http://localhost:8080/api-favorite'

export const useFavoriteStore = defineStore('favorite', () => {    

    const favChannels = ref([]);

    const getFavChannels = () => {
        axios.put(API_FAVORITE + '/channel', useUserStore().user.userID)
        .then((res) => {
            favChannels.value = res.data;
        })
        .catch((err) => console.log(err));
    }

    const isFavChan = ref(false);
    const isFavChannel = (videoID) => {
        axios.get(API_FAVORITE + '/channel', {
            params: {
                userID: useUserStore().user.userID,
                videoID: videoID,
            }
        })
        .then((res) => {
            isFavChan.value = res.data;
        })
        .catch((err) => console.log(err));
    }


    const setFavChannel = (channelName, isFav) => {
        axios.post(API_FAVORITE + '/toggle/channel', null, {
           params: {            
                userID: useUserStore().user.userID,
                channelName: channelName,
                isFavorite: isFav,
            }
        })
        .then((res) => {
            console.log(res)
            isFavChan.value = !isFavChan.value
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


    return {favChannels, favVideos, getFavChannels, allVideos, getAllVideos, getFavVideos, setFavVideo, setFavChannel, isFavChan, isFavChannel}
});