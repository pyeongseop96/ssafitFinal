<template>
    <div>
        <header>
            <nav class="navbar navbar-expand-xl bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">
                        <img style="height: 40px;" src="@/img/logo.png" alt="Description of the image">
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                        aria-label="Toggle navigation">

                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav me-auto mb-3 mb-md-0">
                            <li class="nav-item">
                                <RouterLink class="nav-link active" aria-current="page" to="/">Home
                                </RouterLink>
                            </li>

                            <li class="nav-item">
                                <RouterLink class="nav-link active" aria-current="page" to="/video">비디오
                                </RouterLink>
                            </li>

                            <li class="nav-item dropdown">
                                <!-- <RouterLink class="nav-link active" aria-current="page" to="/calorie">칼로리 계산기
                                </RouterLink> -->
                                    <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                        칼로리 계산기
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <RouterLink class="dropdown-item nav-link" aria-current="page"
                                            to="/calorie">다이어트 칼로리 계산</RouterLink>
                                        </li>
                                        <li>
                                            <RouterLink class="dropdown-item nav-link" aria-current="page"
                                            to="/calorie/eat">음식 칼로리 계산</RouterLink>
                                        </li>
                                    </ul>
                            </li>

                            <template v-if="userStore.user.name == null"> <!-- userStore.loginUser.id == null -->
                                <li class="nav-item">
                                    <RouterLink class="nav-link active" aria-current="page" :to="{ name: 'login' }">로그인
                                    </RouterLink>
                                </li>
                                <li class="nav-item">
                                    <RouterLink class="nav-link active" aria-current="page" :to="{ name: 'regist' }">회원가입
                                    </RouterLink>
                                </li>
                            </template>
                            <template v-else>
                                <li class="nav-item">
                                    <RouterLink class="nav-link active" aria-current="page" to="/record">운동기록
                                    </RouterLink>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                        마이페이지
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <RouterLink class="dropdown-item nav-link" aria-current="page"
                                                :to="{ name: 'favoriteVideo' }">찜한 영상</RouterLink>
                                        </li>
                                        <li>
                                            <RouterLink class="dropdown-item nav-link" aria-current="page"
                                                :to="{ name: 'favoriteChannel' }">구독 채널</RouterLink>
                                        </li>
                                        <li>
                                            <RouterLink class="dropdown-item nav-link" aria-current="page"
                                                :to="{ name: 'updateProfile' }">프로필 변경</RouterLink>
                                        </li>
                                    </ul>
                                </li>
                            </template>
                            <li class="nav-item">
                                <RouterLink class="nav-link active" aria-current="page" :to="{ name: 'map' }">주변 헬스장 찾기
                                </RouterLink>
                            </li>
                        </ul>
                        <template v-if="userStore.user.name !== null">
                            <span class="navbar-text">
                                {{ userStore.user.name }}님 환영합니다.
                            </span>
                            <a class="nav-link active" aria-current="page" @click="logout">로그아웃</a>
                        </template>
                    </div>
                </div>
            </nav>
        </header>
        <br>
    </div>
</template>

<script setup>
import { useUserStore } from '../../stores/user';

const userStore = useUserStore();

const logout = () => {
    userStore.logoutUser();
    window.location.reload();
}


</script>

<style scoped>
nav {
    padding: 10px;
}

nav a {
    font-weight: bold;
    text-decoration: none;
    color: black;
    margin: 5px 10px;
}
/* 
nav a.router-link-exact-active {
    color: blue;
} */

 .dropdown-menu {
    background-color: ghostwhite;
}

.dropdown-item:hover {
  background-color: gainsboro;
  color: grey;
  margin: 2px;
  width: calc(100% - 4px);
  border-radius: 4px;
}
</style>