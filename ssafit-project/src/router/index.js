import { createRouter, createWebHistory } from 'vue-router'
import ReviewCreate from '@/components/review/ReviewCreate.vue'
import ReviewDetail from '@/components/review/ReviewDetail.vue'
import ReviewList from '@/components/review/ReviewList.vue'
import ReviewUpdate from '@/components/review/ReviewUpdate.vue'
import ReviewView from '@/views/ReviewView.vue'
import TheHeaderView from '@/components/common/TheHeaderNav.vue'
import HomeView from '@/views/HomeView.vue'
import VideoView from '@/views/VideoView.vue'
import RecordView from '@/views/RecordView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'homeView',
      component: HomeView
    },

    {
      path: '/video',
      name: 'videoView',
      component: VideoView,
    },

    {
      path: '/record',
      name: 'recordView',
      component: RecordView,
    },
    
  
    {
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
          path: 'detail',
          name: 'reviewDetail',
          component: ReviewDetail,
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
