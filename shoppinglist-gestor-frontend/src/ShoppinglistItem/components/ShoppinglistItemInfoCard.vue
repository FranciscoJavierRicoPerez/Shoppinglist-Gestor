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
  <div class="d-flex flex-column">
    <Card
      :class="{
        'wp-card-background': shoppinglistItem.calculateSystemCode === 'WP',
        'up-card-background': shoppinglistItem.calculateSystemCode === 'UP',
      }"
    >
      <template #header>
        <h5 style="margin-left: 1rem; margin-top: 10px">{{ shoppinglistItem.name }}</h5>
      </template>
      <template #subtitle>
        <Tag severity="info" rounded> Coste producto: {{ shoppinglistItem.calculatedPrice }} </Tag>
      </template>
      <template #content>
        <div class="d-flex flex-row justify-content-start">
          <Tag severity="warn" rounded style="margin-right: 0.5rem">
            Añadido el: {{ shoppinglistItem.assignationToLisDate }}
          </Tag>
          <Tag severity="success" rounded
            >Sistema de calculo: {{ shoppinglistItem.calculateSystemCode }}</Tag
          >
        </div>
      </template>
      <template #footer>
        <div class="d-flex flex-row justify-content-start flex-wrap">
          <!-- ESTE PRIMER BOTON DEBE DE VARIAR EN FUNCION DE LOS DE UP Y WP Y ABRIR EL MODAL PARA AÑADIR EL ITEM UNIT-->
          <Button
            severity="info"
            label="KG/€"
            size="small"
            style="width: 45%; margin-right: 1rem"
            raised
          ></Button>
          <Button severity="danger" label="Borrar" size="small" style="width: 45%" raised></Button>
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
