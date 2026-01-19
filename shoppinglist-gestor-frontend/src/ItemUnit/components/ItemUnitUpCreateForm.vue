<script setup lang="ts">
import InputNumber from 'primevue/inputnumber'
import FloatLabel from 'primevue/floatlabel'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'
import { ref, watch } from 'vue'

const quantity = ref<number | null>(null)
const unitaryPrice = ref<number | null>(null)

watch(quantity, () => {
  calculateShoppinglistItemTotalPrice()
})

watch(unitaryPrice, () => {
  calculateShoppinglistItemTotalPrice()
})

const store = useCreateShoppinglistItemFormStore()

function calculateShoppinglistItemTotalPrice() {
  let totalPrice: number | null = null
  if (quantity.value && unitaryPrice.value) {
    totalPrice = quantity.value * unitaryPrice.value
    store.quantity = quantity.value
    store.unitaryPrice = unitaryPrice.value
  }
  store.shoppinglistItemPrice = totalPrice
}
</script>
<template>
  <div class="flex flex-row gap-2">
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
  </div>
</template>
