<template>
    <div>
        일기쓰기
        <input type="month" v-model="recordStore.month">
    </div>
    <RecordUpdate v-if="recordStore.showUpdate" @closeModal="closeUpdate" />
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
            <div>{{-start + row * 7 + col+1 }}</div>
            <img :src="tagByDate(-start + row * 7 + col+1)">

        </div>
        </td>
  </tr>
        
    </table>
    <div>
        <!-- {{ recordStore.records }} -->
        누른곳 출력공간<br>
        날짜:{{ recordStore.month }}-{{ day }}<br>
        <!-- smile값 나중에 바뀌면 따라 바꿀것 -->
        <div v-if="hasContent()">
        -내용-<br>
        <div v-if="weightInfo!=''">체중:{{ weightInfo[0] }}</div>
        <div v-if="eatCalInfo!=''">섭취 칼로리:{{ eatCalInfo[0] }}</div>
        <div v-if="burnCalInfo!=''">소모 칼로리:{{ burnCalInfo[0] }}</div>
        <div v-if="textInfo!=''">내용:{{ textInfo[0] }}</div>
        </div>
        <!-- smile값 나중에 바뀌면 따라 바꿀것 -->
        <div v-if="!hasContent()">작성된 내용이 없어요!</div>
        <button @click="openUpdate(`${recordStore.month}-${day}`,id)">내용 변경</button>
        <button @click="clickDeleteButton">내용 삭제</button>
</div>
</template>

<script setup>
import { ref, computed, onMounted, watch} from 'vue'; 
import {useRecordStore} from '@/stores/record'
import RecordUpdate from '../components/record/RecordUpdate.vue';
import { useUserStore } from '@/stores/user';

const id = useUserStore().user.userID;
const recordStore = useRecordStore();
const day = ref('')
const weightInfo = ref('')
const eatCalInfo = ref('')
const burnCalInfo = ref('')
const textInfo = ref('')

const hasContent = function(){
    const item = recordStore.records.filter(record =>record.recordDate.split(' ')[0]==`${recordStore.month}-${day.value}`);
    if(item.length>0){
        return true;
    }
    return false;
}

const out = function(out){
    if(out<=0 || out > lastDate.value){
        return; 
    }

    day.value = (out >= 1 && out <= 9) ? `0${out}` : out;

    if(recordStore.records.length > 0){
        const filteredRecords = recordStore.records.filter(record => record.recordDate.split(' ')[0] === `${recordStore.month}-${day.value}`);
        weightInfo.value = filteredRecords.map(record => record.weight);
        eatCalInfo.value = filteredRecords.map(record => record.eatCal);
        burnCalInfo.value = filteredRecords.map(record => record.burnCal);
        textInfo.value = filteredRecords.map(record => record.text);
    }
}

    const date = ref(new Date());
    recordStore.month = ref(`${date.value.getFullYear()}-${date.value.getMonth()+1}`);
    //선택한 달의 막날
    const lastDate = computed(() => new Date(recordStore.month.slice(0,4),recordStore.month.slice(5,7),0).getDate())

    //선택한 달의 첫날이 무슨요일
    const start = computed(() => {const slicedMonth = new Date(recordStore.month.slice(0,4),recordStore.month.slice(5,7)-1,1);
  return slicedMonth.getDay();})


  onMounted(() => {
    recordStore.getRecordList(`${recordStore.month}-01`,id)
    out(new Date().getDate())
})

watch(() => recordStore.month, (newMonth) => {
    recordStore.getRecordList(`${newMonth}-01`,id)
})

const clickDeleteButton = (()=>{
    recordStore.deleteRecord(`${recordStore.month}-${day.value}`,id);
    setTimeout(() => {
        recordStore.getRecordList(`${recordStore.month}-01`, id);
  }, 100);
})


//캘린더에서 tag출력
const tagByDate = ((num) => {
    const tmp = ref(num)
    if(num<10){
        tmp.value = `0${num}`
    }
    const tag = ref('')
    if(recordStore.records.length>0){

     tag.value = recordStore.records.filter(record =>record.recordDate.split(' ')[0]==`${recordStore.month}-${tmp.value}`).map(record => record.tag)
    }
    console.log(tag.value[0])
    //달력 표시, @@나중에 image경로로 바꿀 것@@
    if(tag.value==''){
        return "src/img/record/default.png"
    }
    else if (tag.value=='bad') {
        return "sad"
    } else if (tag.value=='good'){
        return "happy"
    }

    return tag.value[0]
})

watch(() => tagByDate(day), (newTag) => {
    const filteredRecords = recordStore.records.filter(record => record.recordDate.split(' ')[0] === `${recordStore.month}-${day.value}`);
    weightInfo.value = filteredRecords.map(record => record.weight);
    eatCalInfo.value = filteredRecords.map(record => record.eatCal);
    burnCalInfo.value = filteredRecords.map(record => record.burnCal);
    textInfo.value = filteredRecords.map(record => record.text);
});

const openUpdate = (recordDate, userID) => {
    recordStore.recordDate = recordDate;
    recordStore.userID = userID;
    recordStore.showUpdate = true;

};

const closeUpdate = () => {
    recordStore.showUpdate = false;
};
    
</script>

<style scoped>
    table{
        width: 400px;
        height: 400px;
    }
    img{
        width: 40px;
    }
</style>