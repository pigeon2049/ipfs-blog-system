<script setup>
import { ref, watch} from 'vue'
const count = ref(0)

import TinycmeEditor from "./TinycmeEditor/index.vue";
import {postBlog} from "../api/postBlog.js";
import {ElNotification} from "element-plus";

let editorData=ref('')

watch(editorData, (newValue) => {
  console.log(newValue)
});

function submitForm(){
  //todo
  postBlog(editorData.value).then(res=>{
    ElNotification({
      title: 'Title',
      message: res.data.data,
    })
  })
}


</script>

<template>
  <div class="myEditor">

    <tinycme-editor v-model="editorData"></tinycme-editor>
    <el-button @click="submitForm">提交</el-button>


  </div>
</template>

<style scoped>
.myEditor{
  min-width: 60vw;
}
.el-button{
  margin-top: 5px;
}
</style>
