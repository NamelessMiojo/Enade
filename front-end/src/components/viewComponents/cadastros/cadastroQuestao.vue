<template>
    <section>
        <form ref="form" @submit.prevent="addQuestao">
            <div>
                <label for="enunciado">Enunciado:</label>
                <textarea type="text" id="enunciado" v-model="newQuestao.descricao"/>
            </div>
            <div>
                <label for="alternativaA"> Alternativa A:</label>
                <input type="text" id="alternativaA" v-model="newQuestao.alternativaA"/> 
                <input type="radio" name="alternativas" value="A" v-model="newQuestao.questaoCorreta"/>
            </div>
            <div>
                <label for="alternativaB"> Alternativa B:</label>
                <input type="text" id="alternativaB" v-model="newQuestao.alternativaB"/>
                <input type="radio" name="alternativas" value="B" v-model="newQuestao.questaoCorreta"/>
            </div>
            <div>
                <label for="alternativaC"> Alternativa C:</label>
                <input type="text" id="alternativaC" v-model="newQuestao.alternativaC"/>
                <input type="radio" name="alternativas" value="C" v-model="newQuestao.questaoCorreta"/>
            </div>
            <div>
                <label for="alternativaD"> Alternativa D:</label>
                <input type="text" id="alternativaD" v-model="newQuestao.alternativaD"/>
                <input type="radio" name="alternativas" value="D" v-model="newQuestao.questaoCorreta"/>
            </div>
            <div>
                <label for="alternativaE"> Alternativa E:</label>
                <input type="text" id="alternativaE" v-model="newQuestao.alternativaE"/>
                <input type="radio" name="alternativas" value="E" v-model="newQuestao.questaoCorreta"/>
            </div>
            
            <div>
                <label for="tipoQuestao">Tipo Questão</label>
                <select name="tipo" id="tipo" v-model="newQuestao.tipoQuestao.id">
                    <option v-for="qTipo in tipoQuestoes" :key="qTipo.id" :value="qTipo.id">{{qTipo.nome}}</option>
                </select>
            </div>


            <button @click="resetForm"> Cadastrar Questão</button>
        </form>
    </section>
</template>

<script>

import axios from 'axios'

export default{
    data(){
        return {
            newQuestao:{
                descricao:'',
                alternativaA:'',
                alternativaB:'',
                alternativaC:'',
                alternativaD:'',
                alternativaE:'',
                estadoQuestao: 0,
                questaoCorreta: '',
                tipoQuestao:{
                    id:''
                }

            },
            tipoQuestoes:[]
        }
        
    },
    methods: {
        resetForm() {
          this.$refs.form.reset();
        },
        addQuestao(){
            axios.post('http://localhost:56918/questao/', this.newQuestao)
        }
    },
    mounted(){
        axios.get('http://localhost:56918/tipoQuestao/').then(response => this.tipoQuestoes = (response.data.contents))
    }
}

</script>