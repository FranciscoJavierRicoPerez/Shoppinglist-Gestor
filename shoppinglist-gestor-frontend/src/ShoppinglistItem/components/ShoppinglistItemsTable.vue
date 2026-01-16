<script setup lang="ts">
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import { onMounted, ref } from 'vue'
import ShoppinglistItemInfoCard from './ShoppinglistItemInfoCard.vue'
import Panel from 'primevue/panel'
import ScrollPanel from 'primevue/scrollpanel'

const shoppinglistDetailsStore = useShoppinglistDetailStore()
const items = ref<ShoppinglistItemMetadata[]>([])

onMounted(() => {
  items.value = shoppinglistDetailsStore.shoppinglistDetails?.items
    ? shoppinglistDetailsStore.shoppinglistDetails?.items
    : []
})
</script>
<template>
  <Panel toggleable>
    <template #header>
      <h3>Listado de productos</h3>
    </template>
    <ScrollPanel style="width: 100%; height: 50rem">
      <div v-for="item in items">
        <ShoppinglistItemInfoCard
          style="margin-bottom: 1rem"
          :shoppinglistItem="item"
        ></ShoppinglistItemInfoCard>
      </div>
    </ScrollPanel>
  </Panel>
</template>
