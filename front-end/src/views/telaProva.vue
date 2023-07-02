<template>
    <div>
        <div>
            <router-link :to="{ name: 'CadastroProva'}">Cadastrar Prova</router-link> | 
            <router-link :to="{ name: ''}">Consultar Provas</router-link> | 
            <router-link :to="{ name: ''}">Avaliar Prova</router-link> | 
            <router-link :to="{ name: ''}"></router-link> | 
            <router-link :to="{ name: ''}"></router-link> | 
        </div>
        <div>
            <router-view/>
        </div>
    </div>
</template>