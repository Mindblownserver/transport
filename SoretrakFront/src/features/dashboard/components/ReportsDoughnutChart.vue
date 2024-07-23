<template>
  <div class="chart">
    <canvas id="id" class="chart-canvas" :height="height"></canvas>
  </div>
</template>
<script>
import Chart from "chart.js/auto";

export default {
  name: "ReportsDoughnutChart",
  props: {
    height: {
      type: [Number, String],
      default: "370",
    },
  },

  methods: {
    createChart(lbl,arr){

      var ctx = document.getElementById("id");
      ctx.id = lbl;
      ctx = document.getElementById(lbl).getContext("2d")
      let chartStatus = Chart.getChart(lbl);
      if (chartStatus != undefined) {
        chartStatus.destroy();
      }
      new Chart(ctx, {
        type: "doughnut",
        data: {
          labels: ["Interurbain", "Urbain", "Regional"],
          datasets: [
            {
              borderWidth: 3,
              borderRadius: 4,
              borderSkipped: false,
              backgroundColor: "rgba(255, 255, 255, .9)",
              data:arr,
              spacing:3,
              borderColor:[
                "#fb8c00",
                "#BF24B8",
                "#D5DF28"
              ]
            },
          ],
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: {
              display: false,
              position:"bottom",
              labels:{
                color:"#FFFF",
                padding: 30
              },

            },
          },
        },
      });
    }
  },
};
</script>
