import axios from 'axios'

export default {
  data(){
    return{
      postUser: {nome: '', email: '', senha: '' , tipoUsuario:{id: ''}},
      users: [],
      tiposUsers: [],
    }
  },
  methods: {
    createPost(){
      axios.post('http://localhost:56918/usuario/', this.postUser)
    },
    createGet(){
        axios.get('http://localhost:56918/tipoUsuario/')
    .then(response => this.tiposUsers = (response.data.contents))
    }
  },
  mounted(){
    axios.get('http://localhost:56918/usuario/')
      .then(response => this.users = (response.data.contents))
    
    axios.get('http://localhost:56918/tipoUsuario/')
      .then(response => this.tiposUsers = (response.data.contents))
  }
}; export{tipoUsers};