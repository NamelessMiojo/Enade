<template>
<main class="corpo">
    
    <section class="principal">

        <section class="filtros">
            <form @submit.prevent="filterListUsers">
                <div class="campo">
                    <span class="tag"> Codigo</span>
                    <input type="text" id="userId" placeholder="id" v-model="id" />
                </div>
                <div class="campo">
                    <label class="tag" for="userNomeEnade"> Nome</label>
                    <input type="text" id="userNomeEnade" placeholder="nome" v-model="nome" />
                </div>
                <div class="campo">
                    <label class="tag" for="userEmail"> E-mail</label>
                    <input type="text" id="userEmail" placeholder="email" v-model="email" />
                </div>
                <div class="campo">
                    <label class="tag" for="userTipo"> Tipo</label>
                    <select name="tipo" id="tipo" v-model="tipoUser">
                        <option v-for="uTipo in tiposUsers" :key="uTipo.id" :value="uTipo.id">{{uTipo.nome}}</option>
                    </select>
                </div>
                <button class="pesquisa"> Pesquisar </button>

            </form>
        </section>

        <hr>

        <section class="resultados">
            <div v-for="user in filterUsers" :key="user.id">
                {{user.nome}}
                {{user.email}}
                
                <button @click="editarUser(user)" >Editar</button>
                <button @click="deleteUser(user.id)">Deletar</button>
                
                <!--<editaUsuario
                @edit-text="editText"
                :textValue="textValue"
                :showModal="editMode"
                :closeModal="toggleEditMode"
                />*/-->
                <hr>
            </div>

        </section>
    </section>

    <section class="detalhes" >
        <section>
            <form ref="form" @submit.prevent="atualizarUser">
                <div>
                    <label for="userNome">Nome</label>
                    <input type="text" id="editNome" :value="alterUser.nome" :v-model="editUser.nome"/>
                </div>
                <div>
                    <label for="userEmail">E-mail</label>
                    <input type="text" id="editEmail"  :value="alterUser.email" :v-model="editUser.email"/>
                </div>
                <div>
                    <label for="userSenha">Senha</label>
                    <input type="password" id="editSenha" v-model="alterUser.senha"/>
                </div>
                <div>
                    <label for="userTipo">Tipo Usuário</label>
                    <select name="tipo" id="editTipo" :selected="alterUser.tipoUsuario.id" >
                        <option selected value="">Selecione</option>
                        <option v-for="uTipo in tiposUsers" :key="uTipo.id" :value="uTipo.id">{{uTipo.nome}}</option>
                    </select>
        
                </div>
                <button class="pesquisa" @click="resetForm">Atualizar Usuário</button>
            </form>
        </section>
        
        
        
    </section>
</main>
</template>

<script>

import axios from 'axios'
import Swal from 'sweetalert2'




export default {
    data() {
        return {
            activity: false,
            editUser:{},
            alterUser: {
                nome:'',
                email: '',
                tipoUsuario: {
                    id: ''
                }
            },
            filterUsers: [
            ],
            tiposUsers: [],
            id: '',
            nome: '',
            email: '',
            tipoUser: '',
            url: 'http://localhost:56918/usuario/',
            filtro: '',
            codigo: '',
            dismissSecs: 10,
            dismissCountDown: 0,
            showDismissibleAlert: false

        }
    },
    /*components:{
        editaUsuario,
    },
    setup(){
        const textValue = ref("Link Text");
        const editMode = ref(false);

        return { textValue, editMode };
    },*/
    methods: {
        toggleEditMode(){
            this.editMode = !this.editMode;
        },
        editText(text) {
      this.textValue = text;
    },
        /*getListUsers() {
            axios.get('http://localhost:56918/usuario/')
                .then(response => this.users = (response.data.contents))

        },*/
        resetForm() {
          this.$refs.form.reset();
        },
        filterListUsers() {
            this.filtro = '?'
            if (this.id != null && this.id != '') {
                this.filtro += 'id=' + this.id + '&';
            }
            if (this.nome != null && this.nome != '') {
                this.filtro += 'nome=' + this.nome + '&';
            }
            if (this.email != null && this.email != '') {
                this.filtro += 'email=' + this.email + '&';
            }
            if (this.tipoUser != null && this.tipoUser != '') {
                this.filtro += 'tipoUsuario=' + this.tipoUser + '&';
            }
            axios.get(this.url + this.filtro)
                .then(response => this.filterUsers = (response.data.contents))
        },
        deleteUser(userId) {
            //console.log(userId)
            Swal.fire('Deseja Remover esse usuário?')
            axios.delete(this.url + userId)
                .then(response => this.codigo = (response.data.code))
            if (this.codigo == 2) {
                Swal.fire('Usuário Não Encontrado!')
            } else {
                Swal.fire('Usuário Removido!')
            }

        },
        editarUser(usuario){
            this.alterUser=usuario
        },
        atualizarUser() {
            
            if(this.alterUser.senha !=null && this.alterUser.senha !=''){
                this.editUser.senha = this.alterUser.senha
                Swal.fire({
                    titleText: 'Deseja salvar as mudanças?',
                    showDenyButton: true, 
                    showCancelButton: true,
                    confirmButtonText: 'Salvar',
                    denyButtonText: 'Não Salvar',
                }).then((result) =>{
                    if(result.isConfirmed){
                        console.log(this.editUser.nome)
                        axios.put(this.url + this.alterUser.id, this.editUser)
                        Swal.fire('Alterado com sucesso!')
                    }else if(result.isDenied){
                        Swal.fire('Alterações descartadas')
                    }
                }
                    
                )
            }
            else{
                //<p>testando</p>
                axios.put(this.url + this.alterUser.id, this.editUser)
            }
            
                
        }

    },
    mounted() {
        //axios.get('http://localhost:56918/usuario/?nome=' + this.nome+'')
        //.then(response => this.users = (response.data.contents))
        axios.get('http://localhost:56918/tipoUsuario/')
            .then(response => this.tiposUsers = (response.data.contents))
    },
    
}
</script>

<style>
body {
    justify-content: center;
    border: 5px solid red;

}

section.principal {
    margin-top: 2%;
    margin-left: 5%;
    border: 5px solid purple;
    width: 90%;
}

section {

    padding-top: 4px;
    width: 100%;

}

section.filtros {
    justify-content: center;
    height: 10vh;
    border: 5px solid green;
    width: max-contenta;
}

section.resultados {
    display: grid;
    justify-items: center;
    height: 50vh;
    border: 5px solid blue;
}

section.filtros .pesquisa {
    border-radius: 4px;
}

section.filtros .campo {
    display: inline-flex;

    margin: 4px;
    border: 5px solid black;

}

section.filtros .tag {
    font-size: 11px;
    line-height: 15px;
    border: 3px solid grey;
}

label {
    font-weight: bolder;
    padding: 4px;
    margin-bottom: 4px
}
</style>
