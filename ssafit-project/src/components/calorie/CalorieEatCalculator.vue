<template>
  <v-container style="padding:30px 30px 0px 30px;" fluid>
    <v-row>
      <!-- Search Section -->

          <v-col class="pa-4 cell" cols="12" md="7">
    

    <div>
      <v-text-field v-model="foodStore.word" label="검색" @input="updateWord"></v-text-field>
    </div>
    <v-table>
     
  
<tr v-for="item in foodStore.foods" :key="item.desc_KOR">

  <td>&nbsp;&nbsp;{{ item.desc_KOR }}(1회 제공량 : {{ item.serving_SIZE }}g)&nbsp;&nbsp;
  <span>열량 : {{ item.nutr_CONT1 }}kcal</span>
  <div>
    &nbsp;&nbsp;|<span v-if="item.nutr_CONT2"> 탄수화물:{{ item.nutr_CONT2 }}g |</span>
  <span v-if="item.nutr_CONT3"> 단백질:{{ item.nutr_CONT3 }}g |</span>
  <span v-if="item.nutr_CONT4"> 지방:{{ item.nutr_CONT4 }}g |</span>
  <span v-if="item.nutr_CONT6"> 나트륨:{{ item.nutr_CONT6 }}mg |</span>
  </div>
<div>&nbsp;</div>
  </td>
  <td >
    개수 : <input style="width: 30px;" type="number" v-model="item.cnt" min="1">
  </td>
  <td>
    <button @click="add(item.desc_KOR, item.nutr_CONT1, item.cnt)">추가&nbsp;&nbsp;</button>
  </td>

</tr>
</v-table>
</v-col>
<v-col class="pa-4 cell" ></v-col>

<v-col class="pa-4 cell" cols="12" md="4">
    <v-card>
      <br>
      <h3 style="text-align: center; text-decoration: ;" >{{ sum.toFixed(1) }}kcal</h3>


      <v-table>
      <tr v-for="item in array">
        <td>&nbsp;&nbsp;{{ item.name}} 
        {{ item.cnt}}개&nbsp;</td> 
        <td>{{ item.cal * item.cnt}}kcal</td>
        <td><button @click=remove(item)>삭제&nbsp;&nbsp;</button></td>
        <div>&nbsp;</div><div>&nbsp;</div>
        </tr>
    
      </v-table>

    

    </v-card>
  </v-col>


  </v-row>
  </v-container>

</template>

<script setup>
import { useFoodStore } from '@/stores/food';
import { ref } from 'vue';
const foodStore = useFoodStore();
const array = ref([]);
const sum = ref(0);

// 자음만 있으면 검색안함(가ㄱ)
function isKoreanWithConsonant(input) {
  const pattern = /[ㄱ-ㅎ]+/;
  return pattern.test(input);
}

const updateWord = (event) => {
  const inputValue = event.target.value;
  if (inputValue !== '' && !isKoreanWithConsonant(inputValue)) {
    foodStore.word = inputValue;
    foodStore.getFoodList();
  }
};

const add = ((name, cal, cnt) => {
  if(cnt==undefined){
      cnt = 1
  };
  sum.value = +sum.value +cal * +cnt
  const item = {
      name : name,
      cal : cal,
      cnt : cnt
  }
  array.value.push(item)
})

const remove =((item) => {
  const idx = array.value.indexOf(item)
  sum.value = sum.value - (array.value[idx].cal * array.value[idx].cnt)

  if(array.value.length==1){
      console.log(1230)
      sum.value=0
  }
  array.value.splice(idx,1)
})
</script>

<style scoped>
</style>
