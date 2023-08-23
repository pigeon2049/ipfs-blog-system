<script setup>
import {onMounted, ref, watch} from 'vue'
import  dayjs from 'dayjs'
let content=ref('')
const count = ref(0)

import TinycmeEditor from "./TinycmeEditor/index.vue";

let editorData=ref('')

watch(editorData, (newValue) => {
  console.log(newValue)
});

function submitForm(){
  //todo

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
