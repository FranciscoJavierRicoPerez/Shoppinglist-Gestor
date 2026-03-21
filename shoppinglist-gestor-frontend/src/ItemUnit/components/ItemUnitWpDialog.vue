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
import { useUpdateShoppinglistTotalPrice } from '@/Shoppinglist/application/useUpdateShoppinglistTotalPrice'
import { useRoute } from 'vue-router'
import { useUpdateItemUnitWpValues } from '../application/useUpdateItemUnitWpValues'
import { useUpdateItemUnitTotalPrice } from '../application/useUpdateItemUnitTotalPrice'
import { useUpdateShoppinglistItemCalculatedPrice } from '@/ShoppinglistItem/application/useUpdateShoppinglistItemCalculatedPrice'
const visible = ref<boolean>(false)

const store = useUpdateItemWpFormStore()

const shoppinglistDetailsStore = useShoppinglistDetailStore()

const { refetch: updateShoppinglistTotalPrice } = useUpdateShoppinglistTotalPrice()
const { refetch: updateItemUnitWpValues } = useUpdateItemUnitWpValues()
const { refetch: updateItemUnitTotalPrice } = useUpdateItemUnitTotalPrice()
const { refetch: updateShoppinglistItemCalculatedPrice } =
  useUpdateShoppinglistItemCalculatedPrice()

const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})

const idItemUnitWp = ref<number | null>(null)

const idItemUnit = ref<number | null>(null)

const router = useRoute()

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

async function updateItemUnitWp() {
  /**
   * Desarrollar un servicio en el BE que permita la actualizacion de la información
   * de un item unit wp y del precio total de su item unit asociado.
   *
   * Objeto Request -> RequestUpdateItemUnitWpData
   *     idWpItemUnit -> Para actualizar los valores de price_kg and weight -> idItemUnitWp.value
   *     newWeight-> store.newWeight
   *     newPriceKg -> store.newPriceKg
   *     newProductPrice -> store.newProductPrice -> ItemUnit.total_price (EQUIVALENCIA DE COLUMNAS)
   *     idItemUnit -> idItemUnit.value
   * Interfaz del EP -> /api/itemunit/{iditemunit}/update/itemunitwp/{iditemunitwp}
   *
   */
  if (
    idItemUnitWp.value &&
    idItemUnit.value &&
    store.newProductPrice &&
    store.newPriceKg &&
    store.newWeight
  ) {
    // Actualziamos el item unit wp
    await updateItemUnitWpValues(idItemUnitWp.value, {
      newWeight: store.newWeight,
      newPriceKg: store.newPriceKg,
    })
    // Ahora sabemos que hay que actualizar la información del item unit asociado, hay que recalcular su
    // total_price
    // CREAR UN SERVICIO QUE RECALCULE EL TOTAL_PRICE DEL item unit indicado
    // - servio que actualice el precio del item unit indicado
    await updateItemUnitTotalPrice(idItemUnit.value, store.newProductPrice)
    // UNA VEZ RECALCULADO ESE TOTAL PRICE HAY QUE RECALCULAR EL PRECIO DEL SLI
    // - servicio que actualice el precio del sli indicado
    await updateShoppinglistItemCalculatedPrice(props.shoppinglistItem.idShoppinglistItem) // -> ESTE SERVICIO ES MEJOR QUE HAGA TODO EL CALCULO EN EL BE
    // AHORA POR ULTIMO RECALCULAMOS EL PRECIO DE LA SL
    updateShoppinglistPrice()
  }
}

async function updateShoppinglistPrice() {
  // Llamar al servicio que se encarga de actualizar el precio total de la lista de la compra
  if (store.newProductPrice !== null) {
    await updateShoppinglistTotalPrice(Number(router.params.id))
    let oldValue = props.shoppinglistItem.calculatedPrice
    props.shoppinglistItem.calculatedPrice = store.newProductPrice
    shoppinglistDetailsStore.recalculateShoppinglistTotalPrice(
      shoppinglistDetailsStore.totalPrice,
      oldValue,
      store.newProductPrice,
    )
  }
}

function getItemUnitWpId(data: number | null) {
  if (data) {
    idItemUnitWp.value = data
  }
}

function getItemUnitId(data: number | null) {
  if (data) {
    idItemUnit.value = data
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
      @idItemUnitWp="getItemUnitWpId"
      @idItemUnit="getItemUnitId"
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
        @click="((visible = false), updateItemUnitWp())"
      ></Button>
    </div>
  </Dialog>
</template>
