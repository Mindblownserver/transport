<template>
  <div class="py-4 container-fluid">
    <div class="row mb-4">
      <div class="col-lg-12 position-relative z-index-2">
        <TimeCard @Update:date="fetchDataFromDate"/>
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <mini-statistics-card
              :title="{ text: 'Les voyages d\'aujourd\'hui', value: `Aller-Retour: ${statistics.nbrVoyages|| 0}` }"
              :detail="`Aller:${(statistics.nbrAller || 0)} <br> Retour: ${(statistics.nbrRetour || 0)}`"
              :icon="{
                name: 'trending_up',
                color: 'text-white',
                background: 'dark',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Les agents plannifiés', value: `Chuaffeurs-Receveur: ${(statistics.nbrChauffRecAffectes[0] + statistics.nbrChauffRecAffectes[1]) || 0 }`}"
              :detail="`Chauffeur: ${(statistics.nbrChauffRecAffectes[0] || 0)} <br> Receveur: ${(statistics.nbrChauffRecAffectes[1] || 0 )}`"
              :icon="{
                name: 'person',
                color: 'text-white',
                background: 'info',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Les lignes plannifées', value: statistics.nbrLignesAffectes|| 0  }"
              :icon="{
                name: 'route',
                color: 'text-white',
                background: 'primary',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Les bus plannifées', value: statistics.nbrBusAffectes|| 0  }"
              :icon="{
                name: 'directions_bus',
                color: 'text-white',
                background: 'success',
              }"
            />
          </div>
        </div>
        <div class="row mt-4">
          <div class="col-lg-6 col-md-6 mt-4">
            <chart-holder-card
              title="Les lignes plannifiées par centre"
              bgColor="#ed78a1"
              shadowColor="#b4184f"
            >
              <ReportsDoughnutChart ref="ligneParCentreChart"/>
            </chart-holder-card>
          </div>
          <div class="col-lg-6 col-md-6 mt-4">
            <chart-holder-card
              title="Les vehicules plannifiées par centre"
              bgColor="#98cd9a"
              shadowColor="#428a46"

            >
            <ReportsDoughnutChart ref="busParCentreChart"/>
            </chart-holder-card>
          </div>
          <div class="col-lg-6 mt-5">
            <chart-holder-card
              title="Les agents plannifiés par centre"
              bgColor="#8abdf5"
              shadowColor="#1270d3"
            >
              <ReportsDoughnutChart ref="agentsParCentreChart"/>
            </chart-holder-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ChartHolderCard from "../components/ChartHolderCard.vue";
import ReportsDoughnutChart from "../components/ReportsDoughnutChart.vue";
import MiniStatisticsCard from "../components/MiniStatisticsCard.vue";
import TimeCard from "../components/TimeCard.vue";

export default {
  name: "dashboard-default",
  data() {
    return {
      statistics:{
        nbrChauffRecAffectes:Array
      },
      lignesParCentreValues: [],
      busParCentreChartValues:[],
      agentsParCentreChartValues:[]
    };
  },
  components: {
    ChartHolderCard,
    ReportsDoughnutChart,
    MiniStatisticsCard,
    TimeCard
  },
  mounted(){
    this.fetchDataFromDate(new Date());
  },
  methods:{
    fetchDataFromDate(date){
      this.$store.dispatch("dashboardModule/getStatisticsByDate", date).then(()=>{
        this.statistics= this.$store.state.dashboardModule.statistics;
        this.lignesParCentreValues = this.statistics.lignesParCentre.map(item=>item.value);
        this.busParCentreChartValues = this.statistics.busParCentre.map(item=>item.value);
        this.agentsParCentreChartValues = this.statistics.agentParCentre.map(item=>item.value)
        
        this.$refs.ligneParCentreChart.createChart("Lignes",this.lignesParCentreValues);
        this.$refs.busParCentreChart.createChart("Vehicules",this.busParCentreChartValues);
        this.$refs.agentsParCentreChart.createChart("Agents",this.agentsParCentreChartValues);

      })
    }
  }
};
</script>
