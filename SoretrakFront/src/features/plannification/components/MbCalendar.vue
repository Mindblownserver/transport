<template>
  <MbscEventcalendar
    :view="myView"
    :data="myTripsLocal"
    :resources="myBusesProp"
    :dragToCreate="true"
    :dragToMove="true"
    :dragToResize="true"
    :dragInTime="false"
    @event-click="handleEventClick"
    @event-created="handleEventCreated"
    @event-deleted="handleEventDeleted"
    @event-drag-end="handleEventDragEnd"
    @selected-date-change="handleDateChange"
    className="md-timeline-template">
      <template #day="day">
        <div class="md-date-header-day">
          {{ formatDate('DD/MM/YYYY', day.date) }}
        </div>
      </template>
      <template #hour="day">
        <div class="md-date-header-hour">
          {{ formatDate('HH:mm', day.date) }}
        </div>
      </template>
      <template #resource="res">   
        <div v-if="resourceModeLocal=='Agents'" class="md-resource-header-template-cont">
          <div class="md-resource-header-template-name">
            <img :src="getIcon.chauff" style="width: 24px;margin-left: 1px;" alt="">
              {{res.chauffId}}
          </div>
          <div class="md-resource-header-template-seats">
            <img :src="getIcon.recev" style="width: 32px;margin-left: 1px;" alt="">
            {{ res.recId}}
          </div>
        </div>
        <div v-else class="md-resource-header-template-cont">
            <div class="md-resource-header-template-name">
              <img :src="getIcon" style="width: 24px;margin-left: 1px;" alt="">
                {{res.id}}
            </div>
          </div>
      </template>
      <template #resourceHeader>
      <div class="md-resource-header-template-title">  
        <InputText id="search" v-model="searchValueLocal" placeholder="Chercher" @input="filter()"/>
      </div>
    </template>
    <template #header>
      <div class="custom-header-left">
        <MbscCalendarNav/>
        <div class="left-criteria">
          <SearchCriteriaInput :critLbl="'Centre: '" :data="props.centreProp"
          @update:criteria-query="filterCentre"/>
          <SearchCriteriaInput :critLbl="'Délégation: '" :data="props.delegProp"
          @update:criteria-query="filterDeleg"/>
        </div>
        <div class="small-empty-box"></div>
        <div class="card" style="flex-shrink: 0;">
          <SelectButton 
          v-model="resourceModeLocal" 
          :options="['Lignes','Bus','Agents']" 
          aria-labelledby="basic" 
          @change="checkSelection()" 
          v-tooltip.bottom="'Choisir le type des resources à afficher'" />
        </div>
        <div class="small-empty-box"></div>
        <div>
          <DatePicker style="width: 8rem;" v-model="timeDebutLocal" fluid timeOnly :maxDate="timeArriveLocal" placeholder="Temps de Depart"></DatePicker>
        </div>
        <Button label="" disabled text>
          <template #icon>
            <i class="material-icons-round opacity-10 fs-5">arrow_forward</i>
          </template>
        </Button>
        <div>
          <DatePicker style="width: 8rem;" v-model="timeArriveLocal" fluid timeOnly placeholder="Temps d'arrivé" :minDate="timeDebutLocal"></DatePicker>
        </div>
        <div class="small-empty-box"></div>
        <div>
          <SplitButton label="Chercher" :loading="loading" @click="searchByTime" :model="items">
            <template #dropdownicon><!-- icon is called a slot, this is how slots are used -->
              <i class="material-icons-round opacity-10 fs-5">filter_alt</i>
            </template>
            <template #icon><!-- icon is called a slot, this is how slots are used -->
              <i class="material-icons-round opacity-10 fs-5">search</i>
            </template>
            <template #item="{item}">
              <span class="dropDownItem" :class="selectedSearchByTimeMode==item.itemId?'selected-dropDownItem':''">{{ item.label }}</span>
            </template>
          </SplitButton>
        </div>
      </div>
      <div class="custom-header-right">
        <Button label="Ajouter trip" severity="info">
          <template #icon>
            <i class="material-icons-round opacity-10 fs-5">add</i>
          </template>
        </Button>
      </div>
      </template>
    <template #resourceEmpty>
      <div class="mds-resource-filtering-empty mbsc-flex mbsc-align-items-center">
        <div class="mbsc-flex-1-1">
          <img
            src=""
            alt="Empty list"
            style="width: 100px"
          />
          <p class="mbsc-font mbsc-margin mbsc-medium mbsc-italic mbsc-txt-muted">
            No resources match your search.
          </p>
          <p class="mbsc-margin mbsc-medium mbsc-italic mbsc-txt-muted">
            Adjust your filters or try a different keyword.
          </p>
        </div>
      </div>
    </template>
    <template #scheduleEvent="data">
      <div
        class="md-timeline-template-event" :style="{ background: data.color}"
        :class="data.original.directionId==0 ? 'aller' : 'retour'">
        <div class="md-timeline-template-event-cont">
          
          <span class="md-timeline-template-title">{{ data.original.title }}</span>
          <span class="md-timeline-template-time">{{data.start}} - {{ data.end }}</span>
        </div>
      </div>
    </template>
  </MbscEventcalendar>  
  
  <MbPopup ref="myPopup" @cancelEventCreation="deleteEvent"/>
