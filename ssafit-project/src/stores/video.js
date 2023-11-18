import {useRouter} from 'vue-router'
import {ref, computed} from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useUserStore } from './user';

const VideoURL = 'http://localhost:8080/api-video/video/search?key=partInfo&word='
const API_FAVORITE = 'http://localhost:8080/api-favorite'

export const useVideoStore = defineStore('video', () => {
  const router = useRouter();
  const showModal = ref(false)
  const videos = ref([])
  const selectedVideo = ref(null)
  const word = ref('전신');

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

  /////////////////////////////////////////////////////////////////////


  const getFavPartList = function () {

    axios.get(API_FAVORITE + "/video", {
      headers: {
        'access-token': sessionStorage.getItem('access-token')
      },
      params: {
        partInfo: useVideoStore().word,
        userID: useUserStore().user.userID
      }
    })
      .then((response) => {
        videos.value = response.data
      })
      .catch((err) => console.log(err))
  }


  
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
              videos.value = res.data
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
  




  // ////////////////////////////////////////////////////
  // const video = ref({})

  // const getVideo = (id) => {
  //   axios.get(API_VIDEO + `/video/${id}`)
  //     .then((res) => {
  //       console.log(video.value)
  //       video.value = res.data
  //     })
  //     .catch((err) => console.log(err))
  // }


  // const favoriteList = ref([
  //   {
  //     userID: "123",
  //     videoID: [
  //       "999",
  //     ],
  //   }, {
  //     userID: "123",
  //     videoID: [
  //       "123",
  //     ],
  //   }
  // ])

  // const getFavoriteList = () => {
  //   // axios.get(API_VIDEO + '/favorite')
  //   // .then((res) =>{
  //   //   favoriteList.value = res.data
  //   // })
  //   // .catch((err) => console.log(err))

  // }

  // const searchVideoList = (searchCondition) => {
  //   axios.get(API_VIDEO + "/video/search", {
  //     params: searchCondition
  //   })
  //     .then((res) => {
  //       videoList.value = res.data
  //     }).catch((err) => {
  //       console.log(err)
  //     })
  // }

  // const logoutUser = () => {
  //   axios.get(API_USER + "/logout")
  //     .then((res) => {
  //       user = {
  //         userID: '',
  //         name: '',
  //         email: '',
  //         password: '',
  //         age: 0,
  //       }
  //     }).catch((err) => {
  //       console.log(err)
  //     })
  // }



  return { videos, selectedVideo, getVideoList, word, showModal, getVideoList, favChannels, favVideos, getFavChannels, allVideos, getAllVideos, getFavVideos, setFavVideo, getFavPartList}
})