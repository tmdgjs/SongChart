<template>
<div class="two_chart">
    <div class="chart_wrap">
        
        <div style="background : #00CD3C" class="chart_title">
            <h2>멜론 실시간차트</h2>
        </div>

        <div class="chart_ls">
            <ul>
                <Items v-for="index in 50" :index="index" :key="index" 
                :img="melon.image[index-1]" :title="melon.title[index-1]" 
                :singer="melon.singer[index-1]" :album="melon.album[index-1]" v-bind:url="melon.url"/>
            </ul>
        </div>
    </div>

    <div class="chart_wrap">
        <div style="background :  #21B5E6" class="chart_title">
            <h2>지니 실시간차트</h2>
        </div>

        <div class="chart_ls">
            <ul>
                <Items v-for="index in 50" :index="index" :key="index" 
                :img="genie.image[index-1]" :title="genie.title[index-1]" :singer="genie.singer[index-1]" :album="genie.album[index-1]"
                v-bind:url="genie.url"/>
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
                melon : {
                    title : [],
                    image : [],
                    album : [],
                    singer : [],
                    url : "https://www.melon.com/search/total/index.htm?q="
                },
                genie : {
                    title : [],
                    image : [],
                    album : [],
                    singer : [],
                    url : "https://www.genie.co.kr/search/searchMain?query="
                }
            
            }

        },

        methods:{

        melon_axios : function(){
            
            try{
                axios.get('http://localhost:8080/chart/melon')
                .then(res => {
                for(let  i = 0 ; i < res.data.length; i++){
                  
                    this.melon.album.push(res.data[i].album);
                    this.melon.image.push(res.data[i].imageurl);
                  
                    this.melon.title.push(res.data[i].title);
                    this.melon.singer.push(res.data[i].singer);
                    }
                })
            }
            catch(e){
                console.log(e)
                }
            },

            genie_axios : function(){
                axios.get('http://localhost:8080/chart/genie')
            
                .then(res => {
                for(let  i = 0 ; i < res.data.length; i++){
                            
                        this.genie.album.push(res.data[i].album);
                        this.genie.image.push(res.data[i].imageurl);
                    
                        this.genie.title.push(res.data[i].title);
                        this.genie.singer.push(res.data[i].singer);

                    }
                });
        
            }
        },

        mounted: function() {
            try{

                this.melon_axios();
                this.genie_axios();
                
            
            }
            catch(e){
                console.log(e)
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