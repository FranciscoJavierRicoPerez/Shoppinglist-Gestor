<script setup lang="ts">
import Tag from 'primevue/tag'
import { computed, onMounted, ref, type PropType } from 'vue'
import type { ItemUnitWpMetadata } from '../domain/ItemUnitWpMetadata'
import { useGetItemUnitWpMetadata } from '../application/useGetItemUnitWpMetadata'
import { useUpdateItemWpFormStore } from '@/ItemUnit/store/updateItemWpFormStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import InformationCard from '@/Shared/components/InformationCard.vue'

const itemUnitWpMetadata = ref<ItemUnitWpMetadata>()
const { refetch: getItemUnitWpMetadata } = useGetItemUnitWpMetadata()
const store = useUpdateItemWpFormStore()

const emit = defineEmits(['idItemUnitWp', 'idItemUnit'])

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
  if (props.shoppinglistItem && props.shoppinglistItem.idShoppinglistItem)
    itemUnitWpMetadata.value = await getItemUnitWpMetadata(
      props.shoppinglistItem.idShoppinglistItem,
    )
  emit('idItemUnitWp', itemUnitWpMetadata.value?.idItemUnitWp)
  emit('idItemUnit', itemUnitWpMetadata.value?.idItemUnit)
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

const newProductPriceText = computed(() => {
  return 'Precio del producto actualizado: ' + store.newProductPrice?.toFixed(2) + ' €'
})

const newPriceKgText = computed(() => {
  return 'Precio actualizado: ' + store.newPriceKg + ' Kg/€'
})

const newWeightText = computed(() => {
  return 'Peso actualizado: ' + store.newWeight + ' Kg'
})

const isUpdateInformationEmpty = computed(() => {
  return store.newPriceKg === null || store.newPriceKg == null
})
</script>
<template>
  <div v-if="props.isUpdateInfo">
    <div v-if="isUpdateInformationEmpty">
      <InformationCard
        class="mb-2"
        :information="{
          header: 'Información',
          content: 'Actualmente no hay información suficiente para generar el resumen',
        }"
      ></InformationCard>
    </div>
    <div v-else class="flex flex-column justify-content-start gap-2">
      <div class="flex flex-row gap-2">
        <Tag severity="primary" class="w-full">{{ newPriceKgText }}</Tag>
        <Tag severity="secondary" class="w-full">{{ newWeightText }}</Tag>
      </div>
      <div v-if="store.newProductPrice" class="flex flex-row gap-2">
        <Tag severity="info" class="w-full">{{ newProductPriceText }}</Tag>
      </div>
    </div>
  </div>
  <div v-else>
    <div class="flex flex-column gap-2 justify-content-center">
      <div class="flex flex-row">
        <Tag severity="warn" class="w-full">{{ productName }}</Tag>
      </div>
      <div class="flex flex-row gap-2">
        <Tag severity="primary" class="w-full">{{ actualPriceKg }}</Tag>
        <Tag severity="secondary" class="w-full">{{ actualWeight }}</Tag>
      </div>
      <div class="flex flex-row gap-2">
        <Tag severity="info" class="w-full">{{ actualCalculatedPrice }}</Tag>
      </div>
    </div>
  </div>
</template>
