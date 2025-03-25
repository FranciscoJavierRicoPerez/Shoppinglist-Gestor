<script setup lang="ts">
import { defaultShoppinglist, type Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { onMounted, ref } from 'vue'
import { useGetAllShoppinglist } from '@/Shoppinglist/application/useGetAllShoppinglist'
import ShoppinglistCardInfo from '@/Shoppinglist/components/ShoppinglistCardInfo.vue'
import Panel from 'primevue/panel'
import Button from 'primevue/button'
import Toast from 'primevue/toast'
import { useShoppinglistStore } from '@/Shoppinglist/stores/shoppinglistStore'
import { useCreateShoppinglistMetadata } from '@/Shoppinglist/application/useCreateShoppinglistMetadata'
import { useToast } from 'primevue/usetoast'
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const { refetch: createShoppinglistMetadata } = useCreateShoppinglistMetadata()
const store = useShoppinglistStore()
const toast = useToast()
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
    toast.add({
      severity: 'success',
      summary: 'Añadida lista de la compra',
      detail: 'Codigo de la nueva lista de la compra: ' + shoppinglistMetadata.code,
      life: 3000,
    })
  } else {
    toast.add({
      severity: 'error',
      summary: 'Error añadiendo lista de la compra',
      detail: 'Se ha producido un error añadiendo la lista de la compra',
      life: 3000,
    })
  }
}
</script>
<template>
  <Toast></Toast>
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
