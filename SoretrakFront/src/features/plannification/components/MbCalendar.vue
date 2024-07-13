<template>
  <MbscEventcalendar
    :view="myView"
    :data="myTripsProp"
    :resources="myBusesProp"
    clickToCreate="double"
    :dragToCreate="true"
    :dragToMove="true"
    :dragToResize="false"
    :dragInTime="false"
    :selectedDate="mySelectedDate"
    @event-click="handleEventClick"
    @event-created="handleEventCreated"
    @event-deleted="handleEventDeleted">
      <template #day="day">
        <div class="md-date-header-day">
          {{ formatDate('DD/MM/YYYY', day.date) }}
        </div>
      </template>
      <template #hour="day">
        <div class="md-date-header-hour">
          {{ formatDate('hh:mm A', day.date) }}
        </div>
      </template>
      <template #resource="res">
         <div class="md-resource-header-template-cont">
        <div class="md-resource-header-template-name">
          <img :src="getIcon" style="width: 24px;margin-left: 1px;" alt="">
          {{res.id}}
        </div>
        <div v-if="resourceModeLocal=='Agents'" class="md-resource-header-template-seats">{{ res.name}}</div>
      </div>
      </template>
      <template #resourceHeader>
      <div class="md-resource-header-template-title">  
        <InputText id="search" v-model="searchValueLocal" placeholder="Chercher par bus" @input="filter()"/>
      </div>
    </template>
    <template #header>
      <div class="custom-header-left">
        <MbscCalendarNav />
        <div class="empty-box"></div>
        <div class="card" style="flex-shrink: 0;">
          <SelectButton 
          v-model="resourceModeLocal" 
          :options="['Lignes','Bus','Agents']" 
          aria-labelledby="basic" 
          @change="checkSelection()" 
          v-tooltip.bottom="'Choisir le type des resources à afficher'" />
        </div>
        <div class="empty-box"></div>
        <div>
          <DatePicker v-model="timeDebutLocal" fluid timeOnly placeholder="Temps de Depart"></DatePicker>
        </div>
        <Button label="" disabled text>
          <template #icon>
            <i class="material-icons-round opacity-10 fs-5">arrow_forward</i>
          </template>
        </Button>
        <div>
          <DatePicker v-model="timeArriveLocal" fluid timeOnly placeholder="Temps d'arrivé"></DatePicker>
        </div>
        <div class="small-empty-box"></div>
        <div>
          <Button label="Submit" :loading="loading" @click="load">
            <template #icon><!-- icon is called a slot, this is how slots are used -->
              <i class="material-icons-round opacity-10 fs-5">search</i>
            </template>
          </Button>
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
  </MbscEventcalendar>  
  
  <MbscPopup
    display="bottom"
    :contentPadding="false"
    :fullScreen="true"
    :isOpen="isPopupOpen"
    :responsive="myResponsive"
    :anchor="popupAnchor"
    :buttons="popupButtons"
    :headerText="popupHeaderText"
    @close="handlePopupClose"
  >
    <div class="mbsc-form-group">
      <MbscInput label="Title" v-model="popupEventTitle" />
      <MbscTextarea label="Description" v-model="popupEventDescription" />
    </div>
    <div class="mbsc-form-group">
      

      <MbscInput ref="startInput" label="Starts" />
      <MbscInput ref="endInput" label="Ends" />
      <!-- <template v-if="!popupEventAllDay">
        <MbscDropdown v-model="popupTravelTime" label="Travel time">
          <option value="0">None</option>
          <option value="5">5 minutes</option>
          <option value="15">15 minutes</option>
          <option value="30">30 minutes</option>
          <option value="60">1 hour</option>
          <option value="90">1.5 hours</option>
          <option value="120">2 hours</option>
        </MbscDropdown>
      </template> -->
      <MbscDatepicker
        v-model="popupEventDates"
        select="range"
        :controls="popupEventAllDay ? datePickerControls : datetimePickerControls"
        :responsive="popupEventAllDay ? datePickerResponsive : datetimePickerResponsive"
        :startInput="startInput"
        :endInput="endInput"
      />
      <div ref="colorElm" class="event-color-c" @click="openColorPicker($event)">
        <div class="event-color-label">Color</div>
        <div class="event-color" :style="{ background: popupEventColor }"></div>
      </div>
      <MbscSegmentedGroup v-model="popupEventStatus">
        <MbscSegmented value="busy" v-model="popupEventStatus">Show as busy</MbscSegmented>
        <MbscSegmented value="free" v-model="popupEventStatus">Show as free</MbscSegmented>
      </MbscSegmentedGroup>
      <div v-if="isEdit" class="mbsc-button-group">
        <MbscButton
          cssClass="mbsc-button-block"
          color="danger"
          variant="outline"
          @click="handleDeleteClick"
          >Delete event
        </MbscButton>
        <MbscSnackbar
          :button="snackbarButton"
          message="Event deleted"
          :isOpen="isSnackbarOpen"
          @close="handleSnackbarClose"
        />
      </div>
    </div>
  </MbscPopup>

  <MbscPopup
    ref="colorPopup"
    display="bottom"
    :anchor="colorAnchor"
    :contentPadding="false"
    :showArrow="false"
    :showOverlay="false"
    :buttons="colorButtons"
    :responsive="colorResponsive"
    :isOpen="isColorPickerOpen"
  >
    <div class="crud-color-row">
      <div v-for="(color, i) in colors" :key="color.value">
        <div
          v-if="i < 5"
          class="crud-color-c"
          :class="{ selected: tempColor === color }"
          :data-value="color"
          @click="handleColorClick($event)"
        >
         <!-- <span>{{ i }}</span> -->  <!-- This lets us add text to colors -->
        <!-- Useful when attributing color to specific types, not necessairly existant in database -->
        
          <div
            class="crud-color mbsc-icon mbsc-font-icon mbsc-icon-material-check"
            :style="{ background: color }"
          ></div>
        </div>
      </div>
    </div>
    <div class="crud-color-row">
      <div v-for="(color, i) in colors" :key="color.value">
        <div
          v-if="i >= 5"
          class="crud-color-c"
          :class="{ selected: tempColor === color }"
          :data-value="color"
          @click="handleColorClick($event)"
        >
          <div
            class="crud-color mbsc-icon mbsc-font-icon mbsc-icon-material-check"
            :style="{ background: color }"
          ></div>
        </div>
      </div>
    </div>
  </MbscPopup>
</template>


<script setup>
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import SelectButton from 'primevue/selectbutton';
import DatePicker from 'primevue/datepicker';
import {
  MbscButton,
  MbscDatepicker,
  MbscEventcalendar,
  MbscInput,
  MbscPopup,
  MbscSegmented,
  MbscSegmentedGroup,
  MbscSnackbar,
  MbscTextarea,
  MbscCalendarNav,
  setOptions /* localeImport */
} from '@mobiscroll/vue'
import {ref,defineProps,defineEmits, computed} from 'vue'
import { formatDate } from '@mobiscroll/vue';

const emits = defineEmits(["update:search-query","update:resource-mode"])

const props = defineProps({

  myTripsProp:{
    type:Array,
    required:true
  },
  myBusesProp:{
    type:Array,
    required:true
  }
})

const timeDebutLocal = ref("")
const timeArriveLocal = ref("")
const searchValueLocal = ref("");
const resourceModeLocal = ref("Bus");
const myTripsLocal = ref(props.myTripsProp)
let previousResourceValue="Bus";
const loading = ref(false);

const load = () => {
    loading.value = true;
    setTimeout(() => {
        loading.value = false;
    }, 2000);
};

const getIcon = computed(()=>{
  if(resourceModeLocal.value == "Bus"){
    return require('../../../assets/images/bus.svg')
  }
  else if(resourceModeLocal.value=="Lignes")
    return require("../../../assets/images/lignes.svg")
  else
    return "";
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

setOptions({
  // locale,
  // theme
  themeVariant: "light",
  theme: "material"
})

// MbScroll settings DO NOT TOUCH


// Color picker
let colors = [
  '#ffeb3c',
  "#00ff9f",
  '#ff9900',
  '#f44437',
  '#ea1e63',
  '#9c26b0',
  '#3f51b5',
  "#9f00ff",
  "#ff0060",
  "#00e0ff",
  '#ff6f1d',
  "#005678",
  '#009788',
  '#4baf4f',
  '#7e5d4e'
]

const myView = {
  timeline: {
    type: 'day',
    timeCellStep: 30,
  }
}
const isEdit = ref(false)
const popupEventColor = ref('')
const mySelectedDate = ref()
let addedEvent = null
let editedEvent = null

// Popup
const myResponsive = {
  medium: {
    display: 'anchored',
    width: 400,
    fullScreen: false,
    touchUi: false
  }
}
const popupEventTitle = ref('')
const popupEventDescription = ref('')
const popupEventDates = ref([])
const popupEventAllDay = ref(false)
const popupTravelTime = ref(0)
const popupEventStatus = ref('free')
const popupAnchor = ref(null)
const popupButtons = ref([])
const popupHeaderText = ref('')
const isPopupOpen = ref(false)

// Datepicker
const startInput = ref(null)
const endInput = ref(null)

const datePickerControls = ['date']
const datePickerResponsive = {
  medium: {
    controls: ['calendar'],
    touchUi: false
  }
}
const datetimePickerControls = ['datetime']
const datetimePickerResponsive = {
  medium: {
    controls: ['calendar', 'time'],
    touchUi: false
  }
}

const colorAnchor = ref(null)
const isColorPickerOpen = ref(false)
const tempColor = ref('')
const colorElm = ref(null)
const colorPopup = ref(null)

const colorButtons = [
  'cancel',
  {
    text: 'Set',
    keyCode: 'enter',
    handler: () => {
      popupEventColor.value = tempColor.value
      isColorPickerOpen.value = false
    },
    cssClass: 'mbsc-popup-button-primary'
  }
]

const colorResponsive = {
  medium: {
    display: 'anchored',
    buttons: {}
  }
}

// Snackbar
const isSnackbarOpen = ref(false)
const snackbarButton = {
  action: () => {
    myTripsLocal.value = [...myTripsLocal.value, editedEvent]
  },
  text: 'Undo'
}

// Fills the popup with the event's data
function fillPopup(event) {
  popupEventTitle.value = event.title
  popupEventDescription.value = event.description
  popupEventAllDay.value = event.allDay || false
  popupTravelTime.value = event.bufferBefore || 0
  popupEventDates.value = [event.start, event.end]
  popupEventStatus.value = event.status || 'busy'
  popupEventColor.value = event.color || ''
}

function createAddPopup(event, target) {
  // Hide delete button inside add popup
  isEdit.value = false

  addedEvent = event

  // Set popup header text and buttons
  popupHeaderText.value = 'New event'
  popupButtons.value = [
    'cancel',
    {
      text: 'Add',
      keyCode: 'enter',
      handler: () => {
        const newEvent = {
          id: addedEvent.id,
          title: popupEventTitle.value,
          description: popupEventDescription.value,
          allDay: popupEventAllDay.value,
          bufferBefore: popupTravelTime.value,
          status: popupEventStatus.value,
          start: popupEventDates.value[0],
          end: popupEventDates.value[1],
          color: popupEventColor.value,
          resource: event.resource
        }
        myTripsLocal.value = [...myTripsLocal.value, newEvent]
        mySelectedDate.value = popupEventDates.value[0]
        isPopupOpen.value = false
      },
      cssClass: 'mbsc-popup-button-primary'
    }
  ]
  popupAnchor.value = target

  fillPopup(event)
  isPopupOpen.value = true
}

function createEditPopup(event, target) {
  // Show delete button inside edit popup
  isEdit.value = true

  editedEvent = event
  addedEvent = null

  popupHeaderText.value = 'Edit event'

  // Set popup header text and buttons
  popupButtons.value = [
    'cancel',
    {
      text: 'Save',
      keyCode: 'enter',
      handler: () => {
        const updatedEvent = editedEvent
        updatedEvent.title = popupEventTitle.value
        updatedEvent.description = popupEventDescription.value
        updatedEvent.allDay = popupEventAllDay.value
        updatedEvent.bufferBefore = popupTravelTime.value
        updatedEvent.start = popupEventDates.value[0]
        updatedEvent.end = popupEventDates.value[1]
        updatedEvent.color = popupEventColor.value
        updatedEvent.status = popupEventStatus.value
        // Update event
        let newEventList = [...myTripsLocal.value]
        const index = newEventList.findIndex((x) => x.id === updatedEvent.id)
        newEventList[index] = updatedEvent
        myTripsLocal.value = newEventList

        isPopupOpen.value = false
      },
      cssClass: 'mbsc-popup-button-primary'
    }
  ]
  popupAnchor.value = target
  fillPopup(event)
  isPopupOpen.value = true
}

// Calendar events
function handleEventClick(args) {
  createEditPopup(args.event, args.domEvent.currentTarget)
}

function handleEventCreated(args) {
  createAddPopup(args.event, args.target)
}

function deleteEvent(event) {
  myTripsLocal.value = myTripsLocal.value.filter((item) => item.id !== event.id)
  isSnackbarOpen.value = true
}

function handleEventDeleted(args) {
  deleteEvent(args.event)
  isPopupOpen.value = false
}

function handleDeleteClick() {
  deleteEvent(editedEvent)
}

function handlePopupClose() {
  // Remove event if popup is cancelled
  if (addedEvent) {
    deleteEvent(addedEvent)
  }
  isPopupOpen.value = false
  isColorPickerOpen.value = false
}

function openColorPicker(event) {
  tempColor.value = popupEventColor.value || ''
  colorAnchor.value = event.currentTarget
  isColorPickerOpen.value = true
}

function handleColorClick(event) {
  const color = event.currentTarget.getAttribute('data-value')
  tempColor.value = color

  if (!colorPopup.value.instance.s.buttons.length) {
    popupEventColor.value = color
    isColorPickerOpen.value = false
  }
}

function handleSnackbarClose() {
  isSnackbarOpen.value = false
}


</script>

<style>
.event-color-c {
  display: flex;
  margin: 16px;
  align-items: center;
  cursor: pointer;
}

.event-color-label {
  flex: 1 0 auto;
}

.event-color {
  width: 30px;
  height: 30px;
  border-radius: 15px;
  margin-right: 10px;
  margin-left: 240px;
  background: #5ac8fa;
}

.crud-color-row {
  display: flex;
  justify-content: center;
  margin: 5px;
}

.crud-color-c {
  padding: 3px;
  margin: 2px;
}

.crud-color {
  position: relative;
  min-width: 46px;
  min-height: 46px;
  margin: 2px;
  cursor: pointer;
  border-radius: 23px;
  background: #5ac8fa;
}

.crud-color-c.selected,
.crud-color-c:hover {
  box-shadow: inset 0 0 0 3px #007bff;
  border-radius: 48px;
}

.crud-color:before {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -10px;
  margin-left: -10px;
  color: #f7f7f7;
  font-size: 20px;
  text-shadow: 0 0 3px #000;
  display: none;
}

.crud-color-c.selected .crud-color:before {
  display: block;
}

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

.empty-box{
  width: 46px;
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

</style>
