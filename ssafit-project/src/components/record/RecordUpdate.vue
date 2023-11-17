<template>
    <div>
      <div class="modal" tabindex="-1" role="dialog" style="display: block;">
    <div class="modal-dialog" role="document">
      <div class="modal-content">  
      <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="제목을 입력하세요" v-model="tag">
        <label for="floatingInput">태그</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="weight">
        <label for="floatingTextarea">체중:</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="eatCal">
        <label for="floatingTextarea">섭취 칼로리:</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="burnCal">
        <label for="floatingTextarea">소모 칼로리:</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="text">
        <label for="floatingTextarea">내용:</label>
      </div>

    <span class="border-bottom"></span>
    <div>
        <form action="#">
            <br>
            <button @click="clickUpdateButton" type="button" class="shadow btn btn-outline-primary">수정</button>
            <button @click="handleClose" type="button" class="shadow btn btn-outline-danger">취소</button>
        </form>
    </div>
    </div>
  </div>
  </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {useRecordStore} from '@/stores/record'
import axios from 'axios'
import { useUserStore } from './user';

const id = useUserStore().user.userID;
const tag = ref('')
const weight = ref('')
const eatCal = ref('')
const burnCal = ref('')
const text = ref('')
const store = useRecordStore()
const handleClose = () => {
  store.showUpdate=false
};

const updateRecord = () => {
    axios.put(store.RecordURL + `/record?recordDate=${store.recordDate}&userID=${store.userID}&tag=${tag.value}&weight=${weight.value}&eatCal=${eatCal.value}&burnCal=${burnCal.value}&text=${text.value}`)
    .then(function (response) {
      console.log(response);
      store.showUpdate = false;
    })
    .catch(function (error) {
      console.log(error);
      store.showUpdate = false;
    });
  }

  const clickUpdateButton = (()=>{
    updateRecord();
    console.log(store.month)
    setTimeout(() => {
    store.getRecordList(`${store.month}-01`, id);
  }, 100);
})
</script>

<style scoped>
p{ margin-top: 120px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;}
</style>