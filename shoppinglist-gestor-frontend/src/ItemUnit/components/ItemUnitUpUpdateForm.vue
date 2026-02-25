<script setup lang="ts">
import ScrollPanel from 'primevue/scrollpanel'
import Tag from 'primevue/tag'
import { computed, onMounted, ref, type PropType } from 'vue'
import { useGetItemUnitUpGroupedByPrice } from '../application/useGetItemUnitUpGroupedByPrice'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import ItemUnitUpDetailsCard from './ItemUnitUpDetailsCard.vue'
import { useItemUnitUpGroupedByPriceStore } from '../store/itemUnitUpGroupedByPriceStore'

const { refetch: getItemUnitsUpGroupedByPrice } = useGetItemUnitUpGroupedByPrice()

const store = useItemUnitUpGroupedByPriceStore()

const props = defineProps({
  shoppinglistItem: {
    type: Object as PropType<ShoppinglistItemMetadata>,
    default: () => null,
  },
})

onMounted(async () => {
  const response = await getItemUnitsUpGroupedByPrice(props.shoppinglistItem.idShoppinglistItem)
  store.totalPrice = response.totalPrice
  store.itemsGrouped = response.itemsUpGrouped
})

const totalPriceText = computed(() => {
  return 'Precio total: ' + store.totalPrice
})
</script>
<template>
  <div class="flex flex-column gap-2">
    <Tag>{{ totalPriceText }}</Tag>
    <ScrollPanel style="width: 100%; height: 25rem">
      <div v-for="value in store.itemsGrouped">
        <div class="flex flex-column mb-2">
          <ItemUnitUpDetailsCard :itemUnitUpMetadata="value"></ItemUnitUpDetailsCard>
        </div>
      </div>
    </ScrollPanel>
  </div>
</template>
<style lang="css"></style>
