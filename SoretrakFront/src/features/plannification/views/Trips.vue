<template>
    <div class="container-fluid py-4">   
      <div class="row">
        <div class="col-12">
          <div class="card my-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
              <div class="bg-gradient-warning shadow-warning border-radius-lg pt-4 pb-3">
                <h6 class="text-white text-capitalize ps-3">Visualisation des trips</h6>
              </div>
            </div>
            <div class="card-body px-0 pb-2">
              <div class="table-responsive p-0" style="height: 75vh;">
                  <TimelineComponent />
              </div>
            </div>
          </div>
        </div>
      </div>       
    </div>
</template>

<script setup>
import TimelineComponent from '../components/TimelineComponent.vue';
</script>