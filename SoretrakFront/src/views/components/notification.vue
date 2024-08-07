<template>
  <material-snackbar
    v-if="snackbar"
    :title="snackbar.title"
    :description="snackbar.description"
    :icon="{ component: 'done', color: 'white' }"
    :color="snackbar.color"
    :close-handler="closeSnackbar"
  />
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import MaterialSnackbar from "@/components/MaterialComponents/MaterialSnackbar.vue";

export default {
  name: "Notification",
  components: {
    MaterialSnackbar,
  },
  computed: {
    ...mapGetters(['snackbar']),
  },
  methods: {
    ...mapActions(['triggerSnackbar']),
    closeSnackbar() {
      this.$store.commit('hideSnackbar');
    },
  },
};
</script>
