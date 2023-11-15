<template>
    <div>
        다이어트 칼로리 계산<br>
        성별: <label for="male">남성<input id="male" value="male" name="gender" type="radio"></label> | 
        <label for="female">여성<input id="female" value="female" name="gender" type="radio"></label><br>
        <label for="weight">체중: <input id="weight" value="weight" type="number">kg</label><br>
        <label for="height">키: <input id="height" value="height" type="number">cm</label><br>
        <label for="age">나이: <input id="age" value="age" type="number">세</label><br>
        운동빈도: <label for="0week">운동 하지 않음<input id="0week" value="0week" name="exercise" type="radio"></label> | 
        <label for="1week">주 1~2회 운동<input id="1week" value="1week" name="exercise" type="radio"></label> | 
        <label for="3week">주 3~5회 운동<input id="3week" value="3week" name="exercise" type="radio"></label> | 
        <label for="6week">주 6~7회 운동<input id="6week" value="6week" name="exercise" type="radio"></label><br>

        <input type="range" class="form-range" min="0" max="10" id="customRange2">
  

        
    </div>
</template>

<script setup>

</script>

<style scoped>
    
</style>