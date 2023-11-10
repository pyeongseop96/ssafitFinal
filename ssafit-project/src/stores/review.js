import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const ReviewURL = 'http://localhost:8080/api-review'

export const useReviewStore = defineStore('review', () => {
  const reviews = ref([])
  const selectedYoutube = ref(null)

  const getReviewList = function () {
    const encodedYoutube = encodeURIComponent(selectedYoutube.value);
    axios.get(ReviewURL+'/reviewAll?videoID='+encodedYoutube)
      .then((response) => {
      reviews.value = response.data
      })
  }

  return { reviews, selectedYoutube,  getReviewList}
})

