<script setup lang="ts">
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'
import Card from 'primevue/card'
import Tag from 'primevue/tag'

const store = useCreateShoppinglistItemFormStore()
</script>
<template>
  <Card>
    <template #header>
      <div class="text-2xl font-italic ml-3 mt-2">Resumen nuevo producto</div>
    </template>
    <template #content>
      <div class="flex flex-column gap-2">
        <Tag severity="info">Producto: {{ store.selectedProduct }}</Tag>
        <div v-if="store.selectedCalculateSystem !== -1">
          <Tag class="flex w-full bg-indigo-500 text-white mb-2"
            >Sistema de calculo: {{ store.selectedCalculateSystem }}</Tag
          >
          <div v-if="store.selectedCalculateSystem === 1">
            <div class="flex justify-content-start mb-2">
              <Tag class="bg-orange-400 text-white w-6 mr-2"
                >Cantidad: {{ store.quantity }} uds</Tag
              >
              <Tag class="bg-orange-400 text-white w-6"
                >Precio Unitario: {{ store.unitaryPrice }} €</Tag
              >
            </div>
          </div>
          <div v-else>
            <div class="flex justify-content-start mb-2">
              <Tag class="bg-orange-400 text-white w-6 mr-2">Kg/€: {{ store.priceKg }}</Tag>
              <Tag class="bg-orange-400 text-white w-6">Peso: {{ store.weight }}</Tag>
            </div>
          </div>
          <Tag v-if="store.shoppinglistItemPrice" class="bg-red-400 text-white w-full"
            >Precio del item: {{ store.shoppinglistItemPrice }} €</Tag
          >
        </div>
      </div>
    </template>
  </Card>
</template>
<style lang="css"></style>
