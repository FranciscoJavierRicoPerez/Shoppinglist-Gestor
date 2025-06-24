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
  <IonCard>
    <IonCardHeader>
      <IonCardTitle>INFORMACIÓN - {{ title }}</IonCardTitle>
    </IonCardHeader>
    <IonCardContent> {{ message }} </IonCardContent>
  </IonCard>
</template>
<style lang="css"></style>
