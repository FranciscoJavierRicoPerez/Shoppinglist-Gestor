<script setup lang="ts">
import Dialog from 'primevue/dialog'
import { computed, ref, type PropType } from 'vue'
import Button from 'primevue/button'
import { useUpdateItemWpFormStore } from '../store/updateItemWpFormStore'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import Divider from 'primevue/divider'

const visible = ref<boolean>(false)

const modalHeaderText = computed(() => {
  return 'Detalle del producto'
})

const store = useUpdateItemWpFormStore()

const shoppinglistDetailsStore = useShoppinglistDetailStore()

const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})

function updateShoppinglistPrice() {
  if (store.newProductPrice !== null) {
    let oldValue = props.shoppinglistItem.calculatedPrice
    props.shoppinglistItem.calculatedPrice = store.newProductPrice
    shoppinglistDetailsStore.recalculateShoppinglistTotalPrice(
      shoppinglistDetailsStore.totalPrice,
      oldValue,
      store.newProductPrice,
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
      <b>1</b>
    </Divider>
    <Divider align="center" type="solid">
      <b>2</b>
    </Divider>
    <Divider></Divider>
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
