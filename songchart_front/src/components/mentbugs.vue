<template>
    <div class="two_chart">
            <div class="chart_wrap">
                <div style="background : #E30076" class="chart_title">
                    <h2>엠넷 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items />
                        <Items />
                        <Items />
                        <Items />
                        <Items />
                        <Items />
                    </ul>
                </div>
            </div>

            <div class="chart_wrap">
                <div  style="background : #21B5E6" class="chart_title">
                    <h2>벅스 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items />
                        <Items />
                        <Items />
                        <Items />
                        <Items />
                        <Items />
                    </ul>
                </div>
            </div>
        </div>
</template>

<script>

    import Items from './chartitems'
 
    export default {
    
    data : function (){
        return {
          
            bugs : {
                 title : [],
                image : [],
                album : [],
                singer : [],
            },
            mnet : {
                 title : [],
                image : [],
                album : [],
                singer : [],
            },
            genie : {
                 title : [],
                image : [],
                album : [],
                singer : [],
            }
        }
    },

    components: {
        Items
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
