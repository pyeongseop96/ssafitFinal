import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const ReviewURL = 'http://localhost:8080/api-review'

export const useReviewStore = defineStore('review', () => {
  const showModal = ref(false)
  const showUpdate = ref(false)
  const reviews = ref([])
  const selectedYoutube = ref(null)
  const videoID = ref('');
  const reviewID = ref('');

  const getReviewList = function () {
    axios.get(ReviewURL+'/reviewAll?videoID='+ videoID.value)
      .then((response) => {
      reviews.value = response.data
      })
  }

  const createReview = function (content, title, userID, videoID, rating) {
    axios.post(ReviewURL+`/review?content=${content.value}&regDate=0&reviewCnt=${rating.value}&reviewID=0&title=${title.value}&userID=${userID.value}&videoID=${videoID}`)
    .then(function (response) {
      console.log(response);
      getReviewList();
      showModal.value = false;
    })
    .catch(function (error) {
      console.log(error);
      showModal.value = false;
    });
  }

  const updateReview = function (content, title, reviewID) {
    axios.put(ReviewURL+`/review?content=${content}&regDate=0&reviewCnt=0&reviewID=${reviewID}&title=${title}&userID=aa&videoID=aa`)
    .then(function (response) {
      console.log(response);
      getReviewList();
      showUpdate.value = false;
    })
    .catch(function (error) {
      console.log(error);
      showUpdate.value = false;
    });
  }

  const deleteReview = function (id) {
    axios.delete(ReviewURL+`/review/${id}`)
    .then(function (response) {
      getReviewList();
    })
  }

  return { reviewID, reviews,showUpdate, selectedYoutube,deleteReview,updateReview,  getReviewList, videoID, showModal, createReview}
})

