import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import {createRouter, createWebHashHistory} from "vue-router";

import NewPost from "../src/components/NewPost.vue";

const app = createApp(App)

const routes = [
    { path: '/', component: NewPost },
    { path: '/newPost', component: NewPost },

]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})
app.config.globalProperties.$router=router
app.use(router)
app.use(ElementPlus)
app.mount('#app')
