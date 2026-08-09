<script setup lang="ts">
import { computed, ref } from 'vue'
import Button from 'primevue/button'
import Dialog from 'primevue/dialog'
import Divider from 'primevue/divider'
import Card from 'primevue/card'
import Tag from 'primevue/tag'
import { useShoppinglistItemDetailsStore } from '@/ShoppinglistItem/stores/shoppinglistItemDetailsStore'
import ItemUnitUpCreateForm from '@/ItemUnit/components/ItemUnitUpCreateForm.vue'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import { useAddItemUnitUpToShoppinglistItem } from '@/ShoppinglistItem/application/useAddItemUnitUpToShoppinglistItem'
import { useUpdateShoppinglistTotalPrice } from '@/Shoppinglist/application/useUpdateShoppinglistTotalPrice'
import { useItemUnitUpGroupedByPriceStore } from '@/ItemUnit/store/itemUnitUpGroupedByPriceStore'
import { useRoute } from 'vue-router'

/** ----- ATRIBUTOS ------- */
// - Visible atribute for the dialog management
const visible = ref<boolean>(false)
// - New Item Unit UP quantity value
const quantity = ref<number | null>(null)
// - New Item Unit UP unitary price value
const unitaryPrice = ref<number | null>(null)
// - Router
const router = useRoute()

/** ----------------------- */

/** ---- API SECTION ------ */
const { refetch: addItemUnitUpToShoppinglist } = useAddItemUnitUpToShoppinglistItem()
const { refetch: updateShoppinglistTotalPrice } = useUpdateShoppinglistTotalPrice()
/** ----------------------- */

/** ----- STORES ---------- */
const shoppinglistDetailsStore = useShoppinglistDetailStore()
const shoppinglistItemDetailsStore = useShoppinglistItemDetailsStore()
const groupedItemsUpStore = useItemUnitUpGroupedByPriceStore() // -> Cambiar
/** ----------------------- */

/** ------ COMPUTED -------- */
const modalHeaderText = computed(() => {
  return 'Detalle del producto'
})

const productInfoText = computed(() => {
  return 'Información del Producto'
})

const updateProductText = computed(() => {
  return 'Actualización del producto'
})

const updatedProductResumeText = computed(() => {
  return 'Resumen del producto actualizado'
})

const actualTotalPrice = computed(() => {
  return 'Precio total: ' + shoppinglistItemDetailsStore.sliCalculatedPrice
})

const itemsUnitsUpManagementSection = computed(() => {
  return 'Gestión de las unidades'
})

const shoppinglistItemName = computed(() => {
  return 'Producto: ' + shoppinglistItemDetailsStore.sliName
})

const actualShoppinglistItemTotalCost = computed(() => {
  return 'Coste producto: ' + shoppinglistItemDetailsStore.sliCalculatedPrice
})

const addNewItemsUnitsUpSection = computed(() => {
  return 'Añadir unidades al producto'
})

/** ------- FUNCTIONS SECTIONS ------------ */
function clearForm() {
  quantity.value = null
  unitaryPrice.value = null
}

async function addNewItemUnitUp() {
  // TAMBIEN TIENE QUE LLAMARSE AL STORE useItemUnitUpGroupedByPriceStore PARA AÑADIR LA NUEVA INSTANCIA
  if (quantity.value && unitaryPrice.value) {
    await addItemUnitUpToShoppinglist(shoppinglistItemDetailsStore.sliId, {
      createItemUnit: true,
      createUpItemUnitData: {
        idItemUnitUp: null,
        quantity: quantity.value,
        unitaryPrice: unitaryPrice.value,
      },
      createWpItemUnitData: null,
    })
    // ACTUALIZAMOS EL PRECIO TOTAL DE LA LISTA DE LA COMPRA CON EL NUEVO VALOR DEL SLI AL HABER AÑADIDO UN NUEVO ITEM UNIT UP
    shoppinglistDetailsStore.totalPrice = await updateShoppinglistTotalPrice(
      Number(router.params.id),
    )
    // ESTO NO SERA ASI, SERA CON LA RESPUESTA DEL SERVICIO
    groupedItemsUpStore.add({
      quantity: quantity.value,
      price: unitaryPrice.value,
      calculatedPrice: quantity.value * unitaryPrice.value,
      idItemUnitUp: -1,
      idItemUnit: -1,
    })
  }
  groupedItemsUpStore.updateTotalPrice()
  groupedItemsUpStore.totalPriceFixed = groupedItemsUpStore.totalPrice
  clearForm()
}

/** -------------------------- */
</script>
<template>
  <Button
    class="w-full"
    severity="info"
    label="Uds/€ (VERSION 2)"
    raised
    @click="visible = true"
  ></Button>
  <Dialog v-model:visible="visible" :position="'top'" :modal="true" :draggable="false">
    <template #header>
      <div class="ml-3 mt-2 text-2xl text-left font-italic">{{ modalHeaderText }}</div>
    </template>
    <Divider align="center" type="solid">
      <b>{{ productInfoText }}</b>
    </Divider>
    <Card class="w-full">
      <template #content>
        <Tag severity="info" rounded>{{ shoppinglistItemName }}</Tag>
        <Tag severity="success" rounded>{{ actualShoppinglistItemTotalCost }}</Tag>
      </template>
    </Card>
    <Divider>
      <b>{{ itemsUnitsUpManagementSection }}</b>
    </Divider>
    <Card class="w-full">
      <template #title>
        <div class="ml-3 mt-2 text-2xl text-left font-italic">{{ addNewItemsUnitsUpSection }}</div>
      </template>
      <template #content>
        <!-- New Item Unit Up Form Section -->
        <div class="flex flex-column sm:flex-row gap-2">
          <FloatLabel variant="on" class="w-full">
            <InputNumber id="quantity" v-model="quantity" class="w-full"></InputNumber>
            <label for="quantity">Cantidad</label>
          </FloatLabel>
          <FloatLabel variant="on" class="w-full">
            <InputNumber
              id="unitaryPrice"
              class="w-full"
              v-model="unitaryPrice"
              :minFractionDigits="2"
              :maxFractionDigits="3"
            ></InputNumber>
            <label for="unitaryPrice">Precio Unitario</label>
          </FloatLabel>
          <Button
            class="w-full"
            label="Añadir unidad"
            :disabled="unitaryPrice === null || quantity == null"
            @click="addNewItemUnitUp()"
          ></Button>
        </div>
      </template>
    </Card>
  </Dialog>
</template>
<style lang="css"></style>
