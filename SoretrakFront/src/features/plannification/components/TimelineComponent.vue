<template>
    <MbCalendar 
    :myTripsProp="myTrips" 
    :myBusesProp="myResources"
    :centreProp = "centreResourceArray.values()"
    :delegProp = "delegResourceArray.values()"
    @update:search-query="filter" 
    @update:resource-mode="checkSelection"
    @update:search-by-time-query="filerByTime"
    @update:criteria-query="filterByCrit"/>
</template>

<script setup>
import MbCalendar from './MbCalendar.vue';
import { Bus, Ligne, Agent, ResourceArray,ResourceModes } from '../utils/Resource'
import { computed, onMounted, ref, watch } from 'vue'
import {useStore} from "vuex"
import moment from 'moment'
import { TripEvent } from '../utils/TripEvent';
import { DrCentre,DrDeleg } from '../utils/Criteria';


const store = useStore();

const myTrips = ref([])
const myResources = ref([])

const myResourcesCriteria = ref([])
let centreCriteria = [];
let delegCriteria = []

const selectedResourceValue = ref("Bus")
const resourceMode = new ref(new ResourceModes());


let busResourceArray = new ResourceArray();
let ligneResourceArray = new ResourceArray();
let agentResourceArray = new ResourceArray();
let centreResourceArray = new ResourceArray();
let delegResourceArray = new ResourceArray();

// computing values
const getLoading = computed(()=>store.state.tripsModule.loading)

const getTrips= computed(()=>{
  const trips= store.state.tripsModule.trips;
  return trips;
})

// methods
const filter =(newSearchValue)=>{
  let regex = new RegExp(`^${newSearchValue}`)
  myResources.value = myResourcesCriteria.value.filter(resource => regex.test(resource.id));
}

const checkSelection = (newResourceMode)=>{
  selectedResourceValue.value = newResourceMode.value;
  myTrips.value.map(trip=>trip.changeResourceId(newResourceMode.value));
  myResourcesCriteria.value = resourceMode.value.getMode(newResourceMode.value).values()
  //myResources.value = myResourcesCriteria.value
  filterByCrit(centreCriteria,delegCriteria)
}

const filerByTime = (timeDepart,timeArrive, searchByTimeMode)=>{
  const resourcePasLibre = new Set();
  myTrips.value.map(trip=>{
    if (!(compareTime(trip.end,timeDepart)>0 || compareTime(timeArrive, trip.start)>0))
      resourcePasLibre.add(String(trip.resource));
  })
  if(searchByTimeMode==0)
    myResources.value = myResourcesCriteria.value.filter(resource=>!resourcePasLibre.has(resource.id));
  else
    myResources.value = myResourcesCriteria.value.filter(resource=>resourcePasLibre.has(resource.id));
}

/**
 * A method to compare 2 times
 * @returns 1 if dateTime1 is before dateTime2
 * @returns 0 if dateTime1 = dateTime 2
 * @returns -1 if dateTime1 is after dateTime 2
 */
const compareTime = (dateTime1, dateTime2)=>{
  let time1 = moment(`${dateTime1.getHours()}:${dateTime1.getMinutes()}:${dateTime1.getSeconds()}`,"HH:mm:ss");
  let time2 = moment(`${dateTime2.getHours()}:${dateTime2.getMinutes()}:${dateTime2.getSeconds()}`,"HH:mm:ss");
  if(time1.isBefore(time2))
    return 1;
  else if(time1.isAfter(time2))
    return -1;
  else
    return 0;
}

