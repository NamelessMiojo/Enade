<template>

    <section>
        <section>
            <section>
                <form ref="form" @submit.prevent>
                    <div>
                        <label for="data">Data</label>
                        <input type="text" id="data" v-model="newProva.dataProva"/>
                    </div>
                    <!--<div>
                        <label for="data">nome</label>
                        <input type="text" id="data" v-model="newProva.nome"/>
                    </div>-->
                    <div>
                        <label for="questoes">Questoes</label>
                        <div v-for="questao in listQuestoes" :key="questao.id" >
                            {{questao.tipoQuestao.nome}} | 
                            {{questao.descricao}}
                            <button @click="adicionarQuestao(questao)">Adicionar</button>
                        </div>
                    </div>
                    <button @click="addProva"> Cadastrar Prova </button>
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
                url: 'http://localhost:56918/prova/',
                newProva:{
                    dataProva:'',
                    questoes:[
                        
                    ]
                },
                listQuestoes: [],
                listProvaQuestoes:[],
                
                
            }
        },
        methods:{
            resetForm() {
          this.$refs.form.reset();
        },
            adicionarQuestao(questao){
                //funciona tanto passando apenas o id quanto o objeto inteiro
                this.newProva.questoes.push(questao)
                
            },
            addProva(){
                console.log(this.newProva)
                axios.post(this.url, this.newProva)
                this.$refs.form.reset()
            }
        },
        mounted(){
            axios.get('http://localhost:56918/questao/')
            .then(response => this.listQuestoes = (response.data.contents))
        }
    }
</script>