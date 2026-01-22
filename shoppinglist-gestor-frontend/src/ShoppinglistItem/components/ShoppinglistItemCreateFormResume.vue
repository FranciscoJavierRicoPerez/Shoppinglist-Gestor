<script setup lang="ts">
import InformationCard from '@/Shared/components/InformationCard.vue'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'
import Card from 'primevue/card'
import Tag from 'primevue/tag'
import { computed } from 'vue'

const store = useCreateShoppinglistItemFormStore()

/** --- COMPUTED SECTION ---  */
const resumeHeaderText = computed(() => {
  return 'Resumen del nuevo producto'
})
const selectedCalculateSystemText = computed(() => {
  let result = store.selectedCalculateSystem === 1 ? 'UP' : 'WP'
  return 'Sistema de calculo: ' + result
})

const selectedProductText = computed(() => {
  return 'Producto: ' + store.selectedProduct
})

const quantityText = computed(() => {
  return 'Cantidad: ' + store.quantity + ' uds'
})

const unitaryPriceText = computed(() => {
  return 'Precio unitario: ' + store.unitaryPrice + '€'
})

const priceKgText = computed(() => {
  return 'Kg/€: ' + store.priceKg
})

const weightText = computed(() => {
  return 'Peso: ' + store.weight
})

const shoppinglistItemPriceText = computed(() => {
  return 'Precio del item: ' + store.shoppinglistItemPrice + '€'
})
/** ------------------------- */
</script>
<template>
  <Card>
    <template #header>
      <div class="text-2xl italic ml-3 mt-2">{{ resumeHeaderText }}</div>
    </template>
    <template #content>
      <div v-if="store.selectedProduct === ''">
        <InformationCard
          :information="{
            header: 'Información',
            content: 'No hay información suficiente para crear el resumen',
          }"
        ></InformationCard>
      </div>
      <div v-else>
        <div class="flex flex-column gap-2">
          <Tag v-if="store.selectedProduct !== ''" severity="info">{{ selectedProductText }}</Tag>
          <div v-if="store.selectedCalculateSystem !== -1">
            <Tag class="flex w-full bg-indigo-500 text-white mb-2">{{
              selectedCalculateSystemText
            }}</Tag>
            <div v-if="store.selectedCalculateSystem === 1">
              <div class="flex justify-content-start mb-2">
                <Tag v-if="store.quantity !== null" class="bg-orange-400 text-white w-6 mr-2">
                  {{ quantityText }}</Tag
                >
                <Tag v-if="store.unitaryPrice" class="bg-orange-400 text-white w-6"
                  >{{ unitaryPriceText }} €</Tag
                >
              </div>
            </div>
            <div v-else>
              <div class="flex justify-content-start mb-2">
                <Tag v-if="store.priceKg" class="bg-orange-400 text-white w-6 mr-2">{{
                  priceKgText
                }}</Tag>
                <Tag v-if="store.weight" class="bg-orange-400 text-white w-6">{{ weightText }}</Tag>
              </div>
            </div>
            <Tag v-if="store.shoppinglistItemPrice" class="bg-red-400 text-white w-full">{{
              shoppinglistItemPriceText
            }}</Tag>
          </div>
        </div>
      </div>
    </template>
  </Card>
</template>
<style lang="css"></style>