</template>


<script setup>
import Button from 'primevue/button';
import SplitButton from 'primevue/splitbutton';
import InputText from 'primevue/inputtext';
import SelectButton from 'primevue/selectbutton';
import DatePicker from 'primevue/datepicker';
import SearchCriteriaInput from "./SearchCriteriaInput"
import MbPopup from './MbEventPopup.vue';
import {
  MbscEventcalendar,
  MbscCalendarNav,
  setOptions /* localeImport */
} from '@mobiscroll/vue'
import {ref, computed, watch} from 'vue'
import { formatDate } from '@mobiscroll/vue';

const emits = defineEmits(["update:criteria-query","update:search-query","update:resource-mode", "update:date"])

const props = defineProps({

  myTripsProp:{
    type:Array,
    required:true
  },
  myBusesProp:{
    type:Array,
    required:true
  },
  centreProp:{
    type:Array,
    required:true
  },
  delegProp:{
    type:Array,
    required:true
  }
})

const timeDebutLocal = ref(new Date())
const timeArriveLocal = ref(new Date())
const searchValueLocal = ref("");
const resourceModeLocal = ref("Bus");
const myTripsLocal = ref(props.myTripsProp) // the problem is here. Temporary fix is setting this into toRefs(prop, "myTripsProp")
let previousResourceValue="Bus";
const loading = ref(false);

const myPopup = ref()

let selectedCentreCrit=[];
let selectedDelegCrit=[];

const items=[
  {
    itemId:0,
    label: "Resource libre",
    command: ()=>{
      selectedSearchByTimeMode.value = 0
    }
  },
  {
    itemId:1,
    label: "Resource occupée",
    command: ()=>{
      selectedSearchByTimeMode.value = 1;
    }
  }
]
const selectedSearchByTimeMode = ref(0)

const searchByTime = () => {
    loading.value = true;
    setTimeout(() => {
        emits("update:search-by-time-query", timeDebutLocal.value,timeArriveLocal.value, selectedSearchByTimeMode.value);loading.value=false;
    }, 400);
};

const getIcon = computed(()=>{
  if(resourceModeLocal.value == "Bus"){
    return require('../../../assets/images/bus.svg')
  }
  else if(resourceModeLocal.value=="Lignes")
    return require("../../../assets/images/lignes.svg")
  else
    return {
      recev: require("../../../assets/images/receveur.svg"),
      chauff: require("../../../assets/images/busDriver.svg")
    };
})

const filter= ()=>{
  emits("update:search-query", searchValueLocal.value);
}

const checkSelection =()=>{
  if(resourceModeLocal.value==null)
    resourceModeLocal.value = previousResourceValue
  else{
    previousResourceValue = resourceModeLocal.value;
    emits("update:resource-mode", resourceModeLocal);
  }
}

