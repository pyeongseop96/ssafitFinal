import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const RecordURL = 'http://localhost:8080/api-record'

export const useRecordStore = defineStore('record', () => {
  const month = ref('')
  const records = ref([])
  const showUpdate = ref(false)

  const getRecordList = function (recordDate, userID) {
    axios.get(RecordURL + `/recordAll?recordDate=${recordDate}&userID=${userID}`)
      .then((response) => {
        console.log(response)
      records.value = response.data
      })
      .catch(function (error) {
        console.log(error);
        showModal.value = false;
      });
  }

  const deleteRecord = function (recordDate, userID) {
    axios.delete(RecordURL + `/record?recordDate=${recordDate}&userID=${userID}`)
      .then((response) => {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error);
      });
  }



  return {month, RecordURL, records, getRecordList, deleteRecord}
})

