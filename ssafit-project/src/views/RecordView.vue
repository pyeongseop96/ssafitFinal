<template>
<v-container fluid>
    <v-row>
      <!-- Left side with buttons and print area -->
      <v-col class="pa-4 cell" cols="12" md="6">
        <v-card elevation="16">
            <div class="date-picker-container">
    <v-btn icon @click="decrementMonth" variant="text">&lt;</v-btn>
    <input type="month" v-model="recordStore.month">
    <v-btn icon @click="incrementMonth" variant="text">&gt;</v-btn>
  </div><br>
 
    <v-simple-table>
        <tr>
            <th style="color: red;">일</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
            <th>토</th>
        </tr>
        <tr v-for="row in [0,1,2,3,4,5]" :key="row">
    <td v-for="col in [0,1,2,3,4,5,6]" :key="col" @click="out(-start + row * 7 + col+1)">
        <div class="cell" v-if="start<=row * 7 + col && -start + row * 7 + col+1 <= lastDate">
            <v-btn icon="$vuetify" variant="text" :style="{ color: col === 0 ? 'red' : 'black' }">{{-start + row * 7 + col+1 }}</v-btn>
            <img :src="tagByDate(-start + row * 7 + col+1)">

        </div>
        </td>
  </tr>
        
    </v-simple-table>
</v-card>
    </v-col>
    <v-col class="pa-4" cols="12" md="6">
    <div class="print">
        <RecordUpdate v-if="recordStore.showUpdate" @closeModal="closeUpdate" />

        <v-card elevation="16">
            <div class="date-picker-container">
    <v-btn icon @click="decrementDay" variant="text">&lt;</v-btn>
    <div>{{ recordStore.month }}-{{ day }}</div>
    <v-btn icon @click="incrementDay" variant="text">&gt;</v-btn>
  </div>
  <div class="content">
        <div v-if="hasContent()">
        <br>
        <div v-if="weightInfo!=''">체중 : {{ weightInfo[0] }}kg<br><br></div>
        <div v-if="eatCalInfo!=''">섭취 칼로리 : {{ eatCalInfo[0] }}kcal <br><br></div>
        <div v-if="burnCalInfo!=''">소모 칼로리 : {{ burnCalInfo[0] }}kcal <br><br></div>
        <div v-if="textInfo!=''">내용 : {{ textInfo[0] }} <br><br></div>
        </div>
        <!-- smile값 나중에 바뀌면 따라 바꿀것 -->
        <div v-if="!hasContent()">작성된 내용이 없어요!</div>
        </div>
        <div class="changeButton">
        <v-btn @click="openUpdate(`${recordStore.month}-${day}`,id)">내용 변경</v-btn>&nbsp;
        <v-btn @click="clickDeleteButton" color="pink">내용 삭제</v-btn>
        </div>
        </v-card>
</div>
</v-col></v-row></v-container>
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
    if(recordStore.records.length==0){
        return false;
    }
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

const decrementMonth = () => {
  const currentMonth = new Date(recordStore.month);
  currentMonth.setMonth(currentMonth.getMonth() - 1);
  recordStore.month = formatMonth(currentMonth);
};

const incrementMonth = () => {
  const currentMonth = new Date(recordStore.month);
  currentMonth.setMonth(currentMonth.getMonth() + 1);
  recordStore.month = formatMonth(currentMonth);
};

const formatMonth = (date) => {
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  return `${year}-${month}`;
};

const decrementDay = () => {
  updateDay(-1);
};

const incrementDay = () => {
  updateDay(1);
};

const updateDay = (change) => {
  const currentDate = new Date(`${recordStore.month}-${day.value}`);
  currentDate.setDate(currentDate.getDate() + change);
  out(currentDate.getDate()); // Assuming you have an out function to update the day
};
    
</script>

<style scoped>

.pa-4 {
    padding: 1.5rem;
  }
    table{
        width: 400px;
        height: 400px;
    }
    img{
        width: 40px;
    }
    td{
        width: 100px;
        height: 100px;
    }
    th {
        text-align: center;
        
    }
  .cell {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  .date-picker-container {
    margin-top: 20px;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }
  .changeButton{
    margin-left: 10px;
    margin-bottom: 10px;
  }
  .content{
    margin-left: 30px;
    margin-bottom: 15px;
  }
</style>