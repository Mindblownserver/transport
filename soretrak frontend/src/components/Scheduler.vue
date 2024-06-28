<template>
  <DayPilotScheduler :config="config" ref="schedulerRef" />
</template>

<script setup>
import { DayPilot, DayPilotScheduler } from 'daypilot-pro-vue';
import { ref, reactive, onMounted } from 'vue';
import axios from "axios";
import moment from "moment";

const instance = axios.create({
  baseURL: 'http://localhost:8080',
  transformResponse: [function (data) {
    // Parse JSON response with date reviver function
    return JSON.parse(data, (key, value) => {
      if (typeof value === 'string' && value.match(/^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}.\d{3}[+-]\d{2}:\d{2}$/)) {
        return moment.utc(value).toDate(); // Convert to JavaScript Date object using Moment.js
      }
      return value;
    });
  }],
});


const config = reactive({
  startDate: DayPilot.Date.today(),
  days: 1, // Display only one day
  timeHeaders: [
    { groupBy: "Day", format: "d/M/yyyy"},
    { groupBy: "Hour", format: "HH:mm" },
    { groupBy: "Cell", format: "mm" }
  ],
  scale: "CellDuration",
  cellDuration: 10,
  timeRangeSelectedHandling: "Enabled",
  onTimeRangeSelected: async (args) => {
    const dp = args.control;
    const modal = await DayPilot.Modal.prompt("Create a new event:", "Event 1");
    dp.clearSelection();
    if (modal.canceled) { return; }
    dp.events.add({
      start: args.start,
      end: args.end,
      id: DayPilot.guid(),
      resource: args.resource,
      text: modal.result
    });
  },
  eventMoveHandling: "Update",
  onEventMoved: (args) => {
    args.control.message("Event moved: " + args.e.text());
  },
  eventResizeHandling: "Update",
  onEventResized: (args) => {
    args.control.message("Event resized: " + args.e.text());
  },
  eventDeleteHandling: "Update",
  onEventDeleted: (args) => {
    args.control.message("Event deleted: " + args.e.text());
  },
  eventClickHandling: "Disabled",
  eventHoverHandling: "Bubble",
  onResourceClick: (args) => {
    console.log("Resource clicked: " + args.resource.name);
  },
  bubble: new DayPilot.Bubble({
    onLoad: (args) => {
      // if the event object doesn't specify "bubbleHtml" property
      // this onLoad handler will be called to provide the bubble HTML
      args.html = "Event details";
    }
  }),
  treeEnabled: true,
});
const schedulerRef = ref(null);

const loadEvents = async () => {
  const response = await instance.get("/api/trips/10");
  //console.log(response.data[0].finalStopTime instanceof Date);
  let trips = response.data.map(trip=>({
    id: trip.tripsId.trip_id,
    start: DayPilot.Date.today().addHours(trip.timeDepart.getHours()).addMinutes(trip.timeDepart.getMinutes()),
    end: DayPilot.Date.today().addHours(trip.timeDepart.getHours()+ trip.finalStopTime.getHours()).addMinutes(trip.timeDepart.getMinutes()+ trip.finalStopTime.getMinutes()),
    text: "trip "+trip.tripsId.trip_id,
    resource:trip.busPr.bus_id
  }));
  /* const events = [
    { id: 1, 
      start: DayPilot.Date.today(), 
      end: DayPilot.Date.today().addHours(5), 
      text: "Event 1", 
      resource: "R2" 
      DayPilot.Date(
      trip.finalStopTime.getFullYear(), 
      trip.finalStopTime.getMonth() + 1, 
      trip.finalStopTime.getDate(), 
      trip.finalStopTime.getHours(), 
      trip.finalStopTime.getMinutes(), 
      trip.finalStopTime.getSeconds()
    ),
    },
    { id: 2, 
      start: DayPilot.Date.today(), 
      end: DayPilot.Date.today().addHours(5), 
      text: "Event 1", 
      resource: "R2" 
    }
  ]; */
  config.events = trips;
};

const loadResources = async () => {
  const res = await axios.get("http://localhost:8080/api/bus")
  const resources = res.data.map(bus =>({
    name: "bus "+ bus.bus_id,
    id: bus.bus_id
  }))
  config.resources = resources;
};

onMounted(() => {
  loadResources();
  loadEvents();
  schedulerRef.value?.control.message("Welcome!");
});
// Vehicule
// matricule,  
</script>
