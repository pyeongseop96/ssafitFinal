import {ref} from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';


const API_CHANNEL = 'http://localhost:8080/api-channel'

export const useChannelStore = defineStore('channel', () => {  
    
    const videoList = ref([])

    const getVideoList = (channelName) => {
        axios.get(API_CHANNEL + '/' + channelName)
        .then((res) => {
            console.log(res)
        })
        .catch((err) => console.log(err));
    }

    return {videoList, getVideoList, }
})
