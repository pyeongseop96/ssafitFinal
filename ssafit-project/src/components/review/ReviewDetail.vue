<template>
    <div>
        <div class="buttons">
        <form action="#">
          <button onclick="location.href='./리뷰 목록 화면.html'" type="button" class="shadow btn btn-outline-primary">목록</button>
        </form>
        <form action="#">
            <button onclick="location.href='./리뷰 수정 화면.html'" type="button" class="shadow btn btn-outline-primary">글수정</button>
        </form>
    </div>
    <hr>
    <span class="border"></span>
    <div class="container my-5">
      <div class="shadow p-5 text-center bg-body-tertiary rounded-3">
        <blockquote class="blockquote">
          <p><h1 class="text-body-emphasis"
         font-family="Nanum Myeongjo">와! 효과만점 운동 영상입니다.</h1></p>
        </blockquote>
        
          <figcaption class="blockquote-footer">
            작성자 : a<br>작성일 : 2023-08-11 13:36<br>조회수 : 123
          </figcaption>
        <hr>
        <div class="text-center">
        강추강추!!
      </div>
    </div> 
    <span class="border-bottom"></span>
    </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
p{ margin-top: 40px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;margin-right: 35px;}
        .btn-outline-danger {margin-right: 35px;}
        .buttons {display: flex; justify-content: space-between;}
        .form-control{display: inline-flexbox;}
        .bg-body-tertiary{background-color: rgb(230, 235, 241);}
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@400;700&display=swap');
        #fontt{font-family: "Nanum Myeongjo";}
</style>