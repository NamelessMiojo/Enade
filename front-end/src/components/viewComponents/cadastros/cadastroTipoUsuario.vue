<template>
    <section>
        <form ref="form" @submit.prevent="addTipoUser">
            <div>
                <label>Tipo Usuario</label>
                <input type="text" id="tipoUserNome"  v-model="nome"/>
            </div>
            <button @click="resetForm">Cadastrar Tipo Usuário</button>
        </form>
    </section>
</template>

<script>
import axios from 'axios'

export default{
    data(){
        return{
            newTipoUser:{
                nome:'',
            },
            nome:''
        }
    },
    methods: {
        resetForm(){
            this.$refs.form.reset();
        },
        addTipoUser(){
            this.newTipoUser.nome = this.nome.toUpperCase()
            axios.post('http://localhost:56918/tipoUsuario/', this.newTipoUser)
        }, 
        
    }
}
</script>

