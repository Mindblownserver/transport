<template>
    <MbCalendar 
    :myTripsProp="myTrips" 
    :myBusesProp="myResources"
    :centreProp = "centreResourceArray.values()"
    :delegProp = "delegResourceArray.values()"
    @update:search-query="filter" 
    @update:resource-mode="checkSelection"
    @update:search-by-time-query="filerByTime"
    @update:criteria-query="filterByCrit"
    @update:date="fetchData"/>
</template>

<script setup>
import MbCalendar from './MbCalendar.vue';
import { Bus, Ligne, Agent, ResourceArray,ResourceModes } from '../utils/Resource'
import { computed, onMounted, ref } from 'vue'
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


let busResourceArray;
let ligneResourceArray;
let agentResourceArray;
let centreResourceArray = new ResourceArray();
let delegResourceArray = new ResourceArray();

// computing values

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
  busResourceArray = new ResourceArray();
  ligneResourceArray = new ResourceArray();
  agentResourceArray = new ResourceArray();
  

  const trips = getTrips.value.map((trip)=>{
    const busReFromTrips = trip.busRe;
    const busPrFromTrips = trip.busPr;
    const ligneFromTrips=trip.ligne;
    const recPrFromTrips = trip.recPr;
    const chauffPrFromTrips = trip.chauffPr

    let deleg = new DrDeleg(ligneFromTrips.deleg.decdeleg,ligneFromTrips.deleg.frdeleg,ligneFromTrips.deleg.ardeleg);
    let centre = new DrCentre(ligneFromTrips.centre.deccentre, ligneFromTrips.centre.delcentre,ligneFromTrips.centre.arcentre);

    let bus = new Bus(String(busPrFromTrips.decodvh),busPrFromTrips.dematri, busPrFromTrips.decatvh.id, busPrFromTrips.decatvh.decateg, busPrFromTrips.decatvh.color)
    let ligne = new Ligne(String(ligneFromTrips.idLigne),ligneFromTrips.nomLigne,ligneFromTrips.priorite,
      ligneFromTrips.dectyta,ligneFromTrips.dectyeq,ligneFromTrips.denomla,ligneFromTrips.detatec,
      ligneFromTrips.detatea,ligneFromTrips.destatu,ligneFromTrips.denbrkm,
      ligneFromTrips.deleg,ligneFromTrips.deactif,ligneFromTrips.agency_ID,
      ligneFromTrips.route_TYPE,ligneFromTrips.route_URL,ligneFromTrips.route_COLOR,
      ligneFromTrips.route_TEXT_COLOR,ligneFromTrips.denomli_RET,ligneFromTrips.integ_BI,
      ligneFromTrips.decClie,ligneFromTrips.decAdmi,ligneFromTrips.sae,
      ligneFromTrips.type,ligneFromTrips.centre, ligneFromTrips.color
    );
    let agent = new Agent(String(recPrFromTrips.decagen),String(chauffPrFromTrips.decagen),recPrFromTrips.denagea,
    chauffPrFromTrips.denagea,recPrFromTrips.denagen,chauffPrFromTrips.denagen,chauffPrFromTrips.decdeleg, chauffPrFromTrips.color)
    
    agentResourceArray.add(agent);
    ligneResourceArray.add(ligne);
    busResourceArray.add(bus);
    centreResourceArray.add(centre);
    delegResourceArray.add(deleg);
    
    const tripEvent = new TripEvent(trip.tripsId.trip_id,moment(trip.timeDepart,"DD/MM/YYYY HH:mm:ss").toDate(), moment(trip.finalStopTime,"DD/MM/YYYY HH:mm:ss").toDate(),
      String(trip.busPr.decodvh),trip.tripName,trip.serviceId,String(trip.directionId),trip.haveret,trip.timeNret,trip.tripNid,trip.grp,
      String(chauffPrFromTrips.decagen),trip.chauffRe,trip.etat,trip.timeDepartR,trip.timeArriveR,trip.vMax,trip.avanceRetard,trip.changement,trip.metaData,
      trip.deValid,trip.alert,String(recPrFromTrips.decagen),trip.recRe,String(ligneFromTrips.idLigne),busReFromTrips.decodvh,String(busPrFromTrips.decodvh),
      String(ligneFromTrips.deleg.decdeleg), String(ligneFromTrips.centre.deccentre))
    
    return tripEvent;
    
  })
  resourceMode.value.busResources= busResourceArray;
  resourceMode.value.ligneResources= ligneResourceArray;
  resourceMode.value.agentResources = agentResourceArray;
  myTrips.value = trips;

  store.commit("tripsModule/setLignes", ligneResourceArray);
  store.commit("tripsModule/setVehicules", busResourceArray);
  store.commit("tripsModule/setAgents", agentResourceArray);
}
/**
 * Function that filters the resources by criteria (deleg & centres)
 * It's executed the moment any of the criteria is changed
 * @example if deleg is changed, the resource will get filtered by deleg and center, and vice-cersa
 */
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

const fetchData=(date, selectedCentre, selectedDeleg)=>{
  store.dispatch("tripsModule/getTrips", date).then(()=>{
    loadTrips();
    filterByCrit(selectedCentre,selectedDeleg)
  });
}

onMounted(()=>{
  fetchData(new Date());
  
})



</script>