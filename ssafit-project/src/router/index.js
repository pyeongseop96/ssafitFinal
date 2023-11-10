import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'

// 비디오
import VideoView from '@/views/VideoView.vue'
import VideoList from '@/components/video/VideoList.vue'
import VideoDetail from '@/components/video/VideoDetail.vue'
import FavoriteView from '@/components/favorite/FavoriteList.vue'

// 유저 로그인 및 회원가입
import UserView from '@/views/UserView.vue'
import UserLogin from '@/components/user/UserLogin.vue'
import UserRegist from '@/components/user/UserRegist.vue'

// import YoutubeView from '@/views/YoutubeView.vue'
// import BoardView from '@/views/BoardView.vue'
// import BoardList from '@/components/board/BoardList.vue'
// import BoardCreate from '@/components/board/BoardCreate.vue'
// import BoardDetail from '@/components/board/BoardDetail.vue'
// import BoardUpdate from '@/components/board/BoardUpdate.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/video',
      name: 'video',
      component: VideoView,
      children: [
        {
          path: '',
          name: 'list',
          component: VideoList,
        },
        {
          path: ':id',
          name: 'detail',
          component: VideoDetail
        },
      ]
    },
    {
      path: '/favorite',
      name: 'favorite',
      component: FavoriteView,
    },
    {
      path: '/user',
      name: 'user',
      component: UserView,
      children: [
        {
          path: '',
          name: 'login',
          component: UserLogin,
        },
        {
          path: 'regist',
          name: 'regist',
          component: UserRegist,
        },
      ]
    },



    // {
    //   path: '/youtube',
    //   name: 'youtube',
    //   component: YoutubeView
    // },
    // {
    //   path: '/board',
    //   name: 'board',
    //   component: BoardView,
    //   children: [
    //     {
    //       path: "",
    //       name: "boardList",
    //       component: BoardList,
    //     },
    //     {
    //       path: "create",
    //       name: "boardCreate",
    //       component: BoardCreate,
    //     },
    //     {
    //       path: ":id",
    //       name: "boardDetail",
    //       component: BoardDetail,
    //     },
    //     {
    //       path: "update",
    //       name: "boardUpdate",
    //       component: BoardUpdate,
    //     },
    //   ]
    // },
  ]
})

export default router
