<script setup lang="ts">
import { IonHeader, IonTitle } from "@ionic/vue";

const props = defineProps({
  title: {
    type: String,
    default: ''
  }
})
</script>
<template>
  <IonHeader class="custom-header">
    <IonTitle class="custom-text-header">{{ props.title }}</IonTitle>
  </IonHeader>
</template>
<style lang="css">
.custom-header {
  background-color: rgb(51, 255, 153);
  margin-bottom: 5px;
  height: 75px;
}
.custom-text-header {
  text-align: center;
  font-size: large;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  font-style: italic;
  padding: 2rem;
}
</style>
