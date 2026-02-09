<script setup lang="ts">
import Dialog from 'primevue/dialog'
import { computed, ref, type PropType } from 'vue'
import Button from 'primevue/button'
import Divider from 'primevue/divider'
import ItemUnitWpUpdateForm from './ItemUnitWpUpdateForm.vue'
import ItemUnitWpResume from './ItemUnitWpResume.vue'
import { useUpdateItemWpFormStore } from '@/ItemUnit/store/updateItemWpFormStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
const visible = ref<boolean>(false)

const store = useUpdateItemWpFormStore()

const shoppinglistDetailsStore = useShoppinglistDetailStore()

const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})

const emit = defineEmits(['newSliCalculatedPrice'])

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
  <Button class="w-full" severity="info" label="Kg/€" raised @click="visible = true"></Button>
  <Dialog v-model:visible="visible" :position="'top'" :modal="true" :draggable="false">
    <template #header>
      <div class="ml-3 mt-2 text-2xl text-left font-italic">{{ modalHeaderText }}</div>
    </template>
    <Divider align="center" type="solid">
      <b>{{ productInfoText }}</b>
    </Divider>
    <ItemUnitWpResume
      :isUpdateInfo="false"
      :shoppinglistItem="props.shoppinglistItem"
    ></ItemUnitWpResume>
    <Divider align="center" type="solid">
      <b>{{ updateProductText }}</b>
    </Divider>
    <ItemUnitWpUpdateForm></ItemUnitWpUpdateForm>
    <Divider align="center" type="solid">
      <b>{{ updatedProductResumeText }}</b>
    </Divider>
    <ItemUnitWpResume :isUpdateInfo="true"></ItemUnitWpResume>
    <Divider> </Divider>

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
