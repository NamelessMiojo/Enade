<template>
    <p> id: {{idAtualizar}}</p>
    <section>
        <form ref="form" @submit.prevent="addUser">
            <div>
                <label for="userNome">Nome</label>
                <input type="text" id="userNomeEnade"  v-model="newUser.nome" />
            </div>
            <div>
                <label for="userEmail">E-mail</label>
                <input type="text" id="userEmail" v-model="newUser.email" />
            </div>
            <div>
                <label for="userSenha">Senha</label>
                <input type="password" id="userSenha" v-model="newUser.senha" />
            </div>
            <!--<div>
              <label for="userSenha2">Senha</label>
              <input type="password" id="userSenha2" v-model="confirmacao"/>
            </div>-->
            <div>
                <label for="userTipo">Tipo Usuário</label>
                <select name="tipo" id="tipo" v-model="newUser.tipoUsuario.id">
                    <option v-for="uTipo in tiposUsers" :key="uTipo.id" :value="uTipo.id">{{uTipo.nome}}</option>
                </select>
    
            </div>
            <button @click="resetForm">Cadastrar Usuário</button>
        </form>
    </section>
</template>


<script setup>




</script>

<script>
import axios from 'axios'


export default {
    props: ['idAtualizar'],
    data() {
        return {
            newUser: {
                nome: '',
                email: '',
                senha: '',
                tipoUsuario: {
                    id: ''
                }
            },
            users: [],
            tiposUsers: [],
            user: {},
        }
    },
    methods: {
        resetForm() {
          this.$refs.form.reset();
        },
        addUser() {
            axios.post('http://localhost:56918/usuario/', this.newUser)
            //passar um clear input aqui
        },
        getListUsers() {
            axios.get('http://localhost:56918/usuario/')
                .then(response => this.users = (response.data.contents))
        },
        //talvez
        deleteUser() {
            axios.delete('', this.user)
        },
        getUser() {
            axios.get('', +this.id).then(response => this.user = (response.data))
        },
        attUser() {
            axios.put('', )
        }
    },
    mounted() {
        axios.get('http://localhost:56918/usuario/')
            .then(response => this.users = (response.data.contents))
        axios.get('http://localhost:56918/tipoUsuario/')
            .then(response => this.tiposUsers = (response.data.contents))
    },
    watch: {

    }
}
</script>