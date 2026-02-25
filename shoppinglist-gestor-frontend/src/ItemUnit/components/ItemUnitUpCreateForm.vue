<script setup lang="ts">
import InputNumber from 'primevue/inputnumber'
import FloatLabel from 'primevue/floatlabel'
import Button from 'primevue/button'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'
import { ref, watch, type PropType } from 'vue'
import { useItemUnitUpGroupedByPriceStore } from '../store/itemUnitUpGroupedByPriceStore'

const props = defineProps({
  quickCreate: {
    type: Boolean as PropType<Boolean>,
    default: () => false,
  },
})

const quantity = ref<number | null>(null)
const unitaryPrice = ref<number | null>(null)

watch(quantity, () => {
  if (!props.quickCreate) {
    calculateShoppinglistItemTotalPrice()
  }
})

watch(unitaryPrice, () => {
  if (!props.quickCreate) {
    calculateShoppinglistItemTotalPrice()
  }
})

const store = useCreateShoppinglistItemFormStore()

const groupedItemsUpStore = useItemUnitUpGroupedByPriceStore()

function calculateShoppinglistItemTotalPrice(): void {
  let totalPrice: number | null = null
  if (quantity.value && unitaryPrice.value) {
    totalPrice = quantity.value * unitaryPrice.value
    store.quantity = quantity.value
    store.unitaryPrice = unitaryPrice.value
  }
  store.shoppinglistItemPrice = totalPrice
}

function addNewItemUnitUp(): void {
  console.log('llamar al servicio que se encarga de añadir un nuevo item unit')
  // TAMBIEN TIENE QUE LLAMARSE AL STORE useItemUnitUpGroupedByPriceStore PARA AÑADIR LA NUEVA INSTANCIA
  if (quantity.value && unitaryPrice.value) {
    // ESTO NO SERA ASI, SERA CON LA RESPUESTA DEL SERVICIO
    groupedItemsUpStore.add({
      quantity: quantity.value,
      price: unitaryPrice.value,
      calculatedPrice: quantity.value * unitaryPrice.value,
    })
  }
  groupedItemsUpStore.updateTotalPrice()
}
</script>
<template>
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
      v-if="quickCreate"
      class="w-full"
      label="Añadir unidad"
      @click="addNewItemUnitUp()"
    ></Button>
  </div>
</template>
