<template>
    <div>
      <div class="modal" tabindex="-1" role="dialog" style="display: block;">
    <div class="modal-dialog" role="document">
      <div class="modal-content">  
      <div class="form-floating mb-3">
<<<<<<< HEAD
        <input type="text" class="form-control" id="floatingInput" placeholder="제목을 입력하세요" v-model="tag">
        <label for="floatingInput">태그</label>
=======
        태그
        <div>
        <input v-model="tag" type="radio" id="love" name="tag" value="src/img/record/love.png">
        <label for="love">
          <img src="src/img/record/love.png">
        </label> |

          <input v-model="tag" type="radio" id="happy" name="tag" value="src/img/record/happy.png">
        <label for="happy">
          <img src="src/img/record/happy.png">
          </label> |

          <input v-model="tag" type="radio" id="angry" name="tag" value="src/img/record/angry.png">
          <label for="angry">
          <img src="src/img/record/angry.png">
          </label> | 

          <input v-model="tag" type="radio" id="sleep" name="tag" value="src/img/record/sleep.png">
          <label for="sleep">
          <img src="src/img/record/sleep.png">
          </label> |

          <input v-model="tag" type="radio" id="sad" name="tag" value="src/img/record/sad.png">
          <label for="sad">
          <img src="src/img/record/sad.png">
          </label> |

          <input v-model="tag" type="radio" id="hmm" name="tag" value="src/img/record/hmm.png">
          <label for="hmm">
          <img src="src/img/record/hmm.png">
          </label> |

          <input v-model="tag" type="radio" id="dog" name="tag" value="src/img/record/dog.png">
          <label for="dog">
          <img src="src/img/record/dog.png">
          </label> |

          <input v-model="tag" type="radio" id="pig" name="tag" value="src/img/record/pig.png">
          <label for="pig">
          <img src="src/img/record/pig.png">
          </label>
          </div>
      
>>>>>>> 7a1450d546aa2cd4adff78b0e2a0c6e3b6255db8
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="weight">
        <label for="floatingTextarea">체중:</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="eatCal">
        <label for="floatingTextarea">섭취 칼로리:</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="burnCal">
        <label for="floatingTextarea">소모 칼로리:</label>
      </div>
      <div class="form-floating">
        <input type="text" class="form-control" id="floatingTextarea" placeholder="내용을 입력하세요" v-model="text">
        <label for="floatingTextarea">내용:</label>
      </div>

    <span class="border-bottom"></span>
    <div>
        <form action="#">
            <br>
            <button @click="clickUpdateButton" type="button" class="shadow btn btn-outline-primary">수정</button>
            <button @click="handleClose" type="button" class="shadow btn btn-outline-danger">취소</button>
        </form>
    </div>
    </div>
  </div>
  </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {useRecordStore} from '@/stores/record'
import axios from 'axios'
import { useUserStore } from '@/stores/user';

const id = useUserStore().user.userID;
const tag = ref('')
const weight = ref('')
const eatCal = ref('')
const burnCal = ref('')
const text = ref('')
const store = useRecordStore()
const handleClose = () => {
  store.showUpdate=false
};

const updateRecord = () => {
  const weightValue = weight.value === '' ? '' : `&weight=${weight.value}`;
  const eatCalValue = eatCal.value === '' ? '' : `&eatCal=${eatCal.value}`;
  const burnCalValue = burnCal.value === '' ? '' : `&burnCal=${burnCal.value}`;
  const textValue = text.value === '' ? '' : `&text=${text.value}`;

  axios.put(`${store.RecordURL}/record?recordDate=${store.recordDate}&userID=${store.userID}&tag=${tag.value}${weightValue}${eatCalValue}${burnCalValue}${textValue}`)
    .then(function (response) {
      console.log(response);
      store.showUpdate = false;
    })
    .catch(function (error) {
      console.log(error);
      store.showUpdate = false;
    });
}

  const clickUpdateButton = (()=>{
    updateRecord();
    console.log(store.month)
    setTimeout(() => {
    store.getRecordList(`${store.month}-01`, id);
  }, 100);
})
</script>

<style scoped>
p{ margin-top: 120px; }
        .form-floating { margin: 25px 35px 0px; }
        .btn-outline-primary {margin-left: 35px;}
<<<<<<< HEAD
=======
img{
  width:20px

}
>>>>>>> 7a1450d546aa2cd4adff78b0e2a0c6e3b6255db8
</style>