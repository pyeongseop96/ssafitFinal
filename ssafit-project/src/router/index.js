import { createRouter, createWebHistory } from 'vue-router'
import ReviewCreate from '@/components/review/ReviewCreate.vue'
import ReviewDetail from '@/components/review/ReviewDetail.vue'
import ReviewList from '@/components/review/ReviewList.vue'
import ReviewUpdate from '@/components/review/ReviewUpdate.vue'
import ReviewView from '@/views/ReviewView.vue'
import TheHeaderView from '@/components/common/TheHeaderNav.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'header',
      component: TheHeaderView
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
