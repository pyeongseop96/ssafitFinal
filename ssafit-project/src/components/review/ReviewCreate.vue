<template>
    <div>
        <br>
    <div class="form-floating mb-3">
        <input type="text" class="form-control" id="floatingInput" placeholder="제목">
        <label for="floatingInput">제목</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingInputGrid" placeholder="내용">
        <label for="floatingInputGrid">내용</label>
      </div>

    <span class="border-bottom"></span>
    <div>
        <form action="#">
            <br>
            <button onclick="location.href='./리뷰 목록 화면.html'" type="button" class="shadow btn btn-outline-primary">등록</button>
            <button onclick="location.href='./리뷰 목록 화면.html'" type="button" class="shadow btn btn-outline-danger">취소</button>
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