<template>
  <div>
    <button @click="$router.go(-1)">Voltar</button> | 
    <router-link :to="{ name: 'Login' }">Efetuar Login</router-link> | 
    <router-link :to="{ name: 'Usuarios'}">Usuários</router-link> | 
    <router-link :to="{ name: 'Prova'}">Prova</router-link> | 
    <router-link :to="{ name: 'Questao'} ">Questões</router-link> | 
    <router-link :to="{ name: ''}">Realizar Prova</router-link>
    <button @click="$router.go(1)">Avançar</button>
  </div>

  <hr>
     
    
  
  
  <router-view/>
</template>

<!--<script>
    
    //import telaCadastro from './views/telaCadastro.vue'
    import routes from './components/router/routes'

    export default {
        components:{
          routes,
        }
        

    }
</script>-->



<!--
<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>


./components/router../router/routes
-->
