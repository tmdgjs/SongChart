<template>
    <div id="main">
        <div id="chart_wrap">
            <div id="chart_title">
                <h2>실시간차트</h2>
            </div>

            <div id="chart_ls">
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

        <div id="search_input">
            <input id="search_song" placeholder="검색어를 입력하세요."/>
            <button id="search_Btn">
                →
            </button>
        </div>

        <div id="song_info">
            <div id="search_song_category">
                <div id="category_title">곡명</div>
                <div id="category_singer">아티스트</div>
                <div id="category_album">앨범</div>
            </div>
            <Searchitems />
        </div>
            
       
    </div>
</template>


<script>

    import Items from './chartitems'
    import Searchitems from './searchitems'

    export default {
    name: 'items, Searchitems',
    components: {
        Items,Searchitems
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
