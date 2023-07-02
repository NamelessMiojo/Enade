<template>
    <section>
        <form ref="form" @submit.prevent="addTipoQuestao">
            <div>
                <label>Tipo Questao</label>
                <input type="text" id="tipoQuestaoNome" v-model="nome"/>
            </div>
            <button @click="resetForm">Cadastrar Tipo Questao</button>
        </form>
    </section>
</template>

<script>
import axios from 'axios'

export default{
    data(){
        return{
            newTipoQuestao:{
                nome:'',
            },
            nome: ''
        }
    },
    methods: {
        resetForm(){
            this.$refs.form.reset();
        },
        addTipoQuestao(){
            this.newTipoQuestao.nome =this.nome.toUpperCase()
            axios.post('http://localhost:56918/tipoQuestao/', this.newTipoQuestao)
        }
    }
}
</script>