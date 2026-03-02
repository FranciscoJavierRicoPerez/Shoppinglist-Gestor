<script setup lang="ts">
import Panel from 'primevue/panel'
import Button from 'primevue/button'
import ShoppinglistItemCreateItemForm from './ShoppinglistItemCreateItemForm.vue'
import ShoppinglistItemCreateFormResume from './ShoppinglistItemCreateFormResume.vue'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'
import type { RequestCreateShoppinglistItemForm } from '../infrastructure/models/request/RequestCreateShoppinglistItemForm'
import { useCreateShoppinglistItem } from '@/ShoppinglistItem/application/useCreateShoppinglistItem'
import { useToast, type ToastMessageOptions } from 'primevue'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import { computed } from 'vue'

const store = useCreateShoppinglistItemFormStore()
const shoppinglistDetailsStore = useShoppinglistDetailStore()

const { refetch: createShoppinglistItem } = useCreateShoppinglistItem()

const toast = useToast()

const createFormHeaderText = computed(() => {
  return 'Nuevo Producto'
})

function createToast(toastOptions: ToastMessageOptions) {
  toast.add({
    severity: toastOptions.severity,
    summary: toastOptions.summary,
    detail: toastOptions.detail,
    life: toastOptions.life,
  })
}

async function createNewShoppinglistItem() {
  console.log('Info: creacion de un shoppinglistitem')
  const request: RequestCreateShoppinglistItemForm = {
    productInfo: {
      alreadyExists: store.verifyIsSelectedProductAlreadyExists(
        store.selectedProduct,
        store.productOptions,
      ),
      productName: store.selectedProduct,
    },
    selectedCalculateSystem: store.selectedCalculateSystem,
    createItemUnitData: {
      createItemUnit: true,
      createUpItemUnitData:
        store.quantity === null || store.unitaryPrice === null
          ? null
          : {
              quantity: store.quantity,
              unitaryPrice: store.unitaryPrice,
            },
      createWpItemUnitData:
        store.priceKg === null || store.weight === null
          ? null
          : {
              priceKg: store.priceKg,
              weight: store.weight,
            },
    },
  }
  let response = await createShoppinglistItem(request)
  if (response.created) {
    createToast({
      severity: 'success',
      summary: 'Nuevo producto añadido a la lista de la compra',
      detail:
        'El producto con nombre: ' +
        response.shoppinglistItemMetadata.name +
        ' ha sido añadido correctamente',
      life: 3000,
    })
    // Añadir el nuevo SLI al listado y limpiar el formulario
    store.setValuesDefault()
    shoppinglistDetailsStore.addItem(response.shoppinglistItemMetadata)
    shoppinglistDetailsStore.updateTotalPrice(
      true,
      response.shoppinglistItemMetadata.calculatedPrice,
    )
  } else {
    createToast({
      severity: 'danger',
      summary: 'Error en la creacion del producto',
      detail: 'El producto no ha podido añadirse',
      life: 3000,
    })
  }
}

function verifyForm() {
  return !(
    store.selectedProduct !== '' &&
    store.selectedCalculateSystem !== -1 &&
    ((store.quantity !== null && store.unitaryPrice) ||
      (store.priceKg !== null && store.weight !== null))
  ) as boolean
}
</script>
<template>
  <Panel id="1" toggleable>
    <template #header>
      <div class="text-2xl font-italic">{{ createFormHeaderText }}</div>
    </template>
    <template #footer>
      <Button
        :disabled="verifyForm()"
        security="info"
        label="Añadir producto"
        class="w-full"
        @click="createNewShoppinglistItem()"
      ></Button>
    </template>
    <ShoppinglistItemCreateItemForm
      class="w-19rem sm:w-full mb-2 bg-green-100"
    ></ShoppinglistItemCreateItemForm>
    <ShoppinglistItemCreateFormResume class="bg-green-100"></ShoppinglistItemCreateFormResume>
  </Panel>
</template>
<style lang="css"></style>
