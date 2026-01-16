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
  <div class="d-flex flex-row justify-content-start flex-wrap">
    <ShoppinglistDetailsInformation
      id="informationComponent"
      style="margin-bottom: 1rem; margin-right: 1rem; min-width: 21.5rem; width: 25%"
    ></ShoppinglistDetailsInformation>
    <ShoppinglistItemsTable
      id="tableComponent"
      style="margin-bottom: 1rem; margin-right: 1rem; min-width: 21.5rem; width: 50%"
    ></ShoppinglistItemsTable>
    <ShoppinglistItemCreateForm
      id="createFormComponent"
      style="margin-right: 1rem; min-width: 21.5rem; width: 23%"
    ></ShoppinglistItemCreateForm>
  </div>
</template>
