<template>
    <div id="main">

        <div class="two_chart">
            <Melon/>

            <Genie />
        </div>
        
        

        <MnetBugs />
            
       
    </div>
</template>


<script>

    import Genie from './genie'
    import Melon from './melon'
 
    import MnetBugs from './mentbugs'
    export default {
    
    components: {
        Genie,MnetBugs,Melon
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
