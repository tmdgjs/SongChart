<template>
  <div id="app">
    <div id="nav">
   
       <Home />

    </div>
   
  </div>
</template>

<script>

import Home from './components/Main'
export default {
  name: 'home',
  components: {
    Home
  }
}
</script>

<style>

body{
  margin:0;
  background-color: #F2F4F7;
}
#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin:0;
    padding : 0;
  
}

</style>
