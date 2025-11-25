import { defineStore } from "pinia";
import { ref } from "vue";

interface ToastData {
  open: boolean;
  isInformation: boolean;
  message: string;
}

export const useToastStore = defineStore("toastStore", () => {
  const toastData = ref<ToastData>();

  // Función para mostrar el Toast con los valores que queramos
  function show(open: boolean, isInformation: boolean, message: string) {
    let toastDataAux: ToastData = {
      open: open,
      isInformation: isInformation,
      message: message,
    };
    toastData.value = toastDataAux;
  }

  // Funcion para reiniciar el toast una vez que se ha terminado de mostrar
  function reset() {
    let toastDataAux: ToastData = {
      open: false,
      isInformation: false,
      message: "",
    };
    toastData.value = toastDataAux;
  }

  return {
    toastData,
    show,
    reset
  };
});
