<script setup lang="ts">
import Card from 'primevue/card'
import Button from 'primevue/button'
import type { PropType } from 'vue'
import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'

defineProps({
  shoppinglist: {
    type: Object as PropType<Shoppinglist>,
    default: () => null,
  },
})
</script>
<template>
  <Card class="card-general">
    <template #title>{{ shoppinglist.code }}</template>
    <template #subtitle>{{ shoppinglist.creationDate }}</template>
    <template #content>
      <p>{{ shoppinglist.totalPrice }}</p>
      <p>{{ shoppinglist.isActive }}</p>
    </template>
    <template #footer>
      <Button label="Archivar" severity="help"></Button>
      <Button class="buttons-separation" label="Ver" severity="info"></Button>
      <Button class="buttons-separation" label="Eliminar" severity="danger"></Button>
    </template>
  </Card>
</template>
<style lang="css">
.card-general {
  margin-right: 1rem;
  margin-top: 10px;
  max-width: 20rem;
  background-color: aliceblue !important;
}
.buttons-separation {
  margin-left: 1rem;
}
</style>
