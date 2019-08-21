<template>
    <div class="two_chart">
            <div class="chart_wrap">
                <div style="background : #00CD3C" class="chart_title">
                    <h2>멜론 실시간차트</h2>
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
                <div style="background : #FF3B28" class="chart_title">
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
    
    components: {
        Items
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
