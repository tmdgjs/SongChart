<template>
    <div id="main">

        <MelonGene />
        

        <MnetBugs />
            
       
    </div>
</template>


<script>

    import MelonGene from './melongene'
 
    import MnetBugs from './mentbugs'
    export default {
    
    components: {
        MelonGene,MnetBugs
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
