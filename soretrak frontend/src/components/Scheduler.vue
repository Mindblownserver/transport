<template>
  <DayPilotScheduler :config="config" ref="schedulerRef" />
</template>

<script setup>
import { DayPilot, DayPilotScheduler } from 'daypilot-pro-vue';
import { ref, reactive, onMounted } from 'vue';
import axios from "axios";

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
  const response = await axios.get("http://localhost:8080/api/test");
  console.log(response.data);
  const events = [
    { id: 1, 
      start: DayPilot.Date.today(), 
      end: DayPilot.Date.today().addHours(5), 
      text: "Event 1", 
      resource: "R2" 
    },
    { id: 2, 
      start: DayPilot.Date.today(), 
      end: DayPilot.Date.today().addHours(5), 
      text: "Event 1", 
      resource: "R2" 
    }
  ];
  config.events = events;
};

const loadResources = async () => {
  const res = await axios.get("http://localhost:8080/api/bus")
  console.log(res.data)
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
