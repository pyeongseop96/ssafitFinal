<template>
    <div>
        일기쓰기
        <input type="month" v-model="month">
        {{ start }}<br>
        {{ month }}<br>
        {{ store.records }}<br>
        {{ day }}
    </div>

    <table>
        <tr>
            <th>일</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
            <th>토</th>
        </tr>
        <tr v-for="row in [0,1,2,3,4,5]" :key="row">
    <td v-for="col in [0,1,2,3,4,5,6]" :key="col" @click="out(-start + row * 7 + col+1)">
        <div v-if="start<=row * 7 + col && -start + row * 7 + col+1 <= lastDate">
            {{-start + row * 7 + col+1 }}
        </div>
        </td>
  </tr>
        
    </table>
    <div>
       <!-- {{ filteredRecords }}숙제 -->
    </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'; 
import {useRecordStore} from '@/stores/record'

//임시로 로그인한척
import { useSessionStore } from '@/stores/store'
 const sessionStorage = useSessionStore();
 //임시끝

const store = useRecordStore();

const day = ref('')

const out = function(out){
    day.value = out
}

    const date = ref(new Date());
    const month = ref(`${date.value.getFullYear()}-${date.value.getMonth()+1}`);
    //선택한 달의 막날
    const lastDate = computed(() => new Date(month.value.slice(0,4),month.value.slice(5,7),0).getDate())

    //선택한 달의 첫날이 무슨요일
    const start = computed(() => {const slicedMonth = new Date(month.value.slice(0,4),month.value.slice(5,7)-1,1);
  return slicedMonth.getDay();})


  onMounted(() => {
    store.getRecordList(`${month.value}-01`,sessionStorage.data)
})

watch(() => month.value, (newMonth) => {
    store.getRecordList(`${newMonth}-01`,sessionStorage.data)
})

//숙제
// const filteredRecords = ref([]);
// watch(() => {
//   filteredRecords.value = store.records.filter(record => {
//     const date = new Date(record.recordDate);
//     return date.getDate() == day.value;
//   });
// });



    
</script>

<style scoped>
    table{
        width: 400px;
        height: 400px;
    }
</style>