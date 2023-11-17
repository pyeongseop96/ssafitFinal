import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import { useRouter } from 'vue-router'

// const API_VIDEO = 'http://localhost:8080/api-video'
const API_USER = 'http://localhost:8080/api-user'

export const useUserStore = defineStore('user', () => {

  const router = useRouter();

  const newUser = ref({ // 회원가입 정보 담을 객체
    userID: '',
    name: '',
    email: '',
    password: '',
    age: 0,
  })
  
  const registUser = () => { // 유저 등록
    axios.post(API_USER + '/signup', newUser.value)
    .then((res) => {
      alert("가입 성공! 가입한 아이디로 로그인해주세요.")
      router.push({name: "userLogin"}) 
    })
    .catch((err) => {
      console.log(err)
      alert("가입 실패! 다시 시도해주세요;;")
    })
  }

  const user = ref({ // 현재 로그인한 유저
    userID: '',
    name: '',
    email: '',
    password: '',
    age: 0,
  })


  const loginUser = () => { // 유저 로그인 기능
    axios.post(API_USER + '/login', user.value)
    .then((res) => {
      user.value = res.data
      console.log(user.value)
      alert("로긴 성공")
      router.push({path: "/"})
    })
    .catch((err) => {
      alert("로긴 실패")
    })
  }
  
const logoutUser = () => {  // 로그아웃 기능
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


//   const video = ref({})

//   const getVideo = (id) => { // 특정 비디오 id로 조회
//     axios.get(API_VIDEO + `/video/${id}`)
//     .then((res) =>{
//       console.log(video.value)
//       video.value = res.data
//     })
//     .catch((err) => console.log(err))
//   }

//   const videoList = ref([]) // 비디오 목록

//   const getVideoList = () => { // 비디오 목록 받아오기
//     axios.get(API_VIDEO + '/video')
//     .then((res) =>{
//       videoList.value = res.data
//     })
//     .catch((err) => console.log(err))
//   }


//   const favoriteList = ref([ // 
//     {
//       userID: "123", 
//       videoID:[
//         "999",
//       ],
//     },{
//       userID: "123", 
//       videoID:[
//         "123",
//       ],
//     }
//   ])

//   const getFavoriteList = () => {
    // axios.get(API_VIDEO + '/favorite')
    // .then((res) =>{
    //   favoriteList.value = res.data
    // })
    // .catch((err) => console.log(err))
    
//   }

//     const searchVideoList = (searchCondition) => {  // 검색조건으로 비디오 조회
//       axios.get(API_VIDEO + "/video/search", {
//         params: searchCondition
//     })
//     .then((res) => {
//       videoList.value = res.data
//     }).catch((err) => {
//       console.log(err)
//     })
// }


  
  return { newUser, registUser, user, loginUser, logoutUser}
})
