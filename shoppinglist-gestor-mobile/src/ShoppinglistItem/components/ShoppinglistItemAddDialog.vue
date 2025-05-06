<script setup lang="ts">
import ProductSelector from "@/Product/components/ProductSelector.vue";
import {
  IonButton,
  IonButtons,
  IonContent,
  IonHeader,
  IonModal,
  IonTitle,
  IonToolbar,
} from "@ionic/vue";

defineProps({
  openModal: {
    type: Boolean,
  },
});

const emit = defineEmits(["updateModalOpenValue"]);

function closeModal() {
  emit("updateModalOpenValue");
}
</script>
<template>
  <IonModal :is-open="openModal">
    <IonHeader>
      <IonToolbar>
        <IonTitle class="custom-text-header">Añadir Item</IonTitle>
        <IonButtons slot="end">
          <IonButton @click="closeModal()">Cerrar</IonButton>
        </IonButtons>
      </IonToolbar>
    </IonHeader>
    <IonContent>
      <ProductSelector></ProductSelector>
    </IonContent>
  </IonModal>
</template>
<style lang="css">
.custom-text-header {
  text-align: center;
  font-size: large;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  font-style: italic;
  padding: 2rem;
}
ion-toolbar::part(container) {
  background-color: rgb(51, 255, 153); 
}

</style>
