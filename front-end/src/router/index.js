import { createRouter, createWebHistory } from 'vue-router'
import NotFound from  '../views/telaNotFound.vue'


const router = createRouter({
  history: createWebHistory(),
  routes: [
    
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/telaInicio.vue')
        
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/telaLogin.vue')
    },
    {
        path: '/realizarProva',
        name: 'Realizar',
        componente: () => import('../views/telaQuestionario.vue')
    },
    {
        path: '/usuarios',
        name: 'Usuarios',
        props: true,
        component: () => import('../views/telaUsuarios.vue'),
        children: [
            {
                path: '/cadastroUser',
                name: 'CadastroUser',
                component: () => import('../components/viewComponents/cadastros/cadastroUsuario.vue')   
            },
            {
                path: '/consultaUsuario',
                name: 'ConsultaUsuario',
                component: () => import('../components/viewComponents/consultas/consultaUsuario.vue'),
                children:[
                    {
                        path:'/atualizaUser',
                        name: 'AtualizaUsuario',
                        props: true,
                        component: () => import('../components/viewComponents/controler/atualizaUsuario.vue'),
                        
                    }
                ]
            },
            {
                path: '/novoTipoUser',
                name: 'TipoUser',
                component: () => import('../components/viewComponents/cadastros/cadastroTipoUsuario.vue'),
            },
            {
                path: '/consultaTipoUser',
                name: 'ConsultaTipoUsuario',
                component: () => import('../components/viewComponents/consultas/consultaTipoUsuario.vue'),
            },
            
        ]
    },
    {
        path: '/questao',
        name: 'Questao',
        component: () => import('../views/telaQuestoes.vue'),
        children: [
            {
                path: '/novoTipoQuestao',
                name: 'TipoQuestao',
                component: () => import('../components/viewComponents/cadastros/cadastroTipoQuestao.vue')
            },
            {
                path: '/novaQuestao',
                name: 'CadastroQuestao',
                component: () =>import('../components/viewComponents/cadastros/cadastroQuestao.vue')
            },
            
            {
                path: '/pesquisaquestoes',
                name: 'pesquisaQuestoes',
                component: () => import('../components/viewComponents/consultas/consultaQuestoes.vue')
            },
            {
                path: '/pesquisaTipoQuestoes',
                name: 'pesquisaTipoQuestoes',
                component: () => import('../components/viewComponents/consultas/consultaTipoQuestao.vue')
            }
        ]
    },
    {
        path: '/prova',
        name: 'Prova',
        component: () => import('../views/telaProva.vue'),
        children: [
            {
                path: '/novaProva',
                name: 'CadastroProva',
                component: () =>import('../components/viewComponents/cadastros/cadastroProva.vue')
            },
        ]
    },
    
    
    
    
    
    {
        path: '/:catchAll(.*)',
        name : '404Error',
        component : NotFound

    },
    


  ]
})

export default router
