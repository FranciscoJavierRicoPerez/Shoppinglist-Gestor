<script setup lang="ts">
import {
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
} from "@ionic/vue";

defineProps({
    title: String,
    message: String
})

</script>
<template>
  <IonCard style="background-color: rgb(51, 255, 153);">
    <IonCardHeader>
      <IonCardTitle>INFORMACIÓN - {{ title }}</IonCardTitle>
    </IonCardHeader>
    <IonCardContent style="font-size: medium;"> {{ message }} </IonCardContent>
  </IonCard>
</template>
<style lang="css"></style>
