<template>
    <main class="corpo">
        <section class="principal">
            <section class="filtros">
                <form @submit.prevent="filterListTipoUser">
                    <div class="campo">
                        <label class="tag" for="tipoId">Código</label>
                        <input type="text" id="tipoId" placeholder="código" v-model="id"/>
                    </div>
                    <div class="campo">
                        <label class="tag" for="tipoNome">Nome</label>
                        <input type="text" id="tipoNome" placeholder="nome" v-model="nome"/>
                    </div>
                    <button class="pesquisa"> Pesquisar </button>
                </form>
            </section>

            <hr>

            <section class="resultados">
                <div v-for="uTipo in filterTipoUsers" :key="uTipo.id">
                    {{ uTipo.nome }}
                    <button @click="editarTipoUser">Editar</button>
                    <button @click="deleteTipoUser(uTipo.id)">Deletar</button>
                </div>
            </section>
        </section>

        <section class="detalhes">
            <section>
                <form ref="form" @submit.prevent="atualizarTipoUser">
                    
                    <div class="campo">
                        <label class="tag" for="tipoNome">Nome</label>
                        <input type="text" id="tipoNome" placeholder="nome" v-model="edit.nome"/>
                    </div>
                    <button class="pesquisa" @click="resetForm"> Atualizar </button>
                </form>
            </section>
        </section>
    </main>
</template>

<script>
import axios from 'axios'
import Swal from 'sweetalert2'

export default {
    data(){
        return{
            edit:{

            },
            filterTipoUsers: [],
            id:'',
            nome:'',
            url:'http://localhost:56918/tipoUsuario/',
            filtro:'',
        }
    },
    methods: {
        resetForm(){
          this.$refs.form.reset()
        },
        filterListTipoUser(){
            this.filtro='?'
            if (this.id != null && this.id != '') {
                this.filtro += 'id=' + this.id + '&';
            }
            if (this.nome != null && this.nome != '') {
                this.filtro += 'nome=' + this.nome + '&';
            }
            axios.get(this.url + this.filtro)
            .then(response => this.filterTipoUsers = (response.data.contents))
        },
        deleteTipoUser(tipoId){
            Swal.fire({
                    titleText: 'Deseja excluir?',
                    showDenyButton: true, 
                    confirmButtonText: 'Excluir',
                    denyButtonText: 'Cancelar',
                }).then((result) =>{
                    if(result.isConfirmed){
                        axios.delete(this.url + tipoId)
                        .then(Swal.fire('Deletado com sucesso!'))                        
                        
                    }else if(result.isDenied){
                        Swal.fire('Alterações descartadas')
                    }
                }
                    
                )
        }
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
