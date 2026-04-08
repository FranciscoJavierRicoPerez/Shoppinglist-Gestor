<script setup lang="ts">
import Card from 'primevue/card'
import Button from 'primevue/button'
import Tag from 'primevue/tag'
import type { ItemUnitUpMetadata } from '@/ItemUnit/domain/ItemUnitUpMetadata'
import { computed, type PropType } from 'vue'
import { useItemUnitUpGroupedByPriceStore } from '../store/itemUnitUpGroupedByPriceStore'
import { useUpdateItemUnitUpdateMetadataStore } from '../store/upItemUnitUpdateMetadataStore'
import type { RequestUpItemUnitUpUpdateMetadata } from '../infrastructure/models/request/RequestUpItemUnitUpdateMetadata'

const props = defineProps({
  itemUnitUpMetadata: {
    type: Object as PropType<ItemUnitUpMetadata>,
    default: () => null,
  },
})

// NECESITARE 2 STORE
/**
 * STORE DEL GROUPED EN EL CUAL ESTARA EL NUEVO TOTAL QUE SE IRA ACTUALIZANDO Y UNA LISTA DE ITEMUNITUPMETADATA
 * STORE DEL ITEMUNITUPMETADATA EL CUAL USARE PARA CARGAR LA INFORMACION Y PARA ACTUALIZAR EN EL SUPERIOR
 *
 * Y EL SUPERIOR SERA EL QUE UTILIZARE PARA CREAR EL OBJETO DE PETICION DE ACTUALIZACIPON
 */

// Añadir aqui un store para actualizar el estado del itemUnitMetadata

const store = useItemUnitUpGroupedByPriceStore()
const upItemUnitUpdateMetadataStore = useUpdateItemUnitUpdateMetadataStore()

const calculatedPriceText = computed(() => {
  return 'Calculado: ' + props.itemUnitUpMetadata.calculatedPrice
})

const quantityText = computed(() => {
  return 'Cantidad: ' + props.itemUnitUpMetadata.quantity
})

const priceText = computed(() => {
  return 'Precio unitario: ' + props.itemUnitUpMetadata.price
})

function removeItem(): void {
  store.updateItemsGroupedList(store.removeItem(props.itemUnitUpMetadata.price))
  store.updateTotalPrice()
  createRequestUpItemUpdateMetadata(true, null)
}

function updateQuantity(add: boolean): void {
  if (add) {
    props.itemUnitUpMetadata.quantity++
  } else {
    props.itemUnitUpMetadata.quantity--
    if (props.itemUnitUpMetadata.quantity <= 0) {
      props.itemUnitUpMetadata.quantity = 0
    }
  }
  props.itemUnitUpMetadata.calculatedPrice =
    props.itemUnitUpMetadata.price * props.itemUnitUpMetadata.quantity
  store.updateTotalPrice()
  createRequestUpItemUpdateMetadata(false, add ? null : true)
}

function createRequestUpItemUpdateMetadata(
  removeOperation: boolean,
  reduceOperation: boolean | null,
) {
  let requestData: RequestUpItemUnitUpUpdateMetadata = {
    idItemUnit: props.itemUnitUpMetadata.idItemUnit,
    idItemUnitUp: props.itemUnitUpMetadata.idItemUnitUp,
    newQuantity: props.itemUnitUpMetadata.quantity,
    removeItemUnitUp: removeOperation,
    reduceOperation: reduceOperation,
  }
  // NO RECUERDO EXACTAMENTE EL MOTIVO DE ESTO :S
  /* upItemUnitUpdateMetadataStore.removeOldValue(
    props.itemUnitUpMetadata.idItemUnit,
    props.itemUnitUpMetadata.idItemUnitUp,
  ) */
  upItemUnitUpdateMetadataStore.add(requestData)
}
</script>
<template>
  <Card>
    <template #title
      ><Tag class="w-full">{{ calculatedPriceText }}</Tag></template
    >
    <template #content>
      <div class="flex flex-row gap-2">
        <Tag class="w-full">{{ quantityText }}</Tag>
        <Tag class="w-full">{{ priceText }}</Tag>
      </div>
    </template>
    <template #footer>
      <div class="flex flex-row gap-2">
        <Button class="w-full" severity="primary" label="+" @click="updateQuantity(true)"></Button>
        <Button class="w-full" severity="warn" label="-" @click="updateQuantity(false)"></Button>
        <Button class="w-full" severity="danger" label="X" @click="removeItem()"></Button>
      </div>
    </template>
  </Card>
</template>
<style lang="css"></style>
