<script setup lang="ts">
import Dialog from 'primevue/dialog'
import Tag from 'primevue/tag'
import { computed, ref, type PropType } from 'vue'
import Button from 'primevue/button'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import Divider from 'primevue/divider'
import ItemUnitUpPopover from './ItemUnitUpUpdateForm.vue'
import { useItemUnitUpGroupedByPriceStore } from '../store/itemUnitUpGroupedByPriceStore'
import ItemUnitUpCreateForm from './ItemUnitUpCreateForm.vue'
import ItemUnitUpUpdateForm from './ItemUnitUpUpdateForm.vue'
import { useUpdateItemUnitUpValues } from '../application/useUpdateItemUnitUpValues'

const visible = ref<boolean>(false)

const store = useItemUnitUpGroupedByPriceStore()

const shoppinglistDetailsStore = useShoppinglistDetailStore()

const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})

const { refetch: updateItemUnitUpValues } = useUpdateItemUnitUpValues()

const modalHeaderText = computed(() => {
  return 'Detalle del producto'
})

const productInfoText = computed(() => {
  return 'Información del producto'
})

const updateProductText = computed(() => {
  return 'Actualización del producto'
})

const updatedProductResumeText = computed(() => {
  return 'Resumen del producto actualizado'
})

const actualTotalPrice = computed(() => {
  return 'Precio total: ' + props.shoppinglistItem.calculatedPrice
})

const addNewItemUnitText = computed(() => {
  return 'Nuevo item unit'
})

async function updateShoppinglistPrice() {
  await updateItemUnitUpValues()
  if (store.totalPrice !== null) {
    let oldValue = props.shoppinglistItem.calculatedPrice
    props.shoppinglistItem.calculatedPrice = store.totalPrice
    shoppinglistDetailsStore.recalculateShoppinglistTotalPrice(
      shoppinglistDetailsStore.totalPrice,
      oldValue,
      store.totalPrice,
    )
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
    <Tag class="w-full">{{ actualTotalPrice }}</Tag>
    <Divider>
      <b>{{ addNewItemUnitText }}</b>
    </Divider>
    <!-- Formulario para añadir un nuevo item unit  -->
    <ItemUnitUpCreateForm :quickCreate="true"></ItemUnitUpCreateForm>
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
