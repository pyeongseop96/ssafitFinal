<template>
    <div>
        다이어트 칼로리 계산<br>
        성별: <label for="male">남성<input id="male" checked value=66.47 v-model="gender" name="gender" type="radio"></label> | 
        <label for="female">여성<input id="female" value=665.1 v-model="gender" name="gender" type="radio"></label><br>
        <label for="weight">체중: <input id="weight" v-model="weight" type="number">kg</label><br>
        <label for="height">키: <input id="height" v-model="height" type="number">cm</label><br>
        <label for="age">나이: <input id="age" v-model="age" type="number">세</label><br>
        운동빈도: <label for="0week">운동 하지 않음<input checked id="0week" v-model="exercise" value=0.2 name="exercise" type="radio"></label> | 
        <label for="1week">주 1~2회 운동<input id="1week" v-model="exercise" value=0.375 name="exercise" type="radio"></label> | 
        <label for="3week">주 3~5회 운동<input id="3week" v-model="exercise" value=0.55 name="exercise" type="radio"></label> | 
        <label for="6week">주 6~7회 운동<input id="6week" v-model="exercise" value=0.725 name="exercise" type="radio"></label><br>
        <button @click="cal">계산</button><br>
        <h1>다이어트 칼로리 : {{result.toFixed(1)}}kcal</h1>

        섭취 영양소 조절<br>
        <input type="range" class="form-range" min="1" max="10" step="1" v-model="slider1" @change="add">탄수화물{{ratio1}}%<br>
        <input type="range" class="form-range" min="1" max="10" step="1" v-model="slider2" @change="add">단백질{{ ratio2}}%<br>
        <input type="range" class="form-range" min="1" max="10" step="1" v-model="slider3" @change="add">지방{{ratio3 }}%<br>
        추천비율<br>
        <button @click="recom1">영양사 추천 비율</button>
        <button @click="recom2">저탄수 고단백 비율</button>
    
        <h1>결과</h1>
        <h1>탄수화물:{{eat1}}g({{ (eat1*4).toFixed(1) }}kcal)</h1>
        <h1>단백질:{{eat2}}g({{ (eat2*4).toFixed(1) }}kcal)</h1>
        <h1>지방:{{eat3}}g({{ (eat3*9).toFixed(1) }}kcal)</h1>
    </div>
</template>

<script setup>
import { ref } from 'vue';
const gender = ref(66.47)
const weight = ref(0)
const height = ref(0)
const age = ref(0)
const exercise = ref(0.2)
const result = ref(0)
const slider1 = ref(5)
const slider2 = ref(5)
const slider3 = ref(5)
const ratio1 = ref(33.3)
const ratio2 = ref(33.3)
const ratio3 = ref(33.3)
const eat1 = ref('')
const eat2 = ref('')
const eat3 = ref('')
const sum = ref(slider1.value*1 + slider2.value*1 + slider3.value*1)
const add = (() => {
    sum.value = slider1.value*1 + slider2.value*1 + slider3.value*1
    eat1.value = ((((slider1.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat2.value = ((((slider2.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat3.value = ((((slider3.value*1) / (sum.value*1))*result.value)/9).toFixed(1)
    ratio1.value = ((slider1.value*100) / (sum.value*1)).toFixed(1)
    ratio2.value = ((slider2.value*100) / (sum.value*1)).toFixed(1)
    ratio3.value = ((slider3.value*100) / (sum.value*1)).toFixed(1)
})

const cal = (() => {
    if(gender.value==66.47){
        result.value = (gender.value*1 + (13.75 * weight.value) + (5 * height.value) - (6.76 * age.value)) * (1+(exercise.value*1)) - 500
    }
    else{
 
        result.value = (gender.value*1 + (9.56 * weight.value) + (1.85 * height.value) - (4.68 * age.value)) * (1+(exercise.value*1)) - 500
    }
    sum.value = slider1.value*1 + slider2.value*1 + slider3.value*1;
    eat1.value = ((((slider1.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat2.value = ((((slider2.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat3.value = ((((slider3.value*1) / (sum.value*1))*result.value)/9).toFixed(1)
    ratio1.value = ((slider1.value*100) / (sum.value*1)).toFixed(1)
    ratio2.value = ((slider2.value*100) / (sum.value*1)).toFixed(1)
    ratio3.value = ((slider3.value*100) / (sum.value*1)).toFixed(1)
})

const recom1 = (() => {
    slider1.value = 10
    slider2.value = 6
    slider3.value = 4
    sum.value = 20
    eat1.value = ((((slider1.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat2.value = ((((slider2.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat3.value = ((((slider3.value*1) / (sum.value*1))*result.value)/9).toFixed(1)
    ratio1.value = ((slider1.value*100) / (sum.value*1)).toFixed(1)
    ratio2.value = ((slider2.value*100) / (sum.value*1)).toFixed(1)
    ratio3.value = ((slider3.value*100) / (sum.value*1)).toFixed(1)
})

const recom2 = (() => {
    slider1.value = 6
    slider2.value = 8
    slider3.value = 6
    sum.value = 20
    eat1.value = ((((slider1.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat2.value = ((((slider2.value*1) / (sum.value*1))*result.value)/4).toFixed(1)
    eat3.value = ((((slider3.value*1) / (sum.value*1))*result.value)/9).toFixed(1)
    ratio1.value = ((slider1.value*100) / (sum.value*1)).toFixed(1)
    ratio2.value = ((slider2.value*100) / (sum.value*1)).toFixed(1)
    ratio3.value = ((slider3.value*100) / (sum.value*1)).toFixed(1)
})
</script>

<style scoped>
    .form-range{
        width: 10%;
    }
</style>