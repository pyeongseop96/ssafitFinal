import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import { useRouter } from 'vue-router'

// const API_VIDEO = 'http://localhost:8080/api-video'
const API_USER = 'http://localhost:8080/api-user'

export const useUserStore = defineStore('user', () => {

  // 한글 깨짐 수정하는 함수 ( BASE64 => UTF-8 변환)
  const b64DecodeUnicode = (str) => {
    // Going backwards: from bytestream, to percent-encoding, to original string.
    return decodeURIComponent(
      atob(str).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));
  }

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
        router.push({ name: "login" })
      })
      .catch((err) => {
        console.log(err)
        alert("가입 실패! 다시 시도해주세요;;")
      })
  }

  let user = ref({ // 현재 로그인한 유저
    userID: '',
    name: '',
    email: '',
    password: '',
    age: 0,
  })

  const auth = (token) => {
    // 서버로 보내서 access토큰 검증하는 작업
    console.log(token)
    axios.put(API_USER + '/auth', token) 
      .then((res) => {
        console.log(res)
        console.log("통과")
      })
      .catch((err) => {
        console.log(err)
        console.log("당신 누구야")
      })

    let payload = JSON.parse(atob(token.split('.')[1]))

    user.value = {
      userID: payload['id'],
      password: '',
      name: b64DecodeUnicode(payload['name']),
      email: payload['email'],
      age: payload['age'],
    }
  }


  const loginUser = (id, password) => { // 유저 로그인 기능
    // 유저 정보 담기
    user.value.userID = id;
    user.value.password = password;

    axios.post(API_USER + '/login', user.value)
      .then((res) => {
        sessionStorage.setItem('access-token', res.data["access-token"])

        const token = res.data["access-token"].split('.');
        let payload = JSON.parse(atob(token[1]))

        user.value = {
          userID: payload['id'],
          password: '',
          name: b64DecodeUnicode(payload['name']),
          email: payload['email'],
          age: payload['age'],
        }


        alert("로그인 성공")
        router.push({ path: "/" })
      })
      // 에러 처리
      .catch((err) => {
        console.log(err)
        user.value.userID = '';
        alert("로그인 실패")
      })
      // 비밀번호 초기화
      .finally(() => { user.value.password = '' })
  }

  const logoutUser = () => {  // 로그아웃 기능
    axios.get(API_USER + "/logout")
      .then((res) => {
        sessionStorage.removeItem('access-token');

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

  const updateUser = (userInfo) => {
    console.log(userInfo)
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



  return { newUser, registUser, user, loginUser, logoutUser, auth, updateUser }
})
