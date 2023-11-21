<script setup>
import {RouterLink, RouterView} from 'vue-router'
import TheHeaderNav from './components/common/TheHeaderNav.vue';
import { useUserStore } from './stores/user';
import { onMounted, onUpdated } from 'vue';

const userStore = useUserStore();

onMounted(() => {
    const token = sessionStorage.getItem('access-token');
    if (token !== null) {
        userStore.auth(token);
    }
})

</script>

<template>
  <div>
      <TheHeaderNav/>
      <RouterView/>
  </div>
</template>

<style  scoped>

</style>