<script setup lang="ts">
import { defaultShoppinglist, type Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { onMounted, ref } from 'vue'
import { useGetAllShoppinglist } from '../application/useGetAllShoppinglist'
import ShoppinglistCardInfo from '@/Shoppinglist/components/ShoppinglistCardInfo.vue'
import Panel from 'primevue/panel'
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])

onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist()
})
</script>
<template>
  <Panel header="Listas de la compra" class="panelShoppinglistTable">
    <div class="cardOrganization">
      <div v-for="shoppinglistData of shoppinglistTable">
        <ShoppinglistCardInfo :shoppinglist="shoppinglistData"></ShoppinglistCardInfo>
      </div>
    </div>
  </Panel>
</template>
<style>
.panelShoppinglistTable {
  margin-top: 1rem;
  font-size: xx-large;
}
.cardOrganization {
  display: flex;
  flex-wrap: wrap;
}
</style>
