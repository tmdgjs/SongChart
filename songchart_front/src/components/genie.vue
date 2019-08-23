<template>
   
            <div class="chart_wrap">
                <div style="background : #FF3B28" class="chart_title">
                    <h2>지니 실시간차트</h2>
                </div>

                <div class="chart_ls">
                    <ul>
                        <Items v-for="index in 50" :index="index" :key="index" 
                        :img="genie.image[index-1]" :title="genie.title[index-1]" :singer="genie.singer[index-1]" :album="genie.album[index-1]"/>
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

    methods:{
        

        genie_axios : function(event){
                       
            this.genie.album.push(event.album);
            this.genie.image.push(event.imageurl);
                  
            this.genie.title.push(event.title);
            this.genie.singer.push(event.singer);
            //console.log(this.genie)
        }
    },

    mounted: function() {
        axios.get('http://localhost:8080/chart')

        .then(res => {
        
        for(let i = 0 ; i< res.data.length ; i++){
             
                if(res.data[i].type === 'genie'){
                    console.log(res.data[i])
                    this.genie_axios(res.data[i]);
                    
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
