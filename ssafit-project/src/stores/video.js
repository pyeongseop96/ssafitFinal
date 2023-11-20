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
  const video = ref({})
  const selectedVideo = ref(null)
  const word = ref('전신');

  //클릭한 영상 조회수 1 늘리기
  const addViewCount = ((videoID) => {
    axios.get(`http://localhost:8080/api-video/video/addViewCnt?videoID=${videoID}`)
  })

  // const getVideoList = function () {

  //   axios.get(VideoURL + word.value, {
  //     headers: {
  //       'access-token': sessionStorage.getItem('access-token')
  //     }
  //   })
  //     .then((response) => {
  //       videos.value = response.data
  //     })
  //     .catch((err) => console.log(err))
  // }

  /////////////////////////////////////////////////////////////////////

  // 비디오 ID로 영상 정보 가져오기
  const getVideo = ((videoID) => {
    axios.get(`http://localhost:8080/api-video//video/${videoID}`)
      .then((res) => {
        video.value = res.data;
      })
      .catch((err) =>  console.log(err))
  })


  // const getFavPartList = function () {

  //   axios.get(API_FAVORITE + "/video", {
  //     headers: {
  //       'access-token': sessionStorage.getItem('access-token')
  //     },
  //     params: {
  //       partInfo: useVideoStore().word,
  //       userID: useUserStore().user.userID
  //     }
  //   })
  //     .then((response) => {
  //       videos.value = response.data
  //     })
  //     .catch((err) => console.log(err))
  // }


  
  //     const favChannels = ref([]);
  //     const getFavChannels = () => {
  //         const id = useUserStore().user.userID;
  //         console.log(id)
  //         axios.put(API_FAVORITE + '/channel', id)
  //         .then((res) => {
  //             favChannels.value = res.data
  //         })
  //         .catch((err) => console.log(err));
  //     }
      
  //     const favVideos = ref([]);
  //     const allVideos = ref([]);
  
  //     const getAllVideos = () => {
  //         const id = useUserStore().user.userID;
  //         axios.put(API_FAVORITE + '/video/all', id)
  //         .then((res) => {
  //             console.log(res)
  //             videos.value = res.data
  //         })
  //         .catch((err) => console.log(err));
  //     }
  
      //찜 토글
      const setFavVideo = (video, favorite) => {
          console.log(video)
          axios.post(API_FAVORITE + '/toggle/video', null, {
              params: {
                  userID: useUserStore().user.userID, // 찜하지 않았면 null값을 전송하고 찜하면 로그인한 유저의 ID 전송
                  videoID: video,
                  isFavorite: favorite,
              }
          })
      }
  
      // const getFavVideos = () => {
      //     axios.put(API_FAVORITE + '/video/favorite', useUserStore().user.userID)
      //     .then((res) => {
      //         console.log(res)
      //         favVideos.value = res.data
      //     })
      //     .catch((err) => console.log(err));
      // }

    
    //   //영상 하나 별점 가져오는 메서드
    //   const getVideoRating = function (videoID) {
    //     axios.get(`http://localhost:8080/api-video/rating?videoID=${videoID}`)
    //     .then((res) => {
    //       console.log(res.data)
    //     })
    //     .catch((err) => console.log(err));
    // }
    // const ratings = ref([])

  //   //모든 영상 별점 가져오는 메서드
  //   const getVideoRatingAll = function () {
  //     axios.get(`http://localhost:8080/api-video/ratingAll`)
  //     .then((res) => {
  //         ratings.value = res.data
  //     })
  //     .catch((err) => console.log(err));
  // }
  





  



  return {addViewCount, setFavVideo, video, getVideo}
})