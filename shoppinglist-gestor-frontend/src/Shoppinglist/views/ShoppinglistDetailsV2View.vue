<script setup lang="ts">
import ShoppinglistDetailsInformation from '@/Shoppinglist/components/ShoppinglistDetailsInformation.vue'
import ShoppinglistItemCreateForm from '@/ShoppinglistItem/components/ShoppinglistItemCreateForm.vue'
import ShoppinglistItemsTable from '@/ShoppinglistItem/components/ShoppinglistItemsTable.vue'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import { useGetShoppinlistDetailsMetadata } from '@/Shoppinglist/application/useGetShopinglistDetailsMetadata'
import { useRoute } from 'vue-router'
import { onMounted } from 'vue'
import Toast from 'primevue/toast'
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
  <Toast></Toast>
  <div class="flex align-items-start justify-content-between flex-wrap gap-2">
    <ShoppinglistDetailsInformation class="grow-0 shadow-5"></ShoppinglistDetailsInformation>
    <ShoppinglistItemsTable class="grow shadow-5"></ShoppinglistItemsTable>
    <ShoppinglistItemCreateForm class="grow-0 shadow-5"></ShoppinglistItemCreateForm>
  </div>
</template>