const loadTrips = ()=>{
  const trips = getTrips.value.map((trip)=>{
    const busReFromTrips = trip.busRe;
    const busPrFromTrips = trip.busPr;
    const ligneFromTrips=trip.ligne;
    const recPrFromTrips = trip.recPr;
    const chauffPrFromTrips = trip.chauffPr

    let deleg = new DrDeleg(ligneFromTrips.deleg.decdeleg,ligneFromTrips.deleg.frdeleg,ligneFromTrips.deleg.ardeleg);
    let centre = new DrCentre(ligneFromTrips.centre.deccentre, ligneFromTrips.centre.delcentre,ligneFromTrips.centre.arcentre);

    let bus = new Bus(String(busReFromTrips.bus_id),"bus "+ busReFromTrips.bus_id, busReFromTrips.color)
    let ligne = new Ligne(String(ligneFromTrips.idLigne),ligneFromTrips.nomLigne,ligneFromTrips.priorite,
      ligneFromTrips.dectyta,ligneFromTrips.dectyeq,ligneFromTrips.denomla,ligneFromTrips.detatec,
      ligneFromTrips.detatea,ligneFromTrips.destatu,ligneFromTrips.denbrkm,
      ligneFromTrips.deleg,ligneFromTrips.deactif,ligneFromTrips.agency_ID,
      ligneFromTrips.route_TYPE,ligneFromTrips.route_URL,ligneFromTrips.route_COLOR,
      ligneFromTrips.route_TEXT_COLOR,ligneFromTrips.denomli_RET,ligneFromTrips.integ_BI,
      ligneFromTrips.decClie,ligneFromTrips.decAdmi,ligneFromTrips.sae,
      ligneFromTrips.type,ligneFromTrips.centre, ligneFromTrips.color
    );
    let agent = new Agent(recPrFromTrips.decagen,chauffPrFromTrips.decagen,recPrFromTrips.denagea,
    chauffPrFromTrips.denagea,recPrFromTrips.denagen,chauffPrFromTrips.denagen,chauffPrFromTrips.decdeleg, chauffPrFromTrips.color)
    
    agentResourceArray.add(agent);
    ligneResourceArray.add(ligne);
    busResourceArray.add(bus);
    centreResourceArray.add(centre);
    delegResourceArray.add(deleg);
    
    const tripEvent = new TripEvent(trip.tripsId.trip_id,moment(trip.timeDepart,"DD/MM/YYYY HH:mm:ss").toDate(), moment(trip.finalStopTime,"DD/MM/YYYY HH:mm:ss").toDate(),
      String(trip.busPr.bus_id),trip.tripName,"À rejeté",trip.serviceId,String(trip.directionId),trip.haveret,trip.timeNret,trip.tripNid,trip.grp,
      String(chauffPrFromTrips.decagen),trip.chauffRe,trip.etat,trip.timeDepartR,trip.timeArriveR,trip.vMax,trip.avanceRetard,trip.changement,trip.metaData,
      trip.deValid,trip.alert,String(recPrFromTrips.decagen),trip.recRe,String(ligneFromTrips.idLigne),busReFromTrips.bus_id,String(busPrFromTrips.bus_id),
      String(ligneFromTrips.deleg.decdeleg), String(ligneFromTrips.centre.deccentre))
    
    return tripEvent;
    
  })
  resourceMode.value.busResources= busResourceArray;
  resourceMode.value.ligneResources= ligneResourceArray;
  resourceMode.value.agentResources = agentResourceArray;
  
  myTrips.value = trips;

}

const filterByCrit=(selectedCentreCrit, selectedDelegCrit)=>{
  try{
    let regexCentreString ="";
    let regexDelegString ="";
    selectedCentreCrit.map(valuer=>regexCentreString= regexCentreString+valuer.id+"|")
    selectedDelegCrit.map(valuer=>regexDelegString= regexDelegString+valuer.id+"|")
    let regexCentre = RegExp(regexCentreString.substring(0,regexCentreString.length-1))
    let regexDeleg = RegExp(regexDelegString.substring(0,regexDelegString.length-1))
    let includedResources = myTrips.value.map(trip=>{
      if (regexCentre.test(trip.centreId) && regexDeleg.test(trip.delegId))
      return trip.resource;
    })
    myResourcesCriteria.value = resourceMode.value.getMode(selectedResourceValue.value).values().filter(resource=>includedResources.includes(resource.id))
    myResources.value = myResourcesCriteria.value;

    centreCriteria=selectedCentreCrit;
    delegCriteria = selectedDelegCrit;
  }catch(e){
    console.error("Aucune critère n'est selectionnée")
  }
}


watch(getLoading, (isLoading)=>{
  if(!isLoading && getTrips.value.length>0){
    loadTrips();
    myResourcesCriteria.value = resourceMode.value.getMode(selectedResourceValue.value).values()
    myResources.value = myResourcesCriteria.value
  }
})

onMounted(()=>{
  store.dispatch("tripsModule/getTrips", new Date(2024,3,2,0,0,0,0));
})



</script>