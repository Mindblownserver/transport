<template>
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
      <div class="mbsc-form-group-title">Trip Data</div>
      <MbscInput v-if="isEdit" inputStyle="outline" disabled labelStyle="floating" placeholder="dedated,tripId" v-model="popupTripId" label="ID" />
      <MbscInput v-else inputStyle="outline"  labelStyle="floating" placeholder="dedated,tripId" v-model="popupTripId" label="ID" />
      <MbscInput inputStyle="outline" v-model="popupServiceId" labelStyle="floating" placeholder="service" label="service Id" />
      <MbscDatepicker
        :controls="['time']" v-model="popupTimeDepart"
        inputStyle="outline" labelStyle="floating" label="Temps Du depart"
        timeFormat="HH:mm"
      />
      <MbscDatepicker
        :controls="['time']" v-model="popupTimeArr"
        inputStyle="outline" labelStyle="floating" label="Temps d'arrivé"
        timeFormat="HH:mm"
      />
      <MbscSegmentedGroup color="warning" v-model="popupDirectionId">
        <MbscSegmented value="0" >Aller</MbscSegmented>
        <MbscSegmented value="1">Retour</MbscSegmented>
      </MbscSegmentedGroup>
    </div>
    <div class="mbsc-form-group">
      <div class="mbsc-form-group-title">Vehicule Data</div>
      <MbscInput id="bus" @click="openResource" inputStyle="outline" v-model="popupBusId" labelStyle="floating" placeholder="Vehicule ID" label="ID" />
      <MbscInput inputStyle="outline" v-model="popupMatricule" disabled labelStyle="floating" placeholder="Matricule" label="Matricule" />
      <MbscInput inputStyle="outline" v-model="popupBusType" disabled labelStyle="floating" placeholder="Type" label="Type" />
    </div>
    <div class="mbsc-form-group">
      <div class="mbsc-form-group-title">Chauffeur-Receveur Data</div>
      <MbscInput id="chauffRec"  @click="openResource" inputStyle="outline" v-model="popupChauffRecId" labelStyle="floating" placeholder="chauffId | recId" label="Chauffeur | Receveur" />
      <MbscInput inputStyle="outline" v-model="popupChauffNomFr" disabled labelStyle="floating" placeholder="nom prenom Chauffeur" label="Nom et Prenom Chauffeur" />
      <MbscInput inputStyle="outline" v-model="popupChauffNomAr" disabled labelStyle="floating" placeholder="اسم ولقب السائق" label="اسم ولقب السائق"  />
      
      <MbscInput inputStyle="outline" v-model="popupRecNomFr" disabled labelStyle="floating" placeholder="nom prenom" label="Nom et Prenom Receveur" />
      <MbscInput inputStyle="outline" v-model="popupRecNomAr" disabled labelStyle="floating" placeholder="اسم ولقب رجل التذاكر" label="اسم ولقب رجل التذاكر" />
    </div>
    <div class="mbsc-form-group">
      <div class="mbsc-form-group-title">Ligne Data</div>
      <MbscInput id="ligne"  @click="openResource" inputStyle="outline" v-model="popupLigneId" labelStyle="floating" placeholder="Ligne ID" label="ID" />
      <MbscInput inputStyle="outline" v-model="popupLigneNom" disabled labelStyle="floating" placeholder="nom" label="Nom" />
      <MbscInput inputStyle="outline" v-model="popupLigneNomAr" disabled labelStyle="floating" placeholder="خط حافلة" label="خط حافلة" />
    </div>
  </MbscPopup>
  <MbResourcePopup ref="myResourcePopup" :resourcesProps="resourcesProp" @TempUpdate:resource="temporaryUpdate"/>
</template>

<script setup>
import {
  MbscDatepicker,
  MbscInput,
  MbscPopup,
  MbscSegmented,
  MbscSegmentedGroup,
} from '@mobiscroll/vue'
import { ref,computed } from 'vue';
import MbResourcePopup from './MbResourcePopup.vue';
import {useStore} from "vuex";

const emits = defineEmits(["cancelEventCreation"])

const popupTripId = ref("")
const popupServiceId = ref("")
const popupTimeDepart = ref("")
const popupTimeArr = ref("")
const popupDirectionId=ref(0)
const popupBusId = ref(0)

const popupMatricule = ref("")
const popupBusType=ref("")

const popupChauffRecId = ref("")
const popupChauffNomFr = ref("")
const popupChauffNomAr = ref("")

const popupRecNomFr = ref("")
const popupRecNomAr = ref("")

const popupLigneId = ref("");
const popupLigneNom = ref("");
const popupLigneNomAr=ref("")

const popupButtons = ref([])
const popupHeaderText = ref('')
const isPopupOpen = ref(false)
const popupAnchor = ref(null)
const isEdit =ref(false)

const myResourcePopup=ref(null)
const resourcesProp=ref(null);
const store = useStore();
let selectedResource;


const getVehicules = computed(()=>store.state.tripsModule.vehiculeFromTrips.values().sort((a,b)=>a.id>b.id));
const getLignes = computed(()=>store.state.tripsModule.ligneFromTrips.values());
const getAgents = computed(()=>store.state.tripsModule.agentFromTrips.values());

const myResponsive = {
  medium: {
    display: 'anchored',
    width: 400,
    fullScreen: false,
    touchUi: false
  }
}

function openResource(args){
  let mode="agent";
  if(args.target.id=="bus"){
    mode="bus"
    resourcesProp.value=getVehicules.value;
  }
  else if(args.target.id=="ligne"){
    mode="ligne"
    resourcesProp.value=getLignes.value;
  }
  else{
    resourcesProp.value=getAgents.value;
  }
  myResourcePopup.value.open(args.target,mode)
  args.target.blur();
}


function temporaryUpdate(idField,mode){
  if(mode=="bus")
    updateBusData(idField)
  else if(mode=="agent")
    updateAgentData(idField)
  else
    updateLigneData(idField)

}

function updateBusData(busId){
  popupBusId.value=busId;
  selectedResource= store.getters["tripsModule/getVehiculeById"](busId);
  popupBusType.value = selectedResource.typeLbl
  popupMatricule.value = selectedResource.name;
}

function updateAgentData(chauffRecId){
  selectedResource= store.getters["tripsModule/getAgentById"](chauffRecId);
  
  popupChauffRecId.value=selectedResource.id;
  
  popupRecNomFr.value = selectedResource.nameRec
  
  popupRecNomAr.value = selectedResource.nameRecAr;
  popupChauffNomFr.value = selectedResource.nameChauff
  popupChauffNomAr.value = selectedResource.nameChauffAr;
}

function updateLigneData(ligneId){
  popupLigneId.value=ligneId;
  selectedResource= store.getters["tripsModule/getLigneById"](ligneId);
  popupLigneNom.value = selectedResource.name
  popupLigneNomAr.value = selectedResource.denomla;
}

function getSelectedEvent(event){
  console.debug(event)
  popupTripId.value = event.id;
  popupServiceId.value = event.serviceId;
  popupTimeDepart.value = event.start;
  popupTimeArr.value = event.end;
  popupDirectionId.value = event.directionId;
  updateBusData(event.busPrId);
  updateAgentData(`${event.chauffPr}|${event.recPr}`);
  updateLigneData(event.ligneId);
}

function getAddedEvent(event){
  popupTripId.value = event.id;
  popupTimeDepart.value = event.start;
  popupTimeArr.value = event.end;
  clearFields();
}

function clearFields(){
  popupServiceId.value=""
  popupDirectionId.value=1;
  popupBusId.value=""
  popupBusType.value=""
  popupMatricule.value=""
  popupChauffRecId.value=""
  popupChauffNomAr.value=""
  popupChauffNomFr.value="";
  popupRecNomAr.value=""
  popupRecNomFr.value=""
  popupLigneId.value=""
  popupLigneNom.value=""
  popupLigneNomAr.value=""
}

function createAddPopup(event, target, mode) {
  isEdit.value = false
  // Set popup header text and buttons
  popupHeaderText.value = 'Nouveau Trips'
  popupButtons.value = [
    {
      text:'cancel',
      keyCode:27,
      handler:()=>{
        isPopupOpen.value=false;
        emits("cancelEventCreation", event);
      }
    },
    {
      text: 'Add',
      keyCode: 'enter',
      handler: () => {
        
        console.log("Created the add popup while updating ",mode, " if needed")
        isPopupOpen.value = false
      },
      cssClass: 'mbsc-popup-button-primary'
    }
  ]
  popupAnchor.value = target

  getAddedEvent(event)

  isPopupOpen.value = true
}

function createEditPopup(event, target, mode) {
  isEdit.value = true

  popupHeaderText.value = 'Modification du trips '+ event.id
  popupButtons.value = [ 
    {
      text:'cancel',
      keyCode:27,
      handler:()=>{
        isPopupOpen.value=false;
      },
    },
    {
      text: 'Save',
      keyCode: 'enter',
      handler: () => {
        saveEventModification(event,mode);
        isPopupOpen.value = false
      },
      cssClass: 'mbsc-popup-button-primary'
    }
  ]
  popupAnchor.value = target
  getSelectedEvent(event)
  isPopupOpen.value = true
}

function saveEventModification(event,mode){
  const chauffRecArr = popupChauffRecId.value.split("|")
  event.id =popupTripId.value;
  event.serviceID = popupServiceId;
  event.start = popupTimeDepart.value;
  event.end = popupTimeArr.value;
  event.directionId = popupDirectionId.value;
  event.busPrId = popupBusId.value;
  event.ligneId = popupLigneId.value;
  event.recPr = chauffRecArr[1];
  event.chauffPr = chauffRecArr[0]
  event.changeResourceId(mode);

}

defineExpose({
  createAddPopup,
  createEditPopup,
  isPopupOpen,
})
</script>

<style scoped>

</style>