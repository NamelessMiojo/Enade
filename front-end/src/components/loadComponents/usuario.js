let axios = require('axios')
let Swal = require('sweetalert2') 

var url= 'http://localhost:56918/usuario/';
var filteredUsers=[];

let resetForm = (formulario) => {
    formulario.reset();
}

let getUsers = (filtro) =>{
    
    axios.get(url + filtro)
    .then((response) =>  
        filteredUsers = (response.data.contents)
    )
    return filteredUsers
}
let criarUser = (newUser) => {
    Swal.fire({
        titleText: 'Deseja Cadastrar Usuário?',
        showCancelButton: true,
        cancelButtonText: 'Cancelar',
        cancelButtonColor: 'grey',
        showCloseButton: true,
        confirmButtonText: 'Cadastrar',
        confirmButtonColor: 'green',
      }).then((result) => {
        if(result.isConfirmed){
          axios.post(url, newUser)
            .then((response) => {
              if (response.data.code == '2') {
                Swal.fire('Erro!')
              } else if (response.data.code == '1') {
                Swal.fire('Usuário Cadastrado!')
              }
            })
        }
      })    
}

let deleteUser = (userId) => {
    var codigo;
    Swal.fire('Deseja Remover esse usuário?')
            axios.delete(url + userId)
                .then(response => codigo = (response.data.code))
            if (codigo == 2) {
                Swal.fire('Usuário Não Encontrado!')
            } else {
                Swal.fire('Usuário Removido!')
            }
}

var clusterCadastro = {
    criarUser: criarUser,
    resetForm: resetForm,
    getUsers: getUsers, 
    deleteUser: deleteUser,  
}

export default clusterCadastro;