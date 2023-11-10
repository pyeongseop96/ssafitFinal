<template>
    <div>
        <div class="buttons">
        <form action="#">
          <button onclick="location.href='./리뷰 등록 화면.html'" type="button" class="shadow btn btn-outline-primary">글 작성</button>
        </form>
        <form action="#">
          <input class="form-control" placeholder="🔎제목, 내용으로 검색">
        </form>
    </div>
    <hr>
    <!--게시판 목록 작성 https://getbootstrap.kr/docs/5.2/content/tables/ 참고-->
    <table class="table table-striped">
        <div class="flex-container">
            <div><th>번호</th></div>
            <div><th>제목</th></div>
            <div><th>작성자</th></div>
            <div><th>조회수</th></div>
            <div><th>작성시간</th></div>
        </div>
        <div class="flex-container">
          
          <tr >
                <div><td>123</td></div>
                <div><td>와! 효과만점 운동 영상입니다.</td></div>
                <div><td>a</td></div>
                <div><td>123</td></div>
                <div><td>2023-08-11 13:36</td></div>
            </tr>     
        </div>
       
    </table>
    <footer>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              <li class="page-item disabled">
                <a class="page-link">Previous</a>
              </li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#">Next</a>
              </li>
            </ul>
          </nav>
    </footer>
    
    {{ store.reviews }}

    <span class="border-bottom"></span>
    </div>
</template>

<script setup>
import {ref} from 'vue'
import {useReviewStore} from '@/stores/review'
import { onMounted } from 'vue';

onMounted(() => {
  store.getReviewList()
})

const store = useReviewStore()


</script>

<style scoped>
.youtubev{
            display: flexbox;
            justify-content: center;
        }
        p{ margin-top: 40px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;}
        .btn-outline-danger {margin-right: 35px;}
        .buttons {display: flex; justify-content: space-between;}
        .form-control{    box-sizing: border-box; list-style: none;}
        .flex-container > div:nth-child(1){
            flex-grow: 1;
        }
        .flex-container > div:nth-child(2){
            flex-grow: 10;
        }
        .flex-container > div:nth-child(5){
            flex-grow: 1;
        }
</style>