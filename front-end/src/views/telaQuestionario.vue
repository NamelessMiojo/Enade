<template>
    inicio
    <section>
        <form @submit.prevent="createPost">
            <div v-for="prova in provas" :key="prova.id">
            <p>Prova : {{prova.id}}</p>
            <div v-for="(questao) in questoes" :key="questao.id">
                <p>{{ questao.tipoQuestao.nome }}</p>
                <p>{{ questao.descricao }}</p>
    
                <label> A) <input type="radio" name="alternativas" value="A" />{{ questao.alternativaA }}</label><br>
                <label> B) <input type="radio" name="alternativas" value="B" />{{ questao.alternativaB }}</label><br>
                <label> C) <input type="radio" name="alternativas" value="C" />{{ questao.alternativaC }}</label><br>
                <label> D) <input type="radio" name="alternativas" value="D" />{{ questao.alternativaD }}</label><br>
                <label> E) <input type="radio" name="alternativas" value="E" />{{ questao.alternativaE }}</label><br>
                <p></p>
            </div>
            </div>
            <button>Enviar Respostas</button>
        </form>
    </section>
    
    <div v-for="prova in provas" :key="prova.id">
    
    </div>
    </template>
    
    <script>
    import axios from 'axios'
    //import telaCadastro from './view/telaCadastro.vue'
    
    export default {
        data() {
            return {
                postRespostas: {},
                provas: [],
                questoes: [],
                respostas: [],
    
            }
        },
        //pegar questões com pesquisa de filtro
        mounted() {
            axios.get('http://localhost:56918/prova/')
                .then(response => this.provas = (response.data.contents))
            //axios.get('http://localhost:56918/prova/')
            //.then(response => this.provaQuestoes = (response.data.contents))
            axios.get('http://localhost:56918/questao/')
                .then(response => this.questoes = (response.data.contents))
        }
        /*name: 'telaCadastro',
        components: {
          telaCadastro
        }*/
    
    }
    </script>
    