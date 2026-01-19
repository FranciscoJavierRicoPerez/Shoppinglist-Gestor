<script setup lang="ts">
import ShoppinglistDetailsInformation from '@/Shoppinglist/components/ShoppinglistDetailsInformation.vue'
import ShoppinglistItemCreateForm from '@/ShoppinglistItem/components/ShoppinglistItemCreateForm.vue'
import ShoppinglistItemsTable from '@/ShoppinglistItem/components/ShoppinglistItemsTable.vue'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import { onMounted } from 'vue'
import { useGetShoppinlistDetailsMetadata } from '@/Shoppinglist/application/useGetShopinglistDetailsMetadata'
import { useRoute } from 'vue-router'

/**
 * - Obtener el detalle de la lista de la compra del EP: /api/shoppinglistview/v1/{id}/details
 * - Almacenar en un store para el detalle -> shoppinglistDetailStore.ts
 */
const route = useRoute()
const shoppinglistDetailStore = useShoppinglistDetailStore()
const { refetch: getShoppinglistDetailsMetadata } = useGetShoppinlistDetailsMetadata()

onMounted(async () => {
  const param = Number(route.params.id)
  shoppinglistDetailStore.shoppinglistDetails = await getShoppinglistDetailsMetadata(param)
})
</script>
<template>
  <div
    class="flex flex-column sm:flex-row align-items-start justify-content-between flex-wrap gap-1"
  >
    <ShoppinglistDetailsInformation class="flex-grow-0 shadow-5"></ShoppinglistDetailsInformation>
    <ShoppinglistItemsTable class="flex-grow-1 shadow-5"></ShoppinglistItemsTable>
    <ShoppinglistItemCreateForm class="flex-grow-0 shadow-5"></ShoppinglistItemCreateForm>
  </div>
</template>
