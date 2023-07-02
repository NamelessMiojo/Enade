<template>
    <div>
        <div>
            <router-link :to="{name: 'TipoQuestao'}">Novo Tipo Questão</router-link> | 
            <router-link :to="{name: 'CadastroQuestao'}">Cadastrar Questao</router-link> | 
            <router-link :to="{name: 'pesquisaQuestoes'}">Consultar Questões</router-link> | 
            <router-link :to="{name: 'pesquisaTipoQuestoes'}">Consultar Tipo Questões</router-link> | 
        </div>
        <div>
            <router-view/>
        </div>
    </div>
</template>