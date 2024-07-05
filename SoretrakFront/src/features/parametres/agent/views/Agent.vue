<template>
    <div>
        <!-- {{ getRoute() }} -->
    </div>
</template>

<script>
import { computed } from 'vue';
import { useRoute } from 'vue-router';

export default {
    name:"Agent",
    methods:{
        getRoute() {
            const route = useRoute();

            const routeName = computed(() => route.name || 'Default Name');

            return routeName;
        },
    }
    
};
</script>
