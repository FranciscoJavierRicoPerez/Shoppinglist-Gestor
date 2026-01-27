<script setup lang="ts">
import { computed, onMounted, ref, type PropType } from 'vue'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import Card from 'primevue/card'
import Button from 'primevue/button'
import Tag from 'primevue/tag'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ToastMessageOptions } from 'primevue/toast'
import { useToast } from 'primevue/usetoast'
import { useDeleteShoppinglistItem } from '../application/useDeleteShoppinglistItem'
import type { DeleteShoppinglistItemData } from '../infrastructure/models/dto/DeleteShoppinglistItemData'
import ItemUnitUpDialog from '@/ItemUnit/components/ItemUnitUpDialog.vue'
import ItemUnitWpDialog from '@/ItemUnit/components/ItemUnitWpDialog.vue'

/** --- PROPS SECTIONS --- */
const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})
/** ---------------------- */

const calculatedPrice = ref<number>(-1)

onMounted(() => {
  calculatedPrice.value = props.shoppinglistItem.calculatedPrice
})

/** ---- USE CASES ---- */
const { refetch: deleteShoppinglistItem } = useDeleteShoppinglistItem()
/** ------------------- */

/** ---- STORE SECTION ---- */
const shoppinglistDetailsStore = useShoppinglistDetailStore()
/** ----------------------- */

const toast = useToast()

/** ---- COMPUTED SECTION ---- */
const shoppinglistItemPriceText = computed(() => {
  return 'Coste producto: ' + calculatedPrice.value + '€'
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

function updateCalculatedPrice(data: any) {
  console.log(data)
  calculatedPrice.value = data
  if (shoppinglistDetailsStore.shoppinglistDetails) {
    shoppinglistDetailsStore.recalculateShoppinglistTotalPrice(
      shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.totalPrice,
      props.shoppinglistItem.calculatedPrice,
      calculatedPrice.value,
    )
  }
}

async function removeShoppinglistItem(id: number): Promise<void> {
  console.log('INFO: Borrando el shoppinglist item con id : ' + id)
  // IMPLEMENTACION LLAMANDO AL BACKEND
  const response: DeleteShoppinglistItemData = await deleteShoppinglistItem(id)
  if (response.delete) {
    shoppinglistDetailsStore.updateItemsList(shoppinglistDetailsStore.removeItem(id))
    shoppinglistDetailsStore.updateTotalPrice(false, props.shoppinglistItem.calculatedPrice)
    createToast({
      severity: 'success',
      summary: 'Se ha borrado el producto ' + props.shoppinglistItem.name,
      detail: response.message,
      life: 3000,
    })
    // SI SE HA BORRADO CORRECTAMENTE HABRIA QUE ACTUALIZAR EL VALOR DEL TOTAL PRICE DE LA LISTA DE LA COMPRA
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
        <div class="flex flex-column gap-2">
          <div v-if="shoppinglistItem.calculateSystemCode === 'WP'">
            <ItemUnitWpDialog
              :idShoppinglistItem="shoppinglistItem.idShoppinglistItem"
              @newSliCalculatedPrice="updateCalculatedPrice"
            ></ItemUnitWpDialog>
          </div>
          <div v-else>
            <ItemUnitUpDialog></ItemUnitUpDialog>
          </div>
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
