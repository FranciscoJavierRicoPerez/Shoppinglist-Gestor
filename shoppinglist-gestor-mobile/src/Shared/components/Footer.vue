<script setup lang="ts">
import { IonFooter, IonTitle } from '@ionic/vue';

</script>
<template>
    <IonFooter class="custom-footer">
      <IonTitle class="custom-text">v0.0.0</IonTitle>
    </IonFooter>
</template>
<style lang="css">
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
