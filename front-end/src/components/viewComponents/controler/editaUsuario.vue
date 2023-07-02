<template>
    <Modal :isOoen="showModal" :onClose="closeModal">
        <h2>Altere o Usuário</h2>
        <input v-model="text"/>
        <button class="modal-close" @click="closeModal">Alterar</button>
    </Modal>
</template>

<script>
import {computed} from 'vue'
import Modal from '../popupModal.vue'

export default {
    components: {
        Modal,
    }, 
    props:{
        textValue: {
            type: String,
            required: true,

        },
        showModal: {
      type: Boolean,
      required: true,
    },
    closeModal: {
      type: Function,
      required: true,
    },
    },
    setup(props, {emit}){
        const text = computed({
            get(){
                return props.textValue;
            },
            set(text){
                emit("edit-text"), text;
            },
        });
        return {text};
    },
};


</script>
<style scoped></style>