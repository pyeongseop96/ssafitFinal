import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const VideoURL = 'http://localhost:8080/api-video/video/search?key=partInfo&word='

export const useVideoStore = defineStore('video', () => {
  const showModal = ref(false)
  const videos = ref([])
  const selectedVideo = ref(null)
  const word = ref('%EC%A0%84%EC%8B%A0');

  const getVideoList = function () {

    axios.get(VideoURL + word.value)
      .then((response) => {
      videos.value = response.data
      })
  }

 


  return { videos, selectedVideo,  getVideoList, word, showModal}
})

