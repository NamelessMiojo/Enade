<template>

    <section>
        <section>
            <section>
                <form ref="form" @submit.prevent="addProva">
                    <div>
                        <label for="data">Data</label>
                        <input type="text" id="data" v-model="newProva.dataProva"/>
                    </div>
                    <div>
                        <label for="data">nome</label>
                        <input type="text" id="data" v-model="newProva.nome"/>
                    </div>
                    <div>
                        <label for="questoes">Questoes</label>
                        <div v-for="questao in listQuestoes" :key="questao.id">
                            {{questao.tipoQuestao.nome}} | 
                            {{questao.descricao}}
                            <button @click="adicionarQuestao(questao)">Adicionar</button>
                        </div>
                    </div>
                    <button > Cadastrar Prova </button>
                </form>

            </section>
            
        </section>
    </section>
   
</template>


<script>

import axios from 'axios'

    export default {
        data(){
            return {
                url: '',
                newProva:{
                    dataProva:'',
                    nome:'',
                    questoes:[
                        
                    ],
                },
                listQuestoes: [],
                newQuestao:{
                    id:'',
                },
                listProvaQuestoes:[],
                
            }
        },
        methods:{
            adicionarQuestao(questao){
                //funciona tanto passando apenas o id quanto o objeto inteiro
                this.listProvaQuestoes.push(questao)
                console.log(this.listProvaQuestoes)
            },
            addProva(){
                //axios.post(this.url, this.newProva)
            }
        },
        mounted(){
            axios.get('http://localhost:56918/questao/')
            .then(response => this.listQuestoes = (response.data.contents))
        }
    }
</script>