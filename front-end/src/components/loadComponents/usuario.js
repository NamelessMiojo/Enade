import axios from 'axios'

export default {
  data(){
    return{
      newUser: {nome: '', email: '', senha: '' , tipoUsuario:{id: ''}},
      users: [],
      tiposUsers: [],
      user: {},
      
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
    /*function addUsuario(){
      axios.post('http://localhost:56918/usuario/',{
      nome: 'savio',
      email: 'savio@gmail.com',
      senha: '456',
      tipoUsuario: { id: 1}
    },
    )
    }*/
    //function getUsuario(){
      axios.get('http://localhost:56918/usuario/')
      .then(response => this.users = (response.data.contents))
    //}
    axios.get('http://localhost:56918/tipoUsuario/')
    .then(response => this.tiposUsers = (response.data.contents))
    //axios.put('http://localhost:56918/usuario/1', {
      //nome: 'Arthur'
    //})
    //.then(response => console.log(response))
    //axios.delete('http://localhost:56918/usuario/6')
    //.then(response => console.log(response))
    
  }
};



