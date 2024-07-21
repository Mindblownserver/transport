<template>
    <MbscPopup
        :contentPadding="false"
        :fullScreen="true"
        :isOpen="isPopupOpen"
        :responsive="myResponsive"
        :anchor="popupAnchor"
        :headerText="popupHeaderText"
        :filter="true"
        @close="close()"
    >
        <MbscSelect
        :data="props.resourcesProps" :itemHeight="64"
        display="inline" :filter="true" 
        @change="handleTempChange">
            <template #item="item">
                <div :style="{ color:item.data.text.color }">
                <span v-if="resourceMode=='ligne'|| resourceMode=='bus'">{{item.data.text.id}}</span>
                <span v-else-if="resourceMode=='agent'">
                    <img src="../../../assets/images/busDriver.svg" style="width: 24px;margin-left: 1px;" alt="">
                        {{ item.data.text.chauffId }} | 
                    <img src="../../../assets/images/receveur.svg" style="width: 24px;margin-left: 1px;" alt="">
                        {{item.data.text.recId}}
                </span>
                </div>
            </template>
        </MbscSelect>
  </MbscPopup>
</template>
<script setup>
import { MbscPopup,MbscSelect } from '@mobiscroll/vue';
import { ref } from 'vue';
const emits = defineEmits(["update:busId"])
const props = defineProps({
    resourcesProps:{
        type:Array,
        required:true
    }
})

const popupHeaderText = ref('')
const isPopupOpen = ref(false)
const popupAnchor = ref(null)
const resourceMode = ref("")

const myResponsive = {
  medium: {
    display: 'anchored',
    width: 400,
    fullScreen: false,
    touchUi: false
  }
}

function open(target,mode){
    isPopupOpen.value = true;
    popupAnchor.value=target
    resourceMode.value=mode;
}
function close(){
    isPopupOpen.value= false;
}
function handleTempChange(event){
    console.debug(event.value);
    emits("TempUpdate:resource", event.value.id,resourceMode.value);
    close();
}


defineExpose({
    open,
    close,
})
</script>