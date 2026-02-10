<script setup lang="ts">
import Panel from 'primevue/panel'
import Tag from 'primevue/tag'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ShoppinglistMetadata } from '@/Shoppinglist/domain/ShoppinglistMetadata'
import { computed, onMounted, ref } from 'vue'

const shoppinglistDetailsStore = useShoppinglistDetailStore()
const shoppinglistMetadata = ref<ShoppinglistMetadata>()

/** --- COMPUTED ----*/
const informacionHeaderText = computed(() => {
  return 'Información'
})

const shoppinglistStatusText = computed(() => {
  return shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.isActive
    ? 'ABIERTA'
    : 'CERRADA'
})

const shoppinglistCodeText = computed(() => {
  return shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.code
})

const shoppinglistTotalPriceText = computed(() => {
  let response = 'Precio total: '
  if (shoppinglistDetailsStore.totalPrice > 0) {
    response += shoppinglistDetailsStore.totalPrice.toFixed(2) + '€'
  } else {
    response += '0,00 €'
  }
  return response
})

const shoppinglistDatesText = computed(() => {
  let response: string =
    'Lista de la compra del ' +
    shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.creationDate
  if (shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.closeDate) {
    response +=
      ' al ' + shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.closeDate
  }
  return response
})
/** ---------------- */
</script>
<template>
  <div>
    <Panel toggleable>
      <template #header>
        <div class="text-2xl italic">{{ informacionHeaderText }}</div>
      </template>
      <div class="flex flex-row flex-wrap gap-2">
        <Tag class="text-xl italic">{{ shoppinglistStatusText }}</Tag>
        <Tag class="text-xl italic" severity="info">{{ shoppinglistCodeText }}</Tag>
        <Tag class="text-xl italic" severity="danger">{{ shoppinglistTotalPriceText }}</Tag>
        <Tag class="text-xl italic" severity="warn">
          {{ shoppinglistDatesText }}
        </Tag>
      </div>
    </Panel>
  </div>
</template>
<style lang="css"></style>
