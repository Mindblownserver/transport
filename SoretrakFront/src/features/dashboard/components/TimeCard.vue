<template>
    <div class="row mb-5">
        <div class="col-lg-3 col-md-6 col-sm-6">
            <div class="card">
                <div class="card-header p-3 pt-2" style="border-radius: 10px;">
                    <div
                        class="icon icon-lg icon-shape shadow text-center border-radius-xl mt-n4 position-absolute"
                        :class="`bg-gradient-warning shadow-warning`">
                        <i class="material-icons opacity-10 white" aria-hidden="true">schedule</i>
                    </div>
                    <div class="pt-1 text-end">
                        <p class="text-sm mb-0 fw-bold fs-6 text-capitalize" style="color: black;">Choisir le jour du plannification</p>
                    </div>
                    <div class="pt-4">
                        <MbscDatepicker 
                        v-model="defaultDate" :max="maxDate"
                        :controls="['calendar']" 
                        :buttons="['close']"
                        :selectMultiple="false"
                        dateFormat="DD/MM/YYYY" inputStyle="outline" label="Choisir une date" labelStyle="floating"
                        @change="handleDateChange" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</template>
<script setup>
import { MbscDatepicker, setOptions} from '@mobiscroll/vue';
import { ref } from 'vue';
setOptions({
  themeVariant: "light",
  theme: "material"
})
const emits = defineEmits(["Update:date"])

const defaultDate= ref(new Date());
const maxDate = new Date()

const handleDateChange=(args)=>{
    console.debug("Day=",args.value.getDate());
    emits("Update:date", args.value);
}
</script>
