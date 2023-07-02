<template>

    
    <section class="resultados">
        <div v-for="questao in questoes" :key="questao.id">
            {{ questao.enunciado }}
        </div>

    </section>
</template>


<script>

import axios from 'axios'

    export default {
        data(){
            return {
                questoes: [],
            }
        },
        methods:{

        },
        mounted(){
            axios.get('http://localhost:56918/questao/').then(response => this.questoes = (response.data.contens))
        }
    }
</script>