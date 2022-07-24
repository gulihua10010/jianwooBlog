<template>
    <Timeballs/>
    <Calendar/>
    <Recommend/>
    <Tags/>

</template>

<script>
import Recommend from '@/components/Recommend.vue'
import Tags from '@/components/Tags.vue'
import Calendar from "@/components/Calendar";
import Timeballs from "@/components/Timeballs";

export default {
    name: "RightModel",
    components: {
        Recommend,
        Tags,
        Calendar,
        Timeballs,
    },
    data() {
        return {}
    },
    mounted() {

    },
    created() {

    },
    methods: {},
    watch: {}
}
</script>

<style scoped>

</style>