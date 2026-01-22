<script setup lang="ts">
import { computed, onMounted, type PropType } from 'vue'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import Card from 'primevue/card'
import Button from 'primevue/button'
import Tag from 'primevue/tag'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ToastMessageOptions } from 'primevue/toast'
import { useToast } from 'primevue/usetoast'

/** --- PROPS SECTIONS --- */
const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})
/** ---------------------- */

/** ---- STORE SECTION ---- */
const shoppinglistDetailsStore = useShoppinglistDetailStore()
/** ----------------------- */

const toast = useToast()

/** ---- COMPUTED SECTION ---- */
const shoppinglistItemPriceText = computed(() => {
  return 'Coste producto: ' + props.shoppinglistItem.calculatedPrice + '€'
})

const shoppinglistItemAssignationToListDateText = computed(() => {
  return 'Añadido el: ' + props.shoppinglistItem.assignationToLisDate
})

const shoppinglistItemCalculateSystemText = computed(() => {
  return 'Sistema de calculo: ' + props.shoppinglistItem.calculateSystemCode
})

const shoppinglistItemNameText = computed(() => {
  return props.shoppinglistItem.name
})
/** ---- END COMPUTED SECTION ---- */

function createToast(toastOptions: ToastMessageOptions) {
  toast.add({
    severity: toastOptions.severity,
    summary: toastOptions.summary,
    detail: toastOptions.detail,
    life: toastOptions.life,
  })
}

function removeShoppinglistItem(id: number): void {
  console.log('INFO: Borrando el shoppinglist item con id : ' + id)

  // IMPLEMENTACION LLAMANDO AL BACKEND

  const response: boolean = true
  if (response) {
    shoppinglistDetailsStore.updateItemsList(shoppinglistDetailsStore.removeItem(id))
    createToast({
      severity: 'success',
      summary: 'Se ha borrado el producto ' + props.shoppinglistItem.name,
      detail: 'El producto ' + props.shoppinglistItem.name + ' se ha borrado correctamente',
      life: 3000,
    })
  } else {
    createToast({
      severity: 'danger',
      summary: 'Error en el borrado del producto ' + props.shoppinglistItem.idShoppinglistItem,
      detail: 'No ha podido borrarse el producto: ' + props.shoppinglistItem.idShoppinglistItem,
      life: 3000,
    })
  }
}
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
        <div class="ml-3 mt-2 text-2xl text-left font-italic">{{ shoppinglistItemNameText }}</div>
      </template>
      <template #subtitle>
        <Tag severity="info" rounded>{{ shoppinglistItemPriceText }} </Tag>
      </template>
      <template #content>
        <div class="flex flex-row gap-2 justify-content-start">
          <Tag severity="warn" rounded>
            {{ shoppinglistItemAssignationToListDateText }}
          </Tag>
          <Tag rounded class="bg-indigo-300 text-white">{{
            shoppinglistItemCalculateSystemText
          }}</Tag>
        </div>
      </template>
      <template #footer>
        <div class="flex flex-row gap-2 justify-content-start">
          <!-- ESTE PRIMER BOTON DEBE DE VARIAR EN FUNCION DE LOS DE UP Y WP Y ABRIR EL MODAL PARA AÑADIR EL ITEM UNIT-->
          <Button class="w-full" severity="info" label="KG/€" raised></Button>
          <Button
            class="w-full"
            severity="danger"
            label="Borrar"
            raised
            @click="removeShoppinglistItem(shoppinglistItem.idShoppinglistItem)"
          ></Button>
        </div>
      </template>
    </Card>
  </div>
</template>
