<template>
  <ion-app>
    <ion-router-outlet />
    <Toast
      :is-information="toastStore.toastData?.isInformation"
      :message="toastStore.toastData?.message"
      :open="toastStore.toastData?.open"
    ></Toast>
  </ion-app>
</template>

<script setup lang="ts">
import Toast from "@/Shared/components/Toast.vue";
import { useToastStore } from "@/Shared/stores/toastStore";
import { IonApp, IonRouterOutlet } from "@ionic/vue";

const toastStore = useToastStore();
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
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  font-style: italic;
  margin-top: 1rem;
}
</style>
