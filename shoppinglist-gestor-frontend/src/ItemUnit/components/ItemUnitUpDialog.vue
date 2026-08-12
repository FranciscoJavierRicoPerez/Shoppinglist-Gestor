<script setup lang="ts">
import Dialog from 'primevue/dialog'
import Tag from 'primevue/tag'
import { computed, ref, type PropType } from 'vue'
import Button from 'primevue/button'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import Divider from 'primevue/divider'
import { useItemUnitUpGroupedByPriceStore } from '../store/itemUnitUpGroupedByPriceStore'
import ItemUnitUpCreateForm from './ItemUnitUpCreateForm.vue'
import ItemUnitUpUpdateForm from './ItemUnitUpUpdateForm.vue'
import { useUpdateItemUnitUpValues } from '../application/useUpdateItemUnitUpValues'
import type { RequestUpdateShoppinglistItemItemUnitsUp } from '../infrastructure/models/request/RequestUpdateShoppinglistItemItemUnitsUp'
import { useUpdateItemUnitUpdateMetadataStore } from '../store/upItemUnitUpdateMetadataStore'
import { useUpdateShoppinglistItemCalculatedPrice } from '@/ShoppinglistItem/application/useUpdateShoppinglistItemCalculatedPrice'
import { useUpdateShoppinglistTotalPrice } from '@/Shoppinglist/application/useUpdateShoppinglistTotalPrice'
import { useRoute } from 'vue-router'
import { C } from 'vue-router/dist/router-CWoNjPRp.mjs'

const visible = ref<boolean>(false)

const store = useItemUnitUpGroupedByPriceStore()

const requestUpdateUpItemStore = useUpdateItemUnitUpdateMetadataStore()

const shoppinglistDetailsStore = useShoppinglistDetailStore()

const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})

const router = useRoute()

const { refetch: updateShoppinglistItemCalculatedPrice } =
  useUpdateShoppinglistItemCalculatedPrice()
const { refetch: updateItemUnitUpValues } = useUpdateItemUnitUpValues()
const { refetch: updateShoppinglistTotalPrice } = useUpdateShoppinglistTotalPrice()

const modalHeaderText = computed(() => {
  return 'Detalle del producto'
})

const productInfoText = computed(() => {
  return 'Información del producto'
})

const updateProductText = computed(() => {
  return 'Actualización del producto'
})

const actualTotalPrice = computed(() => {
  return (
    'Precio actual: ' +
    (store.totalPriceFixed === -1
      ? props.shoppinglistItem.calculatedPrice.toFixed(2)
      : store.totalPriceFixed.toFixed(2)) +
    '€'
  )
})

const addNewItemUnitText = computed(() => {
  return 'Nuevo item unit'
})

async function updateShoppinglistPrice() {
  if (requestUpdateUpItemStore.requestUpItemUnitUpdateMetadataList.length > 0) {
    let request: RequestUpdateShoppinglistItemItemUnitsUp = {
      requestUpItemUnitUpdateMetadataList:
        requestUpdateUpItemStore.requestUpItemUnitUpdateMetadataList.length > 0
          ? requestUpdateUpItemStore.requestUpItemUnitUpdateMetadataList
          : null,
    }
    await updateItemUnitUpValues(props.shoppinglistItem.idShoppinglistItem, request) // TENGO QUE LLAMAR A ESTA FUNCION CON LOS VALORES QUE HAY EN EL STORE upItemUnitUpdateMetadataStore
    // Importante -> Limpiar el listado si no se acumulara informacion innecesaria
    requestUpdateUpItemStore.clear()
    if (store.totalPrice !== null) {
      let oldValue = props.shoppinglistItem.calculatedPrice
      props.shoppinglistItem.calculatedPrice = store.totalPrice
      shoppinglistDetailsStore.recalculateShoppinglistTotalPrice(
        shoppinglistDetailsStore.totalPrice,
        oldValue,
        store.totalPrice,
      )
    }
    await updateShoppinglistItemCalculatedPrice(props.shoppinglistItem.idShoppinglistItem)
    await updateShoppinglistTotalPrice(Number(router.params.id))
    store.totalPriceFixed = store.totalPrice
  }
}
</script>
<template>
  <Button class="w-full" severity="info" label="Uds/€" raised @click="visible = true"></Button>
  <Dialog v-model:visible="visible" :position="'top'" :modal="true" :draggable="false">
    <template #header>
      <div class="ml-3 mt-2 text-2xl text-left font-italic">{{ modalHeaderText }}</div>
    </template>
    <Divider align="center" type="solid">
      <b>{{ productInfoText }}</b>
    </Divider>
    <Tag severity="info" class="w-full">{{ actualTotalPrice }}</Tag>
    <Divider>
      <b>{{ addNewItemUnitText }}</b>
    </Divider>
    <!-- Formulario para añadir un nuevo item unit  -->
    <!-- A MODIFICAR EN VEZ DE MANDAR EL id MANDAR TODO EL shoppinglistItem -->
    <ItemUnitUpCreateForm
      :quickCreate="true"
      :shoppinglistItem="props.shoppinglistItem"
    ></ItemUnitUpCreateForm>
    <Divider align="center" type="solid">
      <b>{{ updateProductText }}</b>
    </Divider>
    <ItemUnitUpUpdateForm :shoppinglistItem="props.shoppinglistItem"></ItemUnitUpUpdateForm>
    <div class="flex justify-content-end gap-2">
      <Button
        class="w-full"
        type="button"
        label="Cancel"
        severity="danger"
        @click="visible = false"
      ></Button>
      <Button
        class="w-full"
        severity="info"
        type="button"
        label="Actualizar"
        @click="((visible = false), updateShoppinglistPrice())"
      ></Button>
    </div>
  </Dialog>
</template>
