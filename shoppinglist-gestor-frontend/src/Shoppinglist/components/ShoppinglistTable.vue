<script setup lang="ts">
import { defaultShoppinglist, type Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { onMounted, ref } from 'vue'
import { useGetAllShoppinglist } from '../application/useGetAllShoppinglist'
import ShoppinglistCardInfo from '@/Shoppinglist/components/ShoppinglistCardInfo.vue'
import Panel from 'primevue/panel'
import { useShoppinglistStore } from '@/Shoppinglist/stores/shoppinglistStore'
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const store = useShoppinglistStore()
onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist()
  store.setShoppinglistArray(shoppinglistTable.value)
})
</script>
<template>
  <Panel class="panelShoppinglistTable">
    <template #header>
      <span class="panelHeader">Listas de la compra</span>
    </template>
    <div class="cardOrganization">
      <div v-for="shoppinglistData of store.shoppinglistArray">
        <ShoppinglistCardInfo :shoppinglist="shoppinglistData"></ShoppinglistCardInfo>
      </div>
    </div>
  </Panel>
</template>
<style>
.panelHeader {
  font-size: xx-large;
  font-weight: bold;
}
.panelShoppinglistTable {
  margin-top: 1rem;
}
.cardOrganization {
  display: flex;
  flex-wrap: wrap;
}
</style>
