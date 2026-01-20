<script setup lang="ts">
import InputNumber from 'primevue/inputnumber'
import FloatLabel from 'primevue/floatlabel'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'
import { ref, watch } from 'vue'

const priceKg = ref<number | null>(null)
const weight = ref<number | null>(null)

watch(priceKg, () => {
  calculateShoppinglistItemTotalPrice()
})

watch(weight, () => {
  calculateShoppinglistItemTotalPrice()
})

const store = useCreateShoppinglistItemFormStore()

function calculateShoppinglistItemTotalPrice() {
  let totalPrice: number | null = null
  if (priceKg.value && weight.value) {
    totalPrice = priceKg.value * weight.value
    store.priceKg = priceKg.value
    store.weight = weight.value
  }
  store.shoppinglistItemPrice = totalPrice
}
</script>
<template>
  <div class="flex flex-row gap-2">
    <FloatLabel variant="on" class="w-full">
      <InputNumber
        id="pricekg"
        class="w-full"
        v-model="priceKg"
        :minFractionDigits="2"
        :maxFractionDigits="3"
      ></InputNumber>
      <label for="pricekg">Precio Kg/€</label>
    </FloatLabel>
    <FloatLabel variant="on" class="w-full">
      <InputNumber
        id="weight"
        class="w-full"
        v-model="weight"
        :minFractionDigits="2"
        :maxFractionDigits="3"
      ></InputNumber>
      <label for="weight">Peso Kg</label>
    </FloatLabel>
  </div>
</template>
