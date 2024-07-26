<template>
  <div class="card z-index-2">
    <div
      class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent"
    >
      <div
        class="border-radius-lg py-4 pe-1" :style="{backgroundColor: bgColor, boxShadow: `${shadowColor} 0px 5px 15px`}">
        <slot />
      </div>
    </div>
    <div class="card-body">
      <h6 class="mb-0">{{ title }}</h6>
    </div>
  </div>
  <!-- :class="`bg-gradient-${color} shadow-${color}`" -->
</template>

<script>
export default {
  name: "ChartHolderCard",
  props: {
    title: {
      type: String,
      default: "",
    },
    
    color: {
      type: String,
      default: "primary",
    },
    bgColor:{
      type:String,
    },
    shadowColor:{
      type:String
    }
  },
};
</script>
