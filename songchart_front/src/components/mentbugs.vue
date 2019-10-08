<template>
    <div class="two_chart">
            <div class="chart_wrap">
                <div style="background : #E30076" class="chart_title">
                    <h2>엠넷 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items v-for="index in 50" :index="index" :key="index" 
                        :img="mnet.image[index-1]" :title="mnet.title[index-1]" :singer="mnet.singer[index-1]" :album="mnet.album[index-1]"/>
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
                        :img="bugs.image[index-1]" :title="bugs.title[index-1]" :singer="bugs.singer[index-1]" :album="bugs.album[index-1]"/>
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
            },
            mnet : {
                title : [],
                image : [],
                album : [],
                singer : [],
            },
            
        }
    },

    methods:{
        

        mnet_axios : function(){
            
            axios.get('http://54.180.91.106/chart/mnet')
            .then(res => {
                for(let i = 0 ; i < res.data.length ; i++){
                    this.mnet.album.push(res.data[i].album);
                    this.mnet.image.push(res.data[i].imageurl);
                        
                    this.mnet.title.push(res.data[i].title);
                    this.mnet.singer.push(res.data[i].singer);
                }
            })
        },

        bugs_axios : function(){
            
            axios.get('http://54.180.91.106/chart/bugs')
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
   
       
        this.mnet_axios()
        this.bugs_axios()
    },

    components: {
        Items
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
