<template>
            <li>
                <div class="rank" >{{this.index}}</div>
                
                <div class="img_wrap">
                
                    <img :src="img"/>
                
                </div>
                
                <div class="song_detail">
                    
                    <div class="song_title">
                        {{this.title}}
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

axios.get('http://localhost:8080/chart')
    .then(res => {

        /*for(let i = 0 ; i< res.data.length ; i++){
            if(res.data[i].type === 'melon')
                console.log(res.data[i].imageurl);
                 
        }*/
        
        
    })

    export default {
        name: 'example',
        props: ['index','img','singer','album','title'], 
    }
</script>
<style lang="scss" scoped>
    @import '../assets/css/chart.scss';
</style>