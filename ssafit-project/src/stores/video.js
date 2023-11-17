import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import {useRouter} from 'vue-router'

const VideoURL = 'http://localhost:8080/api-video/video/search?key=partInfo&word='
const router = useRouter();

export const useVideoStore = defineStore('video', () => {
  const showModal = ref(false)
  const selectedVideo = ref(null)

  const videos = ref([])
  const word = ref('%EC%A0%84%EC%8B%A0');
  const getVideoList = function () {

    axios.get(VideoURL + word.value, {
      headers: {
        'access-token': sessionStorage.getItem('access-token')
      }
    })

      .then((response) => {
        videos.value = response.data
      })
      .catch((err) => console.log(err))

  }







////////////////////////////////////////////////////
  const video = ref({})

  const getVideo = (id) => {
    axios.get(API_VIDEO + `/video/${id}`)
    .then((res) =>{
      console.log(video.value)
      video.value = res.data
    })
    .catch((err) => console.log(err))
  }


  const favoriteList = ref([
    {
      userID: "123", 
      videoID:[
        "999",
      ],
    },{
      userID: "123", 
      videoID:[
        "123",
      ],
    }
  ])

  const getFavoriteList = () => {
    // axios.get(API_VIDEO + '/favorite')
    // .then((res) =>{
    //   favoriteList.value = res.data
    // })
    // .catch((err) => console.log(err))
    
  }

    const searchVideoList = (searchCondition) => {
      axios.get(API_VIDEO + "/video/search", {
        params: searchCondition
    })
    .then((res) => {
      videoList.value = res.data
    }).catch((err) => {
      console.log(err)
    })
}

const logoutUser = () => {
  axios.get(API_USER + "/logout")
  .then((res) => {
    user = {
      userID: '',
      name: '',
      email: '',
      password: '',
      age: 0,
    }
  }).catch((err) => {
    console.log(err)
  })
}



  return { videos, selectedVideo,  getVideoList, word, showModal, getVideoList, video, getVideo, searchVideoList, favoriteList, getFavoriteList, logoutUser}
})