<template>
  <div class="py-4 container-fluid">
    <div class="row mb-4">
      <div class="col-lg-12 position-relative z-index-2">
        <div class="row">
          <div class="col-lg-3 col-md-6 col-sm-6">
            <mini-statistics-card
              :title="{ text: 'Les voyages d\'aujourd\'hui', value: statistics.nbrVoyages|| 0 }"
              :detail="(statistics.nbrAller || 0) +' aller et '+(statistics.nbrRetour || 0) +' retours'"
              :icon="{
                name: 'trending_up',
                color: 'text-white',
                background: 'dark',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Les agents affectées', value: (statistics.nbrChauffRecAffectes[0] + statistics.nbrChauffRecAffectes[1]) || 0 }"
              :detail="(statistics.nbrChauffRecAffectes[0] || 0) + ' Chauffeurs et '+ (statistics.nbrChauffRecAffectes[1] || 0 )+ ' receveurs'"
              :icon="{
                name: 'person',
                color: 'text-white',
                background: 'info',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Les lignes affectées', value: statistics.nbrLignesAffectes|| 0  }"
              :icon="{
                name: 'route',
                color: 'text-white',
                background: 'primary',
              }"
            />
          </div>
          <div class="col-lg-3 col-md-6 col-sm-6 mt-lg-0 mt-4">
            <mini-statistics-card
              :title="{ text: 'Les bus affectées', value: statistics.nbrBusAffectes|| 0  }"
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
              title="Les differentes lignes affectées par centre"
            >
              <ReportsDoughnutChart ref="ligneParCentreChart"/>
            </chart-holder-card>
          </div>
          <div class="col-lg-6 col-md-6 mt-4">
            <chart-holder-card
              title="Les differentes vehicules affectées par centre"
              color="success"
            >
            <ReportsDoughnutChart ref="busParCentreChart"/>
            </chart-holder-card>
          </div>
          <div class="col-lg-6 mt-5">
            <chart-holder-card
              title="Les agents affectés par centre"
              color="info"
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
  },
  mounted(){
    this.$store.dispatch("dashboardModule/getStatisticsByDate", new Date(2024,3,2,0,0,0,0)).then(()=>{
      this.statistics= this.$store.state.dashboardModule.statistics;
      this.lignesParCentreValues = this.statistics.lignesParCentre.map(item=>item.value);
      this.busParCentreChartValues = this.statistics.busParCentre.map(item=>item.value);
      this.agentsParCentreChartValues = this.statistics.agentParCentre.map(item=>item.value)
      
      this.$refs.ligneParCentreChart.createChart("Lignes",this.lignesParCentreValues);
      this.$refs.busParCentreChart.createChart("Vehicules",this.busParCentreChartValues);
      this.$refs.agentsParCentreChart.createChart("Agents",this.agentsParCentreChartValues);

    })
  }
};
</script>
