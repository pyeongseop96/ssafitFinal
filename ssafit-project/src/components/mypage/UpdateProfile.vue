<template>
    <form class="input-group mb-3">
        <h3 class="mb-3">프로필 수정하기</h3>
        <div class="input-group mb-3">
            <span class="input-group-text">ID</span>
            <input type="text" class="form-control" :placeholder="userStore.user.userID" disabled>
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text">기존 비밀번호</label>
            <input type="password" class="form-control" @input="event => oldPass = event.target.value"
                v-model="oldPass" placeholder="필수 입력" required>
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text">새로운 비밀번호 </label>
            <input type="password" class="form-control" @input="event => newPass = event.target.value"
                v-model="newPass">
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text">비밀번호 재입력</label>
            <input type="password" class="form-control" @input="event => newPass2 = event.target.value"
                v-model="newPass2">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">이름</span>
            <input type="text" class="form-control" @input="event => newName = event.target.value"
                v-model="newName">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">이메일</span>
            <input type="email" class="form-control" @input="event => newEmail = event.target.value"
                v-model="newEmail">
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">나이</span>
            <input type="number" class="form-control" @input="event => newAge = event.target.value"
                v-model="newAge">
        </div>
        <div>
            <button class="btn btn-primary" @click="updateProfile">수정하기</button>
        </div>
    </form>
</template>

<script setup>

import { useUserStore } from '../../stores/user';
import { ref, onMounted, } from 'vue';

const userStore = useUserStore();
const userInfo = ref({});
const oldPass = ref(null);
const newPass = ref(null);
const newPass2 = ref(null);
const newName = ref(null);
const newEmail = ref(null);
const newAge = ref(null);

onMounted(() => {
    userInfo.value = userStore.user;
})

const updateProfile = () => {

    let fix = confirm("정말 수정할거야?"
        + (newName.value ? ("\n이름 : " + newName.value) : "")
        + (newEmail.value ? ("\n이메일 : " + newEmail.value) : "")
        + (newAge.value > 0 ? ("\n나이 : " + newAge.value) : "")
    )

    if (fix) {
        if (newPass.value == newPass2.value) {
            userStore.updateUser({
                userID: null,
                password: newPass.value ? newPass.value : null,
                name: newName.value ? newName.value : null,
                email: newEmail.value ? newEmail.value : null,
                age: newAge.value ? newAge.value : null,
            });
        } else {
            alert('비밀번호가 서로 다릅니다');
        }
    }
}



</script>



<style scoped>
.input-group-text {
    width: 9rem;
}

div {
    padding: 3px;
}

form {
    width: 500px;
    text-align: center;
    margin: 20px auto;
    display: block;
}

</style>