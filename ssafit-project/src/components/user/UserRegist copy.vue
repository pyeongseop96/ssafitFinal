<script setup>

import { useUserStore } from '../../stores/user';

const userStore = useUserStore();

const regist = () => {
    userStore.registUser();
}

(() => {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }

            form.classList.add('was-validated')
        }, false)
    })
})()

</script>


<template>
    <div>
        <form class="row was-validated" novalidate>
            <h1>회원가입</h1>
            <div class="mb-4 row">
                <label for="floatingID" class="col-sm-3 col-form-label">🖊️아이디</label>
                <div class="col-sm-8">
                    <input type="text" id="floatingID" class="form-control is-valid" v-model="userStore.newUser.userID"
                        required>
                    <!-- <div class="valid-feedback">
                                    Looks good!
                                </div> -->
                    <div class="invalid-feedback">
                        Please enter your ID.
                    </div>
                </div>
            </div>
            <div class="mb-4 row">
                <label for="floatingPassword" class="col-sm-3 col-form-label">🔒비밀번호</label>
                <div class="col-sm-8">
                    <input type="password" id="floatingPassword" class="form-control" placeholder="Password"
                    v-model="userStore.newUser.password" required>
                    <div class="invalid-feedback">
                    Please enter your password.
                    </div>
                </div>
            </div>
            <div class="mb-4 row">
                <label for="floatingName" class="col-sm-3 col-form-label">🖊️이름</label>
                <div class="col-sm-8">
                    <input type="text" id="floatingName" class="form-control" placeholder="이름"
                        v-model="userStore.newUser.name" required>
                    <div class="invalid-feedback">
                        Please enter your name.
                    </div>
                </div>
            </div>
            <div class="mb-4 row">
                <label for="floatingEmail" class="col-sm-3 col-form-label">📧이메일</label>
                <div class="col-sm-8">
                    <input type="email" id="floatingEmail" class="form-control" placeholder="name@example.com"
                    v-model="userStore.newUser.email" required>
                    <div class="invalid-feedback">
                        Please enter your e-mail.
                    </div>
                </div>
            </div>
            <div class="mb-4 row">
                <label for="floatingAge" class="col-sm-3 col-form-label">나이</label>
                <div class="col-sm-8">
                    <input type="Number" id="floatingAge" class="form-control" placeholder="Age" v-model="userStore.newUser.age"
                        required>
                    <div class="invalid-feedback">
                        Please enter your age.
                    </div>
                    <div class="valid-feedback">
                    </div>
                </div>
            </div>
            <div class="col-12">
                <!-- <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                            <label class="form-check-label" for="invalidCheck">
                                Agree to terms and conditions
                            </label>
                            <div class="invalid-feedback">
                                You must agree before submitting.
                            </div>
                        </div> -->
            </div>
            <div>
                <a class="btn btn-primary" @click="regist">가입</a>
                <a class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>
</template>

<style scoped>
    h1 {
        margin-bottom: 2rem;
    }

    .col-form-label {
        height: 4rem;
    }

    a {
        width: 5rem;
        margin: 5px;
    }

    form {
        margin: 20px auto;
        display: block;
    }
</style>
