<template>
    <div>
        <h3>프로필 수정하기</h3>
        <div>
            <p>ID : {{ userStore.user.userID }}</p>
        </div>
        <div>
            <label for="name">비밀번호 : </label>
            <input id="name" @input="event => newPass = event.target.value" v-model="userInfo.password" placeholder="필수 입력">
        </div>
        <div>
            <label for="name">이름 : </label>
            <input type="text" id="name" @input="event => newName = event.target.value" :placeholder="userInfo.name">
        </div>
        <div>
            <label for="name">이메일 : </label>
            <input type="email" id="name" @input="event => newEmail = event.target.value" :placeholder="userInfo.email">
        </div>
        <div>
            <label for="name">나이 : </label>
            <input type="number" id="name" @input="event => newAge = event.target.value" :placeholder="userInfo.age">
        </div>
        <div>
            <button @click="updateProfile">수정하기</button>
        </div>

    </div>
</template>

<script setup>

import { useUserStore } from '../../stores/user';
import { ref, onMounted, } from 'vue';

const userStore = useUserStore();
const userInfo = ref({});
const newPass = ref(null);
const newName = ref(null);
const newEmail = ref(null);
const newAge = ref(null);


onMounted(() => {
    userInfo.value = userStore.user;
})

const updateProfile = () => {

    // userStore.auth() 비밀번호 검증하기

    let fix = confirm("정말 수정할거야?"
    + (newName.value ? ("\n이름 : " + newName.value) : "")
        + (newEmail.value ? ("\n이메일 : " + newEmail.value) : "")
        + (newAge.value > 0 ? ("\n나이 : " + newAge.value) : "")

    )

    if (fix) {
        userStore.updateUser({
            userID: null,
            password: newPass.value ? newPass.value : null,
            name: newName.value ? newName.value : null,
            email: newEmail.value ? newEmail.value : null,
            age: newAge.value ? newAge.value : null,

        });

    }
}

</script>



<style scoped>
    label {
        width: 5rem;
    }
    div {
        padding: 3px;
    }

</style>