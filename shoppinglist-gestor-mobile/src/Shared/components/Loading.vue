<script setup lang="ts">
import { IonContent, IonItem, IonLabel, IonSpinner } from "@ionic/vue";

const props = defineProps({
  loading: {
    type: Boolean,
    default: false,
  },
});
</script>
<template>
  <div v-if="loading">
    <IonItem>
      <IonLabel>Cargando</IonLabel>
      <IonSpinner></IonSpinner>
    </IonItem>
  </div>
</template>
<style lang="css"></style>
