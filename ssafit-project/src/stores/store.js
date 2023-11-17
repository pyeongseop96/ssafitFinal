//임시로 로그인한 셈치고 세션에 id정보 넣기
// import { defineStore } from 'pinia';

// export const useSessionStore = defineStore('session', {
//   state: () => ({
//     data: null,
//   }),
//   getters: {
//     getData: (state) => state.data,
//   },
//   actions: {
//     setData(value) {
//       this.data = value;
//       sessionStorage.setItem('id', JSON.stringify(value));
//     },
//     loadSessionData() {
//       const data = sessionStorage.getItem('id');
//       if (data) {
//         this.data = JSON.parse(data);
//       }
//     },
//   },
// });