<template>
    <div>
        <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="제목을 입력하세요">
        <label for="floatingInput">제목</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요">
        <label for="floatingTextarea">내용</label>
      </div>

    <span class="border-bottom"></span>
    <div>
        <form action="#">
            <br>
            <button onclick="location.href='./리뷰 상세 화면.html'" type="button" class="shadow btn btn-outline-primary">수정</button>
            <button onclick="location.href='./리뷰 상세 화면.html'" type="button" class="shadow btn btn-outline-danger">취소</button>
        </form>
    </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
p{ margin-top: 120px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;}
</style>