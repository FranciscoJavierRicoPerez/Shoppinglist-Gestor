<script setup lang="ts">
import { defaultShoppinglist, type Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { onMounted, ref } from 'vue'
import { useGetAllShoppinglist } from '@/Shoppinglist/application/useGetAllShoppinglist'
import ShoppinglistCardInfo from '@/Shoppinglist/components/ShoppinglistCardInfo.vue'
import Panel from 'primevue/panel'
import Button from 'primevue/button'
import { useShoppinglistStore } from '@/Shoppinglist/stores/shoppinglistStore'
import { useCreateShoppinglistMetadata } from '@/Shoppinglist/application/useCreateShoppinglistMetadata'
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const { refetch: createShoppinglistMetadata } = useCreateShoppinglistMetadata()
const store = useShoppinglistStore()
onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist()
  store.setShoppinglistArray(shoppinglistTable.value)
})

async function addNewShoppinglist() {
  console.log('INFO: Añadiendo una nueva lista de la compra')
  let shoppinglistMetadata: Shoppinglist = await createShoppinglistMetadata()
  if (shoppinglistMetadata) {
    // La añadimos a la lista de listas de la compra
    store.addShoppinglist(shoppinglistMetadata)
  }
}
</script>
<template>
  <Panel class="panelShoppinglistTable">
    <template #header>
      <span class="panelHeader">Listas de la compra</span>
    </template>
    <div class="cardOrganization">
      <Button label="Nueva lista" icon="pi pi-plus" @click="addNewShoppinglist"></Button>
    </div>
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
