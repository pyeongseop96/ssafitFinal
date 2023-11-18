import { createRouter, createWebHistory } from 'vue-router'
// 홈 화면
import HomeView from '@/views/HomeView.vue'
// 비디오
import VideoView from '@/views/VideoView.vue'
//운동기록
import RecordView from '@/views/RecordView.vue'
//칼로리계산기
import CalorieView from '@/views/CalorieView.vue'
import CalorieBurnCalculator from '@/components/calorie/CalorieBurnCalculator.vue'
import CalorieEatCalculator from '@/components/calorie/CalorieEatCalculator.vue'
// 마이페이지
import MypageView from '@/views/MypageView.vue'
import MyProfile from '@/components/mypage/MyProfile.vue'
import UpdateProfile from '@/components/mypage/UpdateProfile.vue'
import FavChannelList from '@/components/favorite/FavChannelList.vue'
import FavVideoList from '@/components/favorite/FavVideoList.vue'
// 유저 로그인 및 회원가입
import UserView from '@/views/UserView.vue'
import UserLogin from '@/components/user/UserLogin.vue'
import UserRegist from '@/components/user/UserRegist.vue'

// 리뷰
import ReviewCreate from '@/components/review/ReviewCreate.vue'
import ReviewList from '@/components/review/ReviewList.vue'
import ReviewUpdate from '@/components/review/ReviewUpdate.vue'
import ReviewView from '@/views/ReviewView.vue'
// 카카오맵
import MapView from '@/views/MapView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { // 홈 화면
      path: '/',
      name: 'homeView',
      component: HomeView
    },
    { // 비디오
      path: '/video',
      name: 'video',
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
    { // 리뷰
      path: '/review',
      name: 'review',
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
          component: FavChannelList,
        },
        {
          path: 'favvideo',
          name: 'favoriteVideo',
          component: FavVideoList,
        },
      ]
    },
    { // 지도
      path: '/map',
      name: 'map',
      component: MapView,
    },
    
  ]
})

export default router
