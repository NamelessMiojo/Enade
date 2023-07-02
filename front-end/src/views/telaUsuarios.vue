<template>
<div>
    <div>
        <router-link :to="{ name: 'CadastroUser' }">Cadastrar Novo Usuario</router-link> |
        <router-link :to="{ name: 'ConsultaUsuario'}">Consultar Usuarios</router-link> | 
        <router-link :to="{ name: 'TipoUser'}">Novo Tipo Usuário</router-link> | 
        <router-link :to="{ name: 'ConsultaTipoUsuario'}">Consultar Tipo Usuarios</router-link> | 
    </div>
    <div>
        <router-view/>
    </div>
    <div>
        
    </div>
</div>
    



    


</template>

<style>

section.content{
    display: flex;
    justify-content: center;
    justify-self: center;
    justify-items: center;
    flex-wrap: wrap;
    padding-top: 20px;
    border: 5px solid orange;
    width: 100%;
}
section.header{
    border: 5px solid yellowgreen;
    justify-content: left;
}
</style>