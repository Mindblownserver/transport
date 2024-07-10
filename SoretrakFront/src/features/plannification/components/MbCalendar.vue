<template>
  <MbscEventcalendar
    :view="myView"
    :data="myTrips"
    :resources="myBuses"
    clickToCreate="double"
    :dragToCreate="true"
    :dragToMove="true"
    :dragToResize="true"
    :selectedDate="mySelectedDate"
    @event-click="handleEventClick"
    @event-created="handleEventCreated"
    @event-deleted="handleEventDeleted">
      <template #resource="res">
        <div>
          <img src="../../../assets/images/bus.svg" style="width: 28px;margin-left: 1px;" alt="">
          {{res.id}}</div>
      </template>
      <template #resourceHeader>
      <div class="md-resource-header-template-title">  
        <InputText id="search" v-model="valeurRecherche" placeholder="Chercher par bus" @change="filter()"/>
      </div>
    </template>
    <template #header>
      <MbscCalendarNav />
      <div class="card flex justify-center">
        <SelectButton v-model="value" :options="['Lignes','Bus','Chauffeurs','Receveurs']" aria-labelledby="basic" />
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
import InputText from 'primevue/inputtext';
import SelectButton from 'primevue/selectbutton';
import { Resource, UniqueResourceSet } from '../utils/Resource'
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
import { computed, onMounted, ref, watch } from 'vue'
import {useStore} from "vuex"
import moment from 'moment'

setOptions({
  // locale,
  // theme
  themeVariant: "light",
  theme: "ios"
})
const store = useStore();

const myTrips = ref([])

const valeurRecherche = ref("")

const myBuses = ref([])
let uniqueSet = new UniqueResourceSet();

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

const loadTrips = ()=>{
  const trips = getTrips.value.map((trip,index)=>{
    let bus = new Resource(String(trip.busRe.bus_id),"bus "+ trip.busRe.bus_id, colors[index])
    uniqueSet.add(bus)
    return{
      id: trip.tripsId.trip_id,
      start: moment(trip.timeDepart,"DD/MM/YYYY HH:mm:ss").toDate(),
      end: moment(trip.finalStopTime,"DD/MM/YYYY HH:mm:ss").toDate(),
      text: "trip "+trip.tripsId.trip_id,
      resource:trip.busRe.bus_id,
      title:trip.tripName,
      description: "Description of event Test",
      allDay: false,
      bufferBefore: 0,
      free: false,
    }
  })
  myBuses.value = uniqueSet.values();
  myTrips.value = trips;
  console.debug("Buses=",myBuses.value)
  console.debug("Trips=",myTrips.value)
}

const getLoading = computed(()=>store.state.tripsModule.loading)

const getTrips= computed(()=>{
  const trips= store.state.tripsModule.trips;
  return trips;
})

const filter =()=>{
  let regex = new RegExp(`^${valeurRecherche.value}`)
  myBuses.value = uniqueSet.values().filter(resource => regex.test(resource.id));
}

watch(getLoading, (isLoading)=>{
  if(!isLoading && getTrips.value.length>0){
    loadTrips();
  }
})

onMounted(()=>{
  store.dispatch("tripsModule/getTrips", new Date(2024,3,2,0,0,0,0));
})

/* filter(e){
        this.recipies = this.all_recipies.filter((recipe) => recipe.Tag[0] === e)
        },
    reset(){
        this.recipies = this.all_recipies;
        },
    search(text){
        console.log(text)
        if (text === ""){
            this.recipies = this.all_recipies;
        }
        else{
            this.recipies = this.recipies.filter((recipe) => recipe.title.toLowerCase().includes(text) || recipe.desc.toLowerCase().includes(text));
        }
    } */


// MbScroll settings DO NOT TOUCH
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
    myTrips.value = [...myTrips.value, editedEvent]
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
        myTrips.value = [...myTrips.value, newEvent]
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
        let newEventList = [...myTrips.value]
        const index = newEventList.findIndex((x) => x.id === updatedEvent.id)
        newEventList[index] = updatedEvent
        myTrips.value = newEventList

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
  myTrips.value = myTrips.value.filter((item) => item.id !== event.id)
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
</style>
