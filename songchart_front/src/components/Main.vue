<template>
    <div id="main">

        <MelonGenie />
        

        <MnetBugs />
            
       
    </div>
</template>


<script>

    import MelonGenie from './melongenie'
 
    import MnetBugs from './mentbugs'
    export default {
    
    components: {
        MelonGenie,MnetBugs
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
