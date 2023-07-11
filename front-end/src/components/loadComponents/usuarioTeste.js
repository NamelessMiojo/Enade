let axios = require('axios')
let Swal = require('sweetalert2') 
// limpar componentes js e compartimentar os componentes view


export default {
  data() {
    return {
      
      users: [],
      filterUsers: [
      ],
      tiposUsers: [],
      user: {},
      url: 'http://localhost:56918/usuario/',
      urlTipo: 'http://localhost:56918/tipoUsuario/',
      filtro: '',
      codigo: '',
    }
  },
  methods: {
    resetForm() {
      this.$refs.form.reset();
    },
    getFilterUsers() {
      this.filtro = '?'
      if (this.id != null && this.id != '') {
        this.filtro += 'id=' + this.id + '&';
      }
      if (this.nome != null && this.nome != '') {
        this.filtro += 'nome=' + this.nome + '&';
      }
      if (this.email != null && this.email != '') {
        this.filtro += 'email=' + this.email + '&';
      }
      if (this.tipoUser != null && this.tipoUser != '') {
        this.filtro += 'tipoUsuario=' + this.tipoUser + '&';
      }
      axios.get(this.url + this.filtro)
        .then(response => this.filterUsers = (response.data.contents))
    },
    deleteUser(userId) {
      Swal.fire({
        titleText: 'Deseja Remover esse usuário?',
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        cancelButtonColor: 'grey',
        showCloseButton: true,
        confirmButtonText: 'Excluir',
        confirmButtonColor: 'red',
      }).then((result) => {
        if (result.isConfirmed) {
          axios.delete(this.url + userId)
            .then((response) => {
              if (response.data.code == '2') {
                Swal.fire('Erro: Usuário Não Encontrado!')
              } else if (response.data.code == '1') {
                Swal.fire('Usuário Removido!')
              }
            })
        }
      })
    },
    criarUser(newUser) {
      Swal.fire({
        titleText: 'Deseja Cadastrar Usuário?',
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        cancelButtonColor: 'grey',
        showCloseButton: true,
        confirmButtonText: 'Excluir',
        confirmButtonColor: 'red',
      }).then((result) => {
        if(result.isConfirmed){
          axios.post(this.url, newUser)
            .then((response) => {
              if (response.data.code == '2') {
                Swal.fire('Erro!')
              } else if (response.data.code == '1') {
                Swal.fire('Usuário Cadastrado!')
              }
            })
        }
      })    
  },


   
    getListUsers() {
      axios.get('http://localhost:56918/usuario/')
        .then(response => this.users = (response.data.contents))
    },





    getUser() {
      axios.get('', + this.id).then(response => this.user = (response.data))
    },
    attUser() {
      axios.put('',)
    }
  },
  mounted() {
    axios.get(this.urlTipo)
    .then(response => this.tiposUsers = (response.data.contents))
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
    //axios.get('http://localhost:56918/usuario/')
      //.then(response => this.users = (response.data.contents))
    //}

   
    //axios.put('http://localhost:56918/usuario/1', {
    //nome: 'Arthur'
    //})
    //.then(response => console.log(response))
    //axios.delete('http://localhost:56918/usuario/6')
    //.then(response => console.log(response))

  }
};







