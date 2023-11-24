<template>

<v-row style="background-color: #80CEE1; height: 40; margin-top: 40px;">
      <v-col cols="12" md="4" style=" margin: 7% 0px 90px 160px;">
        <img src="@/img/logo_blue.png">
      </v-col>
      <v-col cols="12" md="6" style=" margin: 8% 0px 60px 60px;">
        <h1>운동 도우미 SSAFIT이</h1><br>
        <h1>여러분의 건강 증진을 도와드립니다</h1>
      </v-col>
    </v-row>


    <v-row style="margin: 100px auto;">
      <v-col class="pa-4 cell" cols="12" md="4">
        <v-card>
          <img
            style=" width: 100%; height: 10%"
            src="@/img/home1.jpg"
          />
          <v-card-text>
            <h5 class="title font-weight-medium mb-2 text-h6">
              운동 유튜브 시청
            </h5>
            <p class="mb-3 text-body-2 text-grey-darken-1">관리자가 선별한 운동 영상들을 만나보세요</p>
            <v-btn depressed color="warning" to="/video">이동하기</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col class="pa-4 cell" cols="12" md="4">
        <v-card>
          <img
            style=" width: 100%; height: 10%"
            src="@/img/home3.jpg"
          />
          <v-card-text>
            <h5 class="title font-weight-medium mb-2 text-h6">
              칼로리 계산기
            </h5>
            <p class="mb-3 text-body-2 text-grey-darken-1">오늘 먹은 음식의 칼로리, 권장 섭취 칼로리를 알려드립니다</p>
            <v-btn depressed color="warning" to="/calorie">이동하기</v-btn>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col class="pa-4 cell" cols="12" md="4">
        <v-card>
          <img
            style=" width: 100%; height: 10%"
            src="@/img/home2.jpg"
          />
          <v-card-text>
            <h5 class="title font-weight-medium mb-2 text-h6">
              헬스장 찾기
            </h5>
            <p class="mb-3 text-body-2 text-grey-darken-1">현재 위치에서 가까운 헬스장을 찾아드립니다</p>
            <v-btn depressed color="warning" to="/map">이동하기</v-btn>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>



    
  </template>

<script setup>


</script>

<style>
</style>