<template>
    <div id="main">
        
        <MelonGenie />
        <FloBugs />

    </div>
</template>


<script>

    
    import MelonGenie from './melongenie'
 
    import FloBugs from './flobugs'
    export default {
    
    components: {
        MelonGenie,FloBugs
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
