<template>
    <div class="two_chart">
            <div class="chart_wrap">
                <div style="background : #00CD3C" class="chart_title">
                    <h2>멜론 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items v-for="index in 50" :index="index" :key="index" 
                        :img="imgls.melonimgls[index-1]" :title="titlels.melontitlels[index-1]" :singer="singerls.melonsingerls[index-1]" :album="albumls.melonalbumls[index-1]"/>
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
            imgls : {
                melonimgls : [],
                genieimgls : [],
                bugsimgls : [],
                mnetimgls : [],
            },
            albumls : {
                melonalbumls : [],
                geniealbumls : [],
                bugsalbumls : [],
                mnetalbumls : [],
            },
            titlels : {
                melontitlels : [],
                genietitlels : [],
                bugstitlels : [],
                mnettitlels : [],
            },
            singerls : {
                melonsingerls : [],
                geniesingerls : [],
                bugssingerls : [],
                mnetsingerls : [],
            }
        }
    },

    methods:{
        melon : function(){
            axios.get('http://localhost:8080/chart/melon')
            .then(res => {
                console.log(res);
            })
        }
    },

    mounted: function() {
        axios.get('http://localhost:8080/chart')
        .then(res => {
            for(let i = 0 ; i< res.data.length ; i++){
                if(res.data[i].type === 'melon')
                    this.albumls.melonalbumls.push(res.data[i].album);
                    this.imgls.melonimgls.push(res.data[i].imageurl);
                  
                    this.titlels.melontitlels.push(res.data[i].title);
                    this.singerls.melonsingerls.push(res.data[i].singer);
            }
        })

        this.melon();
    },

    

    components: {
        Items
    }
}

</script>


<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>
