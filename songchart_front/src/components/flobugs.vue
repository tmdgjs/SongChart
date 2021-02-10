<template>
    <div class="two_chart">
            <div class="chart_wrap">
                <div style="background : #4747FF" class="chart_title">
                    <h2>플로 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items v-for="index in 50" :index="index" :key="index" 
                        :img="flo.image[index-1]" :title="flo.title[index-1]" :singer="flo.singer[index-1]" :album="flo.album[index-1]"
                        v-bind:url="flo.url"/>
                    </ul>
                </div>
            </div>

            <div class="chart_wrap">
                <div  style="background :#FF3B28" class="chart_title">
                    <h2>벅스 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items v-for="index in 50" :index="index" :key="index" 
                        :img="bugs.image[index-1]" :title="bugs.title[index-1]" :singer="bugs.singer[index-1]" :album="bugs.album[index-1]"
                        v-bind:url="bugs.url"/>
                    </ul>
                </div>
            </div>
        </div>
</template>

<script>

    import Items from './chartitems'
    import axios from 'axios'

    export default {
    
    data : function (){
        return {
          
            bugs : {
                title : [],
                image : [],
                album : [],
                singer : [],
                url : "https://music.bugs.co.kr/search/integrated?q="
            },
            flo : {
                title : [],
                image : [],
                album : [],
                singer : [],
                url : "https://www.music-flo.com/search/all?keyword="
            },
            
        }
    },

    methods:{
        

        flo_axios : function(){
            
            axios.get('http://localhost:8080/chart/flo')
            .then(res => {
                for(let i = 0 ; i < res.data.length ; i++){
                    this.flo.album.push(res.data[i].album);
                    this.flo.image.push(res.data[i].imageurl);
                        
                    this.flo.title.push(res.data[i].title);
                    this.flo.singer.push(res.data[i].singer);
                }
            })
        },

        bugs_axios : function(){
            
            axios.get('http://localhost:8080/chart/bugs')
            .then(res => {

                for(let i = 0 ; i < res.data.length ; i++){
                    this.bugs.album.push(res.data[i].album);
                    this.bugs.image.push(res.data[i].imageurl);
                        
                    this.bugs.title.push(res.data[i].title);
                    this.bugs.singer.push(res.data[i].singer);
                }
            })
        }
    },

    mounted: function() {
        this.bugs_axios()
        this.flo_axios()
        
    },

    components: {
        Items
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
