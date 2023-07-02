<template>
    <div></div>
</template>

<script>

//import axios from 'axios'
//import Swal from 'sweetalert2'

export default {
    data(){
        return{

        }
    },
    methods: {
        resetForm() {
          this.$refs.form.reset();
        },
        filterListQuestoes(){

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
    