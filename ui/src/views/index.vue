<script setup>
import {onMounted, ref ,nextTick,getCurrentInstance} from 'vue'
import {useRoute} from "vue-router";

const {proxy} =getCurrentInstance()

const activeIndex = ref('/newPost')
const handleSelect = (key, keyPath) => {
  console.log(key, keyPath)
}
const route = useRoute()

onMounted(()=>{
  console.log(route.path)
  if ("/"===route.path){
      nextTick(()=>{
        proxy.$router.push('/newPost')
      })
  }
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <el-menu
            :default-active="activeIndex"
            router
            class="el-menu-horizontal"
            mode="horizontal"
            :ellipsis="false"
            @select="handleSelect"
        >
          <el-menu-item index="/newPost" >
            <div class="logo">
              <el-image src="/vite.svg"/>
            </div>
          </el-menu-item>
          <div class="flex-grow" />
          <el-menu-item index="/newPost">新文章</el-menu-item>
          <el-sub-menu index="2">
            <template #title>Workspace</template>
            <el-menu-item index="2-1">item one</el-menu-item>
            <el-menu-item index="2-2">item two</el-menu-item>
            <el-menu-item index="2-3">item three</el-menu-item>
            <el-sub-menu index="2-4">
              <template #title>item four</template>
              <el-menu-item index="2-4-1">item one</el-menu-item>
              <el-menu-item index="2-4-2">item two</el-menu-item>
              <el-menu-item index="2-4-3">item three</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
        </el-menu>
      </el-header>
      <el-container>
        <el-aside width="200px">Aside</el-aside>
        <el-container>
          <el-main>  <router-view></router-view></el-main>
          <el-footer>Footer</el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.common-layout{
  width: 100%;
  height: 100%;
}
.el-container{
  width: 100%;
  height: 100%;
}
.el-aside{
  width: 230px;
  background: lavender;
}
.el-main{
  min-height: 600px;
}
.el-header{
  padding: 0;
  height: 60px;
}
.el-footer{
  background: #9fc1d7;
}
.flex-grow {
  flex-grow: 1;
}
.el-menu-horizontal{
  width: 100vw;
}
.logo{
  width: 190px;
  display: flex;
  justify-content: center;
}
</style>