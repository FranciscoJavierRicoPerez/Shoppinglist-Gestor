<template>
  <ion-app>
    <IonHeader class="custom-header">
      <IonTitle class="custom-text">Gestor de Listas de Compra</IonTitle>
    </IonHeader>
    <ion-router-outlet />
    <IonFooter class="custom-footer">
      <IonTitle class="custom-text">Francisco Javier Rico Pérez - 2025/2026 v0.0.1</IonTitle>
    </IonFooter>
  </ion-app>
</template>

<script setup lang="ts">
import { IonApp, IonFooter, IonHeader, IonRouterOutlet, IonTitle } from '@ionic/vue';
</script>

<style scoped>
.custom-header {
  background-color: rgb(51, 255, 153);
  margin-bottom: 5px;
  height: 50px;
}
.custom-footer {
  background-color: rgb(51, 255, 153);
  margin-top: 10px;
  height: 50px;
}
.custom-text {
  text-align: center;
  font-size: large;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-style: italic;
  margin-top: 1rem;
}
</style>
