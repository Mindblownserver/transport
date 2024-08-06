<template>
    <label for="critInput">{{props.critLbl}}</label>

    <MultiSelect id="critInput" filter
    v-model="selectedCrit" 
    :options="props.data" optionLabel="name"
    class="w-full md:w-80"
    :loading="isLoading"
    @change="filterCrit()"
    />
</template>

<script setup>
import MultiSelect from 'primevue/multiselect';
import {ref} from "vue";
let emits = defineEmits(["update:criteria-query"])

let props = defineProps({
    critLbl:{
        type:String,
        required:true
    },
    data:{
        type:Array,
        required:true
    },
})

let selectedCrit = ref([]);
let isLoading = ref(false);

const filterCrit =()=>{
    isLoading.value=true;
    setTimeout(()=>{
        emits("update:criteria-query", selectedCrit.value);
        isLoading.value=false;
    }, 400);
}

</script>

<style scoped>
#critInput{
    width: 8rem;
}
label{
    padding-right: 10px;
    padding-left: 20px;
    font-size: 16px;
    font-weight: bold;
}
</style>