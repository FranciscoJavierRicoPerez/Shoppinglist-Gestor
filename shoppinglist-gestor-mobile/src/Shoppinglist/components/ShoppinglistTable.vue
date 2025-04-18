<script setup lang="ts">
import { defaultShoppinglist, type Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { onMounted, ref, watch } from 'vue'
import { useGetAllShoppinglist } from '@/Shoppinglist/application/useGetAllShoppinglist'
import ShoppinglistCardInfo from '@/Shoppinglist/components/ShoppinglistCardInfo.vue'
import { useShoppinglistStore } from '@/Shoppinglist/stores/shoppinglistStore'
import { useCreateShoppinglistMetadata } from '@/Shoppinglist/application/useCreateShoppinglistMetadata'
import { useShoppinglistFilterStore } from '@/Shoppinglist/stores/shoppinglistFilterStore'
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const shoppinglistActiveTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const shoppinglistNoActiveTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const { refetch: createShoppinglistMetadata } = useCreateShoppinglistMetadata()
const store = useShoppinglistStore()

const storeShoppinglistFilter = useShoppinglistFilterStore()

onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist()
  store.setShoppinglistArray(shoppinglistTable.value)
  updateShoppinglistTables()
})


async function addNewShoppinglist() {
  console.log('INFO: Añadiendo una nueva lista de la compra')
  let shoppinglistMetadata: Shoppinglist = await createShoppinglistMetadata()
  if (shoppinglistMetadata) {
    store.addShoppinglist(shoppinglistMetadata)
    updateShoppinglistTables()
  } 
}

function updateShoppinglistTables() {
  debugger
  shoppinglistActiveTable.value = store.getActiveShoppinglist()
  shoppinglistNoActiveTable.value = store.getNoActiveShoppinglist()
}

</script>
<template>
  <p>PRUEBA</p>
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
