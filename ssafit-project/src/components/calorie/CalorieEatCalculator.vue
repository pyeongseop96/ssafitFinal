<template>
    <div>
      음식 칼로리 계산
  
      <div>
        검색:<input type="text" :value="store.word" @input="updateWord">
      </div>
      <table>
        <tr v-for="item in store.foods">
          <td>{{ item.desc_KOR }}(1회 제공량:{{ item.serving_SIZE }}g)</td>
          <td>열량:{{ item.nutr_CONT1 }}kcal</td>
          <td>탄수화물:{{ item.nutr_CONT2 }}g</td>
          <td>단백질:{{ item.nutr_CONT3 }}g</td>
          <td>지방:{{ item.nutr_CONT4 }}g</td>
          <td>나트륨:{{ item.nutr_CONT6 }}mg</td>
          <td><input type="number" v-model=item.cnt min=1 placeholder="1">개</td>
          <td><button @click="add(item.desc_KOR, item.nutr_CONT1, item.cnt)">추가</button></td>
        </tr>
      </table>
      <br>
      <div>
        결과화면
        <table>
        <tr v-for="item in array">
          <td>{{ item.name}}</td> | 
          <td>{{ item.cnt}}개</td> | 
          <td>{{ item.cal * item.cnt}}kcal</td>
          <td><button @click=remove(item)>삭제</button></td>
        </tr>
        </table>
       <h3 v-if="array.length>0">칼로리 합계 : {{ sum.toFixed(1) }}kcal</h3>
      </div>

    </div>
  </template>
  
  <script setup>
  import { useFoodStore } from '@/stores/food';
  import { ref } from 'vue';
  const store = useFoodStore();
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
      store.word = inputValue;
      store.getFoodList();
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
  