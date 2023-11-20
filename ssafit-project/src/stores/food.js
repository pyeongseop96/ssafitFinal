import { ref } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const FoodURL = 'http://localhost:8080/api-food'

export const useFoodStore = defineStore('food', () => {
  const word = ref('')
  const foods = ref([])

  const getFoodList = function () {
    axios.get(FoodURL + `/food?DESC_KOR=${word.value}`)
      .then((response) => {
        console.log(response)
        foods.value = response.data.map(food => ({ ...food, cnt: 1 }));
      })
      .catch(function (error) {
        console.log(error);
      })
  }

  return {word, getFoodList, foods}
})

