<script setup>

import {ref, watch, toRefs} from "vue";

import tinymce from "tinymce/tinymce.min.js"
import 'tinymce/models/dom';

// 外观
import "tinymce/themes/silver/theme.min.js"

// Icon
import 'tinymce/icons/default'

//插件
import 'tinymce/plugins/emoticons';
import 'tinymce/plugins/emoticons/js/emojiimages.min.js';
import 'tinymce/plugins/emoticons/js/emojis.js';
import 'tinymce/plugins/lists';
import 'tinymce/plugins/link';
import 'tinymce/plugins/image';
import 'tinymce/plugins/table';
import 'tinymce/plugins/code';
import 'tinymce/plugins/help/plugin.min.js';
import 'tinymce/plugins/help/js/i18n/keynav/zh_CN.js';
import 'tinymce/plugins/wordcount';
import 'tinymce/plugins/autoresize';
import 'tinymce/plugins/preview'

// 語言包
import 'tinymce-i18n/langs/zh_CN.js'

// TinyMCE-Vue
import Editor from '@tinymce/tinymce-vue'

const props = defineProps({
      modelValue: {
        type: String,
        default: '',
      }
    }
)
const emit = defineEmits(['update:modelValue']);

let contentText = ref('')
let apiKey = ref('6pfsiq4arb6wa8a8cy68yjn4j3wvh318l8i6zxo9ox4q3o5n')
let initOptions = ref({
  language: 'zh_CN',//注意大小写
  plugins: 'autoresize emoticons lists link image table code help wordcount preview',
  inline: false,
  statusbar: false,
  image_upload_handler: (blob, success, failure) => {
    const img = 'data:image/jpeg;base64,' + blob.base64()
    success(img)
  },
  content_style: "img {max-width:100%;height:auto}",
  promotion: false,
  skin_url: "tinymce/skins/ui/oxide",
  content_css: "tinymce/skins/content/default/content.min.css",
  min_height: 600
})

const {modelValue} = toRefs(props);
watch(modelValue, (newValue) => {
  contentText.value = newValue;
});

watch(contentText, (newValue) => {
  emit('update:modelValue', contentText.value);
});


</script>

<template>
  <editor
      :init="initOptions"
      v-model="contentText"
  />

</template>

<style scoped>

</style>