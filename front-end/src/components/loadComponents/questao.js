import axios from 'axios'

export default {
    data(){
      return{
        newQuestao: {alternativaA: '', alternativaB: '', alternativaC: '', alternativaD: '', alternativaE: '', email: '', senha: '' , tipoUsuario:{id: ''}},
        questoes: [],
        tiposQuestao: [],
        questao: {},
        id,
      }
    },
    methods: {
      postUser(){
        axios.post('http://localhost:56918/usuario/', this.newUser)
      },
      getListUsers(){
          axios.get('http://localhost:56918/usuario/')
      .then(response => this.users = (response.data.contents))
      },
      //talvez
      deleteUser(){
        axios.delete('', this.user)
      },
      getUser(){
        axios.get('', + this.id).then(response => this.user = (response.data))
      },
      attUser(){
        axios.put('', )
      }
    },
    mounted(){
      
        axios.get('http://localhost:56918/questoes/')
        .then(response => this.users = (response.data.contents))
      //}
      axios.get('http://localhost:56918/tipoQuestao/')
      .then(response => this.tiposUsers = (response.data.contents))
      
      
    }
  };
  