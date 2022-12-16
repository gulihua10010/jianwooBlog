<template>
    <div id="app" style="height: 100%;">
        <router-view :key="key"/>
    </div>
</template>

<script>

export default {
    name: 'App',
    props: {
        isMobile: {
            type: Boolean,
        },
    },
    computed: {
        key() {
            return this.$route.path
        }
    }
}
</script>

<style>
#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
}
</style>
