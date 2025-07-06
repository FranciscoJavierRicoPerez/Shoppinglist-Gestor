<script setup lang="ts">
import {
  IonButton,
  IonContent,
  IonIcon,
  IonInput,
  IonItem,
  IonPopover,
} from "@ionic/vue";
const params = defineProps({
  idShoppinglistItem: {
    type: Number,
  },
});

function addItem() {
  console.log("asdad");
}
</script>
<template>
  <IonButton
    size="small"
    shape="round"
    expand="block"
    :id="'click-trigger' + params.idShoppinglistItem"
  >
    <IonIcon name="add-circle-outline"></IonIcon> Kg/€
  </IonButton>
  <IonPopover
    :trigger="'click-trigger' + params.idShoppinglistItem"
    trigger-action="click"
  >
    <IonContent class="ion-padding">
      <IonItem>
        <IonInput label="Peso en Kg" type="number" placeholder="0"></IonInput>
      </IonItem>
      <IonItem>
        <IonInput label="Precio KG/€" type="number" placeholder="0"></IonInput>
      </IonItem>
      <IonButton color="primary" size="small" expand="block" @click="addItem"
        >Añadir</IonButton
      >
    </IonContent>
  </IonPopover>
</template>
<style lang="css"></style>
