<template>
    <div>
        일기쓰기
        <input type="month" v-model="store.month">
    </div>
    <RecordUpdate v-if="store.showUpdate" @closeModal="closeUpdate" />
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
            {{ tagByDate(-start + row * 7 + col+1) }}
        </div>
        </td>
  </tr>
        
    </table>
    <div>
        누른곳 출력공간<br>
        날짜:{{ store.month }}-{{ day }}<br>
        <!-- smile값 나중에 바뀌면 따라 바꿀것 -->
        <div v-if="tagByDate(day)!='smile'">
        -내용-<br>
        <div v-if="weightInfo!=''">체중:{{ weightInfo[0] }}</div>
        <div v-if="eatCalInfo!=''">섭취 칼로리:{{ eatCalInfo[0] }}</div>
        <div v-if="burnCalInfo!=''">소모 칼로리:{{ burnCalInfo[0] }}</div>
        <div v-if="textInfo!=''">내용:{{ textInfo[0] }}</div>
        </div>
        <!-- smile값 나중에 바뀌면 따라 바꿀것 -->
        <div v-if="tagByDate(day)=='smile'">작성된 내용이 없어요!</div>
        <button @click="openUpdate(`${store.month}-${day}`,sessionStorage.data)">내용 변경</button>
        <button @click="clickDeleteButton">내용 삭제</button>
</div>
</template>

<script setup>
import { ref, computed, onMounted, watch, toRaw } from 'vue'; 
import {useRecordStore} from '@/stores/record'
import RecordUpdate from '../components/record/RecordUpdate.vue';

//임시로 로그인한척
import { useSessionStore } from '@/stores/store'
 const sessionStorage = useSessionStore();
 //임시끝

const store = useRecordStore();

const day = ref('')
const weightInfo = ref('')
const eatCalInfo = ref('')
const burnCalInfo = ref('')
const textInfo = ref('')

const out = function(out){
    if(out<=0 || out > lastDate.value){
        return
    }
    if (out >= 1 && out <= 9) {
    day.value = `0${out}`;
  } else {
    day.value = out;
  }
  if(store.records.length>0){
  weightInfo.value = store.records.filter(record =>record.recordDate.split(' ')[0]==`${store.month}-${day.value}`).map(record => record.weight)
  eatCalInfo.value = store.records.filter(record =>record.recordDate.split(' ')[0]==`${store.month}-${day.value}`).map(record => record.eatCal)
 burnCalInfo.value = store.records.filter(record =>record.recordDate.split(' ')[0]==`${store.month}-${day.value}`).map(record => record.burnCal)
  textInfo.value = store.records.filter(record =>record.recordDate.split(' ')[0]==`${store.month}-${day.value}`).map(record => record.text)
  }
}

    const date = ref(new Date());
    store.month = ref(`${date.value.getFullYear()}-${date.value.getMonth()+1}`);
    //선택한 달의 막날
    const lastDate = computed(() => new Date(store.month.slice(0,4),store.month.slice(5,7),0).getDate())

    //선택한 달의 첫날이 무슨요일
    const start = computed(() => {const slicedMonth = new Date(store.month.slice(0,4),store.month.slice(5,7)-1,1);
  return slicedMonth.getDay();})


  onMounted(() => {
    store.getRecordList(`${store.month}-01`,sessionStorage.data)
    out(new Date().getDate())
})

watch(() => store.month, (newMonth) => {
    store.getRecordList(`${newMonth}-01`,sessionStorage.data)
})

const clickDeleteButton = (()=>{
    store.deleteRecord(`${store.month}-${day.value}`,sessionStorage.data);
    setTimeout(() => {
    store.getRecordList(`${store.month}-01`, sessionStorage.data);
  }, 100);
})

//누른 날짜의 상세 기록, tagByDate로 모두 대체(기능 다 만들고 필요없어보이면 삭제)
// const RecordByDate = ref('')

// watch(() => day.value, (newDay) => {
//     if(store.records==[]){
//         RecordByDate.value = ''
//     }
//     else{
//     RecordByDate.value = store.records.filter(record =>record.recordDate.split(' ')[0]==`${month.value}-${newDay}`)
//     if(RecordByDate.value.length==0){
//         RecordByDate.value = ''
//     }
//     console.log(RecordByDate.value)
// }
// })

// watch(() => store.records, (newRecords) => {
//     if(newRecords==[]){
//         RecordByDate.value = ''
//     }
//     else{
//     RecordByDate.value = store.records.filter(record =>record.recordDate.split(' ')[0]==`${month.value}-${day.value}`)
//     if(RecordByDate.value.length==0){
//         RecordByDate.value = ''
//     }
// }
// })


//캘린더에서 tag출력
const tagByDate = ((num) => {
    const tmp = ref(num)
    if(num<10){
        tmp.value = `0${num}`
    }
    const tag = ref('')
    if(store.records.length>0){

     tag.value = store.records.filter(record =>record.recordDate.split(' ')[0]==`${store.month}-${tmp.value}`).map(record => record.tag)
    }

    //달력 표시, @@나중에 image경로로 바꿀 것@@
    if(tag.value==''){
        return "smile"
    }
    else if (tag.value=='bad') {
        return "sad"
    } else if (tag.value=='good'){
        return "happy"
    }

    return tag
})

const openUpdate = (recordDate, userID) => {
  store.recordDate = recordDate;
  store.userID = userID;
  store.showUpdate = true;

};

const closeUpdate = () => {
  store.showUpdate = false;
};
    
</script>

<style scoped>
    table{
        width: 400px;
        height: 400px;
    }
</style>