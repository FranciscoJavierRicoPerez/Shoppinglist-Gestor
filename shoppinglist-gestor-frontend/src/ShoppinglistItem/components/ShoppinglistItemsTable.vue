<script setup lang="ts">
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ShoppinglistItemMetadata } from '@/ShoppinglistItem/domain/ShoppinglistItemMetadata'
import { onMounted, ref } from 'vue'
import ShoppinglistItemInfoCard from './ShoppinglistItemInfoCard.vue'
import Panel from 'primevue/panel'
import ScrollPanel from 'primevue/scrollpanel'

const shoppinglistDetailsStore = useShoppinglistDetailStore()

onMounted(() => {
  shoppinglistDetailsStore.items = shoppinglistDetailsStore.shoppinglistDetails?.items
    ? shoppinglistDetailsStore.shoppinglistDetails?.items
    : []
})
</script>
<template>
  <Panel toggleable>
    <template #header>
      <div class="text-2xl italic">Listado de productos</div>
    </template>
    <ScrollPanel style="width: 100%; height: 50rem">
      <div v-for="item in shoppinglistDetailsStore.items">
        <ShoppinglistItemInfoCard class="mb-2" :shoppinglistItem="item"></ShoppinglistItemInfoCard>
      </div>
    </ScrollPanel>
  </Panel>
</template>
