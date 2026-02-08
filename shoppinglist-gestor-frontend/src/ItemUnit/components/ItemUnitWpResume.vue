<script setup lang="ts">
import Tag from 'primevue/tag'
import { computed, onMounted, ref, type PropType } from 'vue'
import type { ItemUnitWpMetadata } from '../domain/ItemUnitWpMetadata'
import { useGetItemUnitWpMetadata } from '../application/useGetItemUnitWpMetadata'
import { useUpdateItemWpFormStore } from '@/ItemUnit/store/updateItemWpFormStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'

const itemUnitWpMetadata = ref<ItemUnitWpMetadata>()
const { refetch: getItemUnitWpMetadata } = useGetItemUnitWpMetadata()
const store = useUpdateItemWpFormStore()

const props = defineProps({
  isUpdateInfo: {
    type: Boolean as PropType<boolean>,
  },
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})

onMounted(async () => {
  if (props.shoppinglistItem.idShoppinglistItem)
    itemUnitWpMetadata.value = await getItemUnitWpMetadata(
      props.shoppinglistItem.idShoppinglistItem,
    )
})

const productName = computed(() => {
  return props.shoppinglistItem.name
})

const actualPriceKg = computed(() => {
  return 'Precio: ' + itemUnitWpMetadata.value?.priceKg.toFixed(2) + ' Kg/€'
})

const actualWeight = computed(() => {
  return 'Peso: ' + itemUnitWpMetadata.value?.weight.toFixed(2) + ' Kg'
})

const actualCalculatedPrice = computed(() => {
  return 'Coste del producto: ' + itemUnitWpMetadata.value?.calculatedPrice.toFixed(2) + ' €'
})
</script>
<template>
  <div v-if="props.isUpdateInfo">
    <div class="flex flex-column justify-content-start gap-2">
      <div class="flex flex-row gap-2">
        <Tag class="w-full">Nuevo Precio Kg/€: {{ store.newPriceKg }}€</Tag>
        <Tag class="w-full">Nuevo Peso: {{ store.newWeight }} Kg</Tag>
      </div>
      <div class="flex flex-row gap-2">
        <Tag class="w-full">Nuevo Precio del producto: {{ store.newProductPrice }}</Tag>
      </div>
    </div>
  </div>
  <div v-else>
    <div class="flex flex-column gap-2 justify-content-center">
      <div class="flex flex-row">
        <Tag class="w-full">{{ productName }}</Tag>
      </div>
      <div class="flex flex-row gap-2">
        <Tag class="w-full">{{ actualPriceKg }}</Tag>
        <Tag class="w-full">{{ actualWeight }}</Tag>
      </div>
      <div class="flex flex-row gap-2">
        <Tag class="w-full">{{ actualCalculatedPrice }}</Tag>
      </div>
    </div>
  </div>
</template>
