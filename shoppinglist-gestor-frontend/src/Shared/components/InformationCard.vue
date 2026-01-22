<script setup lang="ts">
import Card from 'primevue/card'
</script>
<template>
  <Card id="informationCard" class="flex w-full bg-red-200">
    <template #header>
      <div class="text-2xl ml-3 mt-2">Información</div>
    </template>
    <template #content>
      <div class="text-xl">No hay informacion suficiente, para mostrar información de resumen</div>
    </template>
  </Card>
</template>
