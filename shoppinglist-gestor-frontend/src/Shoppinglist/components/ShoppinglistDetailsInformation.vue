<script setup lang="ts">
import Panel from 'primevue/panel'
import Tag from 'primevue/tag'
// import Button from 'primevue/button'
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
  return shoppinglistMetadata.value?.isActive ? 'ABIERTA' : 'CERRADA'
})

const shoppinglistCodeText = computed(() => {
  return shoppinglistMetadata.value?.code
})

const shoppinglistTotalPriceText = computed(() => {
  return 'Precio total: ' + shoppinglistDetailsStore.totalPrice + '€'
})

const shoppinglistDatesText = computed(() => {
  let response: string = 'Lista de la compra del ' + shoppinglistMetadata.value?.creationDate
  if (shoppinglistMetadata.value?.closeDate) {
    response += ' al ' + shoppinglistMetadata.value.closeDate
  }
  return response
})
/** ---------------- */
onMounted(() => {
  shoppinglistMetadata.value = shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata
  if (shoppinglistDetailsStore.shoppinglistDetails)
    shoppinglistDetailsStore.totalPrice =
      shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata.totalPrice
})
</script>
<template>
  <div>
    <Panel toggleable>
      <template #header>
        <div class="text-2xl italic">{{ informacionHeaderText }}</div>
      </template>
      <div class="flex flex-column flex-wrap gap-2">
        <div class="flex flex-row gap-2 justify-content-between">
          <Tag>{{ shoppinglistStatusText }}</Tag>
          <Tag severity="info">{{ shoppinglistCodeText }}</Tag>
        </div>
        <Tag severity="danger">{{ shoppinglistTotalPriceText }}</Tag>
        <Tag severity="warn">
          {{ shoppinglistDatesText }}
        </Tag>
      </div>
    </Panel>
  </div>
</template>
<style lang="css"></style>
