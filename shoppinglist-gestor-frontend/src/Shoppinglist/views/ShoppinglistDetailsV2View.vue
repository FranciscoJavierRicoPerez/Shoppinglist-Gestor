<script setup lang="ts">
import ShoppinglistDetailsInformation from '@/Shoppinglist/components/ShoppinglistDetailsInformation.vue'
import ShoppinglistItemCreateForm from '@/ShoppinglistItem/components/ShoppinglistItemCreateForm.vue'
import ShoppinglistItemsTable from '@/ShoppinglistItem/components/ShoppinglistItemsTable.vue'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import { useGetShoppinglistDetailsMetadata } from '@/Shoppinglist/application/useGetShoppinglistDetailsMetadata'
import { useRoute } from 'vue-router'
import { onMounted } from 'vue'
import Toast from 'primevue/toast'
/**
 * - Obtener el detalle de la lista de la compra del EP: /api/shoppinglistview/v1/{id}/details
 * - Almacenar en un store para el detalle -> shoppinglistDetailStore.ts
 */
const route = useRoute()
const shoppinglistDetailStore = useShoppinglistDetailStore()
const { refetch: getShoppinglistDetailsMetadata } = useGetShoppinglistDetailsMetadata()

onMounted(async () => {
  const param = Number(route.params.id)
  shoppinglistDetailStore.shoppinglistDetails = await getShoppinglistDetailsMetadata(param)
  shoppinglistDetailStore.items = shoppinglistDetailStore.shoppinglistDetails?.items
    ? shoppinglistDetailStore.shoppinglistDetails?.items
    : []
  shoppinglistDetailStore.shoppinglistDetails
    ? (shoppinglistDetailStore.totalPrice =
        shoppinglistDetailStore.shoppinglistDetails?.shoppinglistMetadata.totalPrice)
    : -1
})
</script>
<template>
  <Toast></Toast>
  <div class="flex flex-column gap-2">
    <ShoppinglistDetailsInformation class="grow shadow-5"></ShoppinglistDetailsInformation>
    <div class="flex flex-wrap gap-2 align-items-start sm:flex-nowrap">
      <ShoppinglistItemsTable class="w-full grow-0 shadow-5"></ShoppinglistItemsTable>
      <ShoppinglistItemCreateForm class="w-full grow shadow-5"></ShoppinglistItemCreateForm>
    </div>
  </div>
</template>
