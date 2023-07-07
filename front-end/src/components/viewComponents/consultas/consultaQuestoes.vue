<template>
<section class="corpo">

    <section class="principal">

        <section class="filtros">
            <form @submit.prevent="filterListQuestoes">
                <div class="campo">
                    <label class="tag">Código</label>
                    <input type="text" id="questaoId" placeholder="Código" v-model="id">
                </div>
                <div class="campo">
                    <label class="tag" for="enunciado">Enunciado</label>
                    <input type="text" id="enunciado" placeholder="Enunciado" v-model="descricao" />
                </div>
                <div class="campo">
                    <label>Estado</label>
                </div>
                <div class="campo">
                    <label>Tipo Questão</label>
                    <select name="tipo" id="tipo" v-model="tipoQuestao">
                        <option v-for="qTipo in tiposQuestoes" :key="qTipo.id" :value="qTipo.id">{{qTipo.id}}</option>
                    </select>
                </div>
                <button class="pesquisa"> Pesquisar </button>
            </form>
        </section>

        <section class="resultados">
            <div v-for="questao in filterQuestoes" :key="questao.id">
                {{ questao.id}}
                {{ questao.tipoQuestao.nome }}
                {{ questao.descricao}}
                {{ questao.questaoCorreta}}
                <button>Editar</button>
                <button @click="deleteQuestao(questao.id)">Remover</button>
            </div>
        </section>
    </section>
</section>
</template>

<script>
import axios from 'axios'
import Swal from 'sweetalert2'

export default {
    data() {
        return {
            id: '',
            tipoQuestao: '',
            descricao: '',
            estado: '',
            filterQuestoes: [],
            tiposQuestoes: [],
            url: 'http://localhost:56918/questao/',
            filtro: '',

        }
    },
    methods: {
        resetForm() {
            this.$refs.form.reset();
        },
        filterListQuestoes() {
            this.filtro = '?'
            if (this.id != null && this.id != '') {
                this.filtro += 'id=' + this.id + '&'
            }
            if (this.descricao != null && this.descricao != '') {
                this.filtro += 'descricao=' + this.descricao + '&'
            }
            if (this.estado != null && this.estado != '') {
                this.filtro += 'estado=' + this.estado + '&'
            }
            if (this.tipoQuestao != null && this.tipoQuestao != '') {
                this.filtro += 'tipoQuestao=' + this.tipoQuestao + '&'
            }
            axios.get(this.url + this.filtro)
                .then(response => this.filterQuestoes = (response.data.contents))
        },
        deleteQuestao(questaoId) {
            Swal.fire({
                titleText: 'Deseja remover essa questao?',
                showCancelButton: true,
                cancelButtonText: 'Cancelar',
                cancelButtonColor: 'grey',
                showCloseButton: true,
                confirmButtonText: 'Excluir',
                confirmButtonColor: 'red',

            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete(this.url + questaoId)
                        .then((response) => {
                            if (response.data.code == '2') {
                                Swal.fire('Erro: Elemento não encontrado!')
                            } else if (response.data.code == '1') {
                                Swal.fire('Exclusão bem sucedida!')
                            }
                        })
                }
            })
        },
        atualizaQuestao() {

        }

    }
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
