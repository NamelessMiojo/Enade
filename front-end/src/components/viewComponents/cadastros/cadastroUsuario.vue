<template>
<h2>Cadastrar Novo Usuário</h2>
<section>
    <form ref="form" @submit.prevent="addUser">
        <div>
            <label for="userNome">Nome</label>
            <input type="text" id="userNomeEnade" v-model="newUser.nome" />
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
<!--<div v-for=" user in users" :key="user.id">
      <p>{{user.nome}}</p>
      <p>{{user.email}}</p>

    </div>-->
<div></div>
</template>

  
<script>
import axios from 'axios'
import usuario from '../../loadComponents/usuario'

export default {
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
            
            tiposUsers: [],
            user: {},
        }
    },
    methods: {
        resetForm() {
          usuario.resetForm(this.$refs.form)
        },
        addUser() {
            usuario.criarUser(this.newUser)
        },
        getListUsers() {
            axios.get('http://localhost:56918/usuario/')
                .then(response => this.users = (response.data.contents))
        },
        
    },
    mounted() {
        
        axios.get('http://localhost:56918/tipoUsuario/')
            .then(response => this.tiposUsers = (response.data.contents))
    },
    
}
</script>

<style>
html,
body {
    margin: 0;
    padding: 0
}

section {
    height: 100vh;
    display: grid;
    justify-items: center;
    padding-top: 40px
}

div {
    margin: 24px auto
}

label {
    font-weight: bolder;
    display: block;
    margin-bottom: 4px
}
</style>
../../loadComponents/usuarioTeste