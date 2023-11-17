import { createRouter, createWebHistory } from 'vue-router'
//리뷰
import ReviewCreate from '@/components/review/ReviewCreate.vue'
import ReviewList from '@/components/review/ReviewList.vue'
import ReviewUpdate from '@/components/review/ReviewUpdate.vue'
import ReviewView from '@/views/ReviewView.vue'
//홈화면
import HomeView from '@/views/HomeView.vue'
//비디오
import VideoView from '@/views/VideoView.vue'
//운동기록
import RecordView from '@/views/RecordView.vue'
//칼로리계산기
import CalorieView from '@/views/CalorieView.vue'
import CalorieBurnCalculator from '@/components/calorie/CalorieBurnCalculator.vue'
import CalorieEatCalculator from '@/components/calorie/CalorieEatCalculator.vue'

// 유저 로그인 및 회원가입
import UserView from '@/views/UserView.vue'
import UserLogin from '@/components/user/UserLogin.vue'
import UserRegist from '@/components/user/UserRegist.vue'
// 마이페이지
import MypageView from '@/views/MypageView.vue'
import MyProfile from '@/components/mypage/MyProfile.vue'
import UpdateProfile from '@/components/mypage/UpdateProfile.vue'

// 카카오맵
import MapView from '@/views/MapView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {//홈 화면
      path: '/',
      name: 'homeView',
      component: HomeView
    },

    {//비디오
      path: '/video',
      name: 'videoView',
      component: VideoView,
    },

    {//운동기록
      path: '/record',
      name: 'recordView',
      component: RecordView,
    },
    {//칼로리계산
      path: '/calorie',
      name: 'calorieView',
      component: CalorieView,
      children: [
        {
          path: '',
          name: 'calorieBurnCalculator',
          component: CalorieBurnCalculator,
        },
        {
          path: 'eat',
          name: 'calorieEatCalculator',
          component: CalorieEatCalculator,
        }
      ]
    },
    { // 유저
      path: '/user',
      name: 'userView',
      component: UserView,
      children: [
        {
          path: '',
          name: 'userLogin',
          component: UserLogin,
        },
        {
          path: 'regist',
          name: 'userRegist',
          component: UserRegist,
        },
      ]
    },
    { // 마이페이지 (프로필, 구독채널, 영상찜)
      path: '/mypage',
      name: 'mypage',
      component: MypageView,
      children: [
        {
          path: '',
          name: 'profile',
          component: MyProfile,
        },
        {
          path: '',
          name: 'updateProfile',
          component: UpdateProfile,
        },
        {
          path: 'favchannel',
          name: 'favoriteChannel',
        },
      ]
    },
 
    { // 지도
      path: '/map',
      name: 'mapView',
      component: MapView,
    },
    { //리뷰
      path: '/review',
      name: 'reviewView',
      component: ReviewView,
      children: [
        {
          path: '',
          name: 'reviewList',
          component: ReviewList,
        },
        {
          path: 'update',
          name: 'reviewUpdate',
          component: ReviewUpdate,
        },
        {
          path: 'create',
          name: 'reviewCreate',
          component: ReviewCreate,
        }
      ]
    },
  ]
})

export default router