const filterDeleg =(selectedCrit)=>{
  selectedDelegCrit = selectedCrit;
  emits("update:criteria-query", selectedCentreCrit, selectedDelegCrit);
}

const filterCentre =(selectedCrit)=>{
  selectedCentreCrit = selectedCrit;
  emits("update:criteria-query", selectedCentreCrit, selectedDelegCrit);
}

setOptions({
  themeVariant: "light",
  theme: "material"
})

const myView = {
  timeline: {
    type: 'day',
    timeCellStep: 30,
  }
}

function handleEventClick(args) {
  myPopup.value.createEditPopup(args.event, args.domEvent.currentTarget,resourceModeLocal.value)
}

function handleEventCreated(args) {
  myPopup.value.createAddPopup(args.event, args.target,resourceModeLocal.value)
}

function deleteEvent(event) {
  myTripsLocal.value = myTripsLocal.value.filter((item) => item.id !== event.id)
}

function handleEventDeleted(args) {
  deleteEvent(args.event)
  myPopup.value.isPopupOpen.value = false
}

function handleEventDragEnd(args){
  if(resourceModeLocal.value=="Bus")
    args.event.busPrId = args.resource;
  else if(resourceModeLocal.value == "Ligne")
    args.event.ligneId = args.resource;
  else{
    const chauffRecArr = args.resource.split("|")
    args.event.recPr = chauffRecArr[1];
    args.event.chauffPr = chauffRecArr[0]
  }
}

function handleDateChange(args){
  if(args.date){
    console.log(args.date.getDate())
    emits("update:date", args.date);
  }
}

// Pas à rejeter
watch(()=>props.myTripsProp, (newValue)=>{
  myTripsLocal.value = newValue;
  console.log(myTripsLocal.value)
})

</script>

<style>

.md-resource-header-template-title{
  display: inline-block;

}
#search{
  width: 160px;
  margin-left: 5px;
}
.custom-header-left{
  display: flex;
  justify-content: flex-start;
  width: 100%;
}
.custom-header-right{
  display: flex;
  justify-content: flex-end;
  flex-shrink: 0;
}

.md-resource-header-template-cont {
  line-height: 50px;
  height: 100%;
}

.md-resource-header-template-name {
  height: 100%;
  display: inline-block;
  padding: 0 5px;
}

.md-resource-header-template-seats {
  border-left: 1px solid #ccc;
  width: 90px;
  height: 100%;
  line-height: 63px;
  float: right;
  padding: 0 5px;
  position: absolute;
  top: 0;
  right: 0;
}

.md-date-header-hour {
    font-size: 12px;
    font-weight: 600;
    text-align: center;
    line-height: 20px;
    border-radius: 8px;
    margin: 3px;
    color: #f8f8f8;
    background-color: var(--simple-soretrak-color);
}
.md-date-header-day{
  font-weight: 600;
  /* padding-top: 10px;
  padding-bottom: 10px; */
}

.small-empty-box{
  width:25px
}

.highlight-time {
  background-color: #f0c674;
  color: #000;
}

.dropDownItem{
  margin-left: 10px;
  font-weight: bolder;
}
.selected-dropDownItem{
  color: var(--simple-soretrak-color);
}


.md-timeline-template .mbsc-schedule-event.mbsc-ltr {
  height: auto !important;
}

.md-timeline-template-event {
  border: 1px solid transparent;
  margin: 2px 0;
}

.md-timeline-template-event-cont {
  font-size: 12px;
  height: 32px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  display: flex;
  color: white;
  flex-direction: column;
  padding-left: 5px;
}


.md-timeline-template-time {
  margin: 0 5px;
  font-size: 10px;
  color: rgb(236, 236, 236);
}

.md-timeline-template .mbsc-timeline-column,
.md-timeline-template .mbsc-timeline-header-column {
  min-width: 100px;
}

.md-timeline-template .mbsc-timeline-resource,
.md-timeline-template .mbsc-timeline-row {
  min-height: 100px;
}
.aller{
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;

}
.retour{
  border-top-left-radius: 20px;
  border-bottom-left-radius: 20px; 
}
.left-criteria{
  display: flex;
  align-items: flex-end;
}

</style>
