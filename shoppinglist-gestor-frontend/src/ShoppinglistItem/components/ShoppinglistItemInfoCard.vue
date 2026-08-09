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
import { useUpdateShoppinglistTotalPrice } from '@/Shoppinglist/application/useUpdateShoppinglistTotalPrice'
import { useRoute } from 'vue-router'
import { useItemUnitUpGroupedByPriceStore } from '@/ItemUnit/store/itemUnitUpGroupedByPriceStore'
import { useShoppinglistItemDetailsStore } from '../stores/shoppinglistItemDetailsStore'
import ItemUnitUpDialogV2 from '@/ItemUnit/components/v2/ItemUnitUpDialogV2.vue'

/** TODO -> REFACTORIZACION DE TODO ESTE COMPONENTE  */
/**
 * Coste producto: X€ -> Debe actualizarse dinamicamente si se modifican los precios de los items units up y wp
 * CREACIÓN DE UNA V2 DEL COMPONENTE ->  <ItemUnitUpDialog :shoppinglistItem="shoppinglistItem"></ItemUnitUpDialog>
 *  CON LAS SIGUIENTES CARACTERISTICAS
 *  ->
 */

/** --- PROPS SECTIONS --- */
const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
  isSLActive: {
    type: Boolean as PropType<boolean>,
    default: () => false,
  },
})
/** ---------------------- */
const router = useRoute()

// const calculatedPrice = ref<number>(-1) // ESTO EN VEZ DE SER UN const FIJO DEBE DE CAMBIAR A UN store ASI PUEDO USAR ESE STORE COMO ALMACEN EN MEMORIA DE TODOS LOS DATOS QUE SE PUEDAN MODIFICAR

/** ---- USE CASES ---- */
const { refetch: deleteShoppinglistItem } = useDeleteShoppinglistItem()
const { refetch: updateShoppinglistTotalPrice } = useUpdateShoppinglistTotalPrice()
/** ------------------- */

/** ---- STORE SECTION ---- */
/**
 * Management the data of a shoppinglist - Information Section
 */
const shoppinglistDetailsStore = useShoppinglistDetailStore()
/**
 * Management the data of a shoppinglist item
 */
const shoppinglistItemDetailsStore = useShoppinglistItemDetailsStore()
/**
 * Management the data of the items units up grouped by his price
 */
const store = useItemUnitUpGroupedByPriceStore()
/** ----------------------- */

const toast = useToast()

onMounted(() => {
  // TODO -> 1º - Crear un store llamado shoppinglistItemDetails -> que se encarge de gestionar la información visual de los datos que se muestran dinamicante de un shoppinglist item
  // calculatedPrice.value = props.shoppinglistItem.calculatedPrice
  shoppinglistItemDetailsStore.initializateValues(
    props.shoppinglistItem.name,
    props.shoppinglistItem.calculateSystemCode,
    props.shoppinglistItem.assignationToLisDate,
    props.shoppinglistItem.calculatedPrice,
    props.shoppinglistItem.idShoppinglistItem,
  )
})

/** ---- COMPUTED SECTION ---- */
const shoppinglistItemPriceText = computed(() => {
  if (shoppinglistItemDetailsStore.sliCalculateSystem === 'WP') {
    return 'Coste producto: ' + shoppinglistItemDetailsStore.sliCalculatedPrice + '€'
  } else {
    return (
      'Coste producto: ' +
      (store.totalPriceFixed === -1
        ? shoppinglistItemDetailsStore.sliCalculatedPrice
        : store.totalPriceFixed) +
      '€'
    )
  }
})

const shoppinglistItemAssignationToListDateText = computed(() => {
  return 'Añadido el: ' + shoppinglistItemDetailsStore.sliAssignationToListDate
})

const shoppinglistItemCalculateSystemText = computed(() => {
  return 'Sistema de calculo: ' + shoppinglistItemDetailsStore.sliCalculateSystem
})

const shoppinglistItemNameText = computed(() => {
  return shoppinglistItemDetailsStore.sliName
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
  shoppinglistItemDetailsStore.sliCalculatedPrice = data
  if (shoppinglistDetailsStore.shoppinglistDetails) {
    shoppinglistDetailsStore.recalculateShoppinglistTotalPrice(
      shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.totalPrice,
      props.shoppinglistItem.calculatedPrice,
      shoppinglistItemDetailsStore.sliCalculatedPrice,
    )
  }
}

async function removeShoppinglistItem(id: number): Promise<void> {
  // IMPLEMENTACION LLAMANDO AL BACKEND
  const response: DeleteShoppinglistItemData = await deleteShoppinglistItem(id)
  if (response.delete) {
    shoppinglistDetailsStore.updateItemsList(shoppinglistDetailsStore.removeItem(id))
    shoppinglistDetailsStore.updateTotalPrice(false, props.shoppinglistItem.calculatedPrice)
    await updateShoppinglistTotalPrice(Number(router.params.id))
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
      <template #footer v-if="isSLActive">
        <div class="flex flex-column gap-2">
          <div v-if="shoppinglistItem.calculateSystemCode === 'WP'">
            <ItemUnitWpDialog
              :shoppinglistItem="shoppinglistItem"
              @newSliCalculatedPrice="updateCalculatedPrice"
            ></ItemUnitWpDialog>
          </div>
          <div v-else>
            <!-- -->
            <ItemUnitUpDialog :shoppinglistItem="shoppinglistItem"></ItemUnitUpDialog>
            <!-- <ItemUnitUpDialogV2></ItemUnitUpDialogV2> -->
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
