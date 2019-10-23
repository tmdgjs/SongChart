<template>
            <li>
                <div class="rank" >{{this.index}}</div>
                
                <div class="img_wrap">
                
                    <img :src="img"/>
                
                </div>
                
                <div class="song_detail">
                    
                    <div class="song_title">
                        <a v-bind:href="this.url + this.title" v-bind:title="this.title" target="_blank">{{this.title}}</a>
                    </div>

                    <div class="song_singer">
                        {{this.singer}}
                    </div>

                    <div class="song_album">
                        {{this.album}}
                    </div>
                </div>
            </li>
       
    
</template>

<script>

import axios from 'axios'



    export default {
        name: 'chartitems',
        props: ['index','img','singer','album','title','url'], 
    }
</script>
<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>