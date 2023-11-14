import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import { useRouter } from 'vue-router'

const API_VIDEO = 'http://localhost:8080/api-video'
const API_USER = 'http://localhost:8080/api-user'

export const useSsafitStore = defineStore('ssafit', () => {

  const router = useRouter();

  const videoList = ref([])

  const getVideoList = () => {
    axios.get(API_VIDEO + '/video')
    .then((res) =>{
      videoList.value = res.data
    })
    .catch((err) => console.log(err))
  }

  const newUser = ref({
    userID: '',
    name: '',
    email: '',
    password: '',
    age: 0,
  })
  
  const registUser = () => {
    axios.post(API_USER + '/signup', newUser.value)
    .then((res) => {
      console.log(res)
      router.push({name: "home"}) 
    })
    .catch((err) => console.log(err))
  }

  const user = ref({
    userID: '',
    name: '',
    email: '',
    password: '',
    age: 0,
  })


  const loginUser = () => {
    axios.post(API_USER + '/login', user.value)
    .then((res) => {
      user.value=res.data
      alert("로긴 성공")
      router.push({name: "home"})
    })
    .catch((err) => {
      console.log(err)
      alert("로긴 실패")
    })
  }

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

  
  return { newUser, registUser, user, loginUser,  videoList, getVideoList, video, getVideo, searchVideoList, favoriteList, getFavoriteList}
})
