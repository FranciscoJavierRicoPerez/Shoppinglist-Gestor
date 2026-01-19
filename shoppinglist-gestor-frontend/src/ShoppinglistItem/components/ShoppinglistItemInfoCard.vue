<script setup lang="ts">
import { onMounted, type PropType } from 'vue'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import Card from 'primevue/card'
import Button from 'primevue/button'
import Tag from 'primevue/tag'
const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})
</script>
<template>
  <div>
    <Card
      :class="{
        'bg-green-300': shoppinglistItem.calculateSystemCode === 'WP',
        'bg-green-200': shoppinglistItem.calculateSystemCode === 'UP',
      }"
    >
      <template #header>
        <div class="ml-3 mt-2 text-2xl text-left font-italic">{{ shoppinglistItem.name }}</div>
      </template>
      <template #subtitle>
        <Tag severity="info" rounded> Coste producto: {{ shoppinglistItem.calculatedPrice }} </Tag>
      </template>
      <template #content>
        <div class="flex flex-row gap-2 justify-content-start">
          <Tag severity="warn" rounded>
            Añadido el: {{ shoppinglistItem.assignationToLisDate }}
          </Tag>
          <Tag rounded class="bg-indigo-300 text-white"
            >Sistema de calculo: {{ shoppinglistItem.calculateSystemCode }}</Tag
          >
        </div>
      </template>
      <template #footer>
        <div class="flex flex-row gap-2 justify-content-start">
          <!-- ESTE PRIMER BOTON DEBE DE VARIAR EN FUNCION DE LOS DE UP Y WP Y ABRIR EL MODAL PARA AÑADIR EL ITEM UNIT-->
          <Button class="w-full" severity="info" label="KG/€" raised></Button>
          <Button class="w-full" severity="danger" label="Borrar" raised></Button>
        </div>
      </template>
    </Card>
  </div>
</template>
<style lang="css">
.wp-card-background {
  background-color: rgb(242, 238, 207) !important;
}
.up-card-background {
  background-color: rgb(252, 221, 201) !important;
}
</style>
