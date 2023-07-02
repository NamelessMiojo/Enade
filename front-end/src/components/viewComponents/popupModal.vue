<template>
    <div
      id="modal-container"
      v-show="isOpen"
      @mousedown="clickBG"
      class="modal-container"
    >
      <div id="modal-content">
        <slot />
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      isOpen: {
        type: Boolean,
        required: true,
      },
      onClose: {
        type: Function,
        required: true,
      },
    },
    methods: {
      clickBG(e) {
        const { id } = e.target;
        if (id === "modal-container") {
          this.onClose();
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .modal-container {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: transparent;
    z-index: 0;
    display: flex;
    justify-content: center;
    align-items: flex-start;
  }
  
  #modal-content {
    position: relative;
    top: 50%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 1.5rem;
    width: 50%;
    height: 20%;
    background-color: rgba(0, 0, 0, 0.4);
  }
  </style>