<template>
    <div class="chart_wrap">
                <div style="background : #00CD3C" class="chart_title">
                    <h2>멜론 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items v-for="index in 50" :index="index" :key="index" 
                        :img="melon.image[index-1]" :title="melon.title[index-1]" 
                        :singer="melon.singer[index-1]" :album="melon.album[index-1]"/>
                    </ul>
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
                }
            
            }

        },

        methods:{
        melon_axios : function(event){
            
         
                    this.melon.album.push(event.album);
                    this.melon.image.push(event.imageurl);
                  
                    this.melon.title.push(event.title);
                    this.melon.singer.push(event.singer);
                
        

            
            }
        },

        mounted: function() {
        axios.get('http://localhost:8080/chart')

            .then(res => {
               
                for(let i = 0 ; i< res.data.length ; i++){
                    if(res.data[i].type ==='melon'){
                        this.melon_axios(res.data[i]);
                    }
                    
            
                }
            });
        
       
        },

        components: {
        Items

            }
    }

    
</script>

<style lang="scss" scoped>
 @import '../assets/css/chart.scss';
</style>