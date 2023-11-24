<template>
    <div>
        <v-container fluid>
    <v-row>
        <v-col class="pa-4 cell" cols="12" md="6">
            <v-card class="question">
    <br><span>1. 성별을 입력해주세요:</span><br><br>
    <div>
      <img src="src/img/calorie/man.png" alt="남성 이미지">
      <img src="src/img/calorie/woman.png" alt="여성 이미지">
    </div>
    <br>
    <div>
        <v-btn
        outlined
        color="primary"
        @click="setGender(66.47)"
        :class="{ 'primary--text': gender === 66.47 }"
      >
        남성
      </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <v-btn
        outlined
        color="primary"
        @click="setGender(665.1)"
        :class="{ 'primary--text': gender === 665.1 }"
      >
        여성
      </v-btn>
    </div><br>
  </v-card>
        </v-col>

        <v-col class="pa-4 cell" cols="12" md="6">
            <v-card class="question">
    <br><span>2. 체중을 입력해주세요:</span><br><br><br>
    <div>
        <input placeholder="60" class="text-end" id="weight" v-model="weight" type="number" style="font-size: 2em;">kg
    </div>
    <br>
  </v-card>
        </v-col>

        <v-col class="pa-4 cell" cols="12" md="6">
            <v-card class="question">
    <br><span>3. 키를 입력해주세요:</span><br><br><br>
    <div>
        <input placeholder="170" class="text-end" id="height" v-model="height" type="number" style="font-size: 2em;">cm
    </div>
    <br>
  </v-card>
        </v-col>

        <v-col class="pa-4 cell" cols="12" md="6">
            <v-card class="question">
    <br><span>4. 나이를 입력해주세요:</span><br><br><br>
    <div>
        <input placeholder="20" class="text-end" id="age" v-model="age" type="number" style="font-size: 2em;">세
    </div>
    <br>
  </v-card>
        </v-col>
     
  
        <v-col class="pa-4 cell" cols="12" md="12">
            <v-card class="question">
    <br><span>5. 운동 빈도를 선택해주세요:</span><br><br>
    <div>
      <img src="src/img/calorie/sleep.png" alt="자는 사람">
      <img src="src/img/calorie/walk.png" alt="걷는 사람">
      <img src="src/img/calorie/exercise.png" alt="뛰는 사람">
      <img src="src/img/calorie/athlete.png" alt="무림 고수">
    </div>
    <br>
    <div>
        <v-btn class="frequency"
        outlined
        color="primary"
        @click="setExercise(0.2)"
      >
      운동 하지 않음
      </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <v-btn
        outlined
        color="primary"
        @click="setExercise(0.375)"
  
      >
      주 1~2회 운동
      </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <v-btn
        outlined
        color="primary"
        @click="setExercise(0.55)"

      >
      주 3~5회 운동
      </v-btn>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <v-btn
        outlined
        color="primary"
        @click="setExercise(0.725)"

      >
      주 6~7회 운동
      </v-btn>
    </div><br>
  </v-card>
        </v-col>
   
        
   

    <v-col class="pa-4 text-center" cols="12">
        <v-btn class="cal" @click="cal" style="width: 250px;">계산</v-btn>
        </v-col>
    </v-row>
        </v-container>

        <div v-show="result!=0">
            <div>
    <h1 class="result-heading">다이어트 칼로리: {{ result.toFixed(1) }} kcal</h1>
  </div>

  
<div class="freqButton">
        섭취 영양소 비율 조절&nbsp;&nbsp;<v-btn @click="recom1">영양사 추천 비율</v-btn>&nbsp;&nbsp;
        <v-btn @click="recom2">저탄수 고단백 비율</v-btn><br><br>
</div>
        <v-card style="width: 50%; margin-left: 20px;">
            <br>
        <div>&nbsp;&nbsp;탄수화물<v-slider
        min=0 max=10
      step=1
      color="orange"
      tick-size="4"
      v-model="slider1" @end="add"
    ><template v-slot:append>
          <v-text-field
            v-model="ratio1"
            type="number"
            style="width: 87px"
            density="compact"
            hide-details
            variant="outlined"
            readonly
          ></v-text-field>
        </template></v-slider>
    </div>
    <div>&nbsp;&nbsp;단백질
    <v-slider
        min=0 max=10
      step=1
      color="green"
      tick-size="4"
      v-model="slider2" @end="add"
    ><template v-slot:append>
          <v-text-field
            v-model="ratio2"
            type="number"
            style="width: 87px"
            density="compact"
            hide-details
            variant="outlined"
            readonly
          ></v-text-field>
        </template></v-slider>
    </div>
    <div>&nbsp;&nbsp;지방
    <v-slider
        min=0 max=10
      step=1
      color="blue"
      tick-size="4"
      v-model="slider3" @end="add"
    ><template v-slot:append>
          <v-text-field
            v-model="ratio3"
            type="number"
            style="width: 87px"
            density="compact"
            hide-details
            variant="outlined"
            readonly
          ></v-text-field>
        </template></v-slider>
    </div>
    </v-card>

    <br><div style="margin-left: 20px;">결과</div><br>
  <v-table density="compact" style="width: 50%; margin-left: 20px;">
    <thead>
      <tr>
        <th class="text-left" style="font-weight: bold;">
          영양소
        </th>
        <th class="text-left" style="font-weight: bold;">
          섭취량
        </th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>탄수화물</td>
        <td>{{eat1}}g({{ (eat1*4).toFixed(1) }}kcal)</td>
      </tr>
      <tr>
        <td>단백질</td>
        <td>{{eat2}}g({{ (eat2*4).toFixed(1) }}kcal)</td>
      </tr>
      <tr>
        <td>지방</td>
        <td>{{eat3}}g({{ (eat3*9).toFixed(1) }}kcal)</td>
      </tr>
    </tbody>
  </v-table>

  
    </div>
    </div>
    
</template>

<script setup>
import { ref } from 'vue';
const gender = ref(66.47)
const weight = ref('')
const height = ref('')
const age = ref('')
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
    setTimeout(() => {
        window.scrollTo(0, 10000);
  }, 100);

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

const setGender = function(value) {
      gender.value = value
    }

    const setExercise = function(value) {
      exercise.value = value
    }

</script>

<style scoped>
.pa-4 {
    padding: 1.5rem;

    flex-direction: column;
    align-items: center;
    text-align: center;
  }


    .form-range{
        width: 10%;
    }

    span{
        font-weight: bold;
    }
    img{
        width: 40px;
        margin-right: 5%;
        margin-left: 5%;
    }
  .question{
    height: 200px;
  }
  .text-end{
    width: 100px;
  }
  .result-heading {
  font-family: 'Arial', sans-serif;
  font-size: 2em;
  color: #333;
  text-align: center;
  margin-top: 20px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f8f8f8;
}

.freqButton{
    margin-left: 20px;
}
tr{
  height: 60px;
}
</style>