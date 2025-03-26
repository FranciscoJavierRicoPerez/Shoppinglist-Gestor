<script setup lang="ts">
import { defaultShoppinglist, type Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { onMounted, ref } from 'vue'
import { useGetAllShoppinglist } from '@/Shoppinglist/application/useGetAllShoppinglist'
import ShoppinglistCardInfo from '@/Shoppinglist/components/ShoppinglistCardInfo.vue'
import Panel from 'primevue/panel'
import Button from 'primevue/button'
import Toast, { type ToastMessageOptions } from 'primevue/toast'
import { useShoppinglistStore } from '@/Shoppinglist/stores/shoppinglistStore'
import { useCreateShoppinglistMetadata } from '@/Shoppinglist/application/useCreateShoppinglistMetadata'
import { useToast } from 'primevue/usetoast'
import Tabs from 'primevue/tabs'
import TabList from 'primevue/tablist'
import Tab from 'primevue/tab'
import TabPanels from 'primevue/tabpanels'
import TabPanel from 'primevue/tabpanel'
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const shoppinglistActiveTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const shoppinglistNoActiveTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const { refetch: createShoppinglistMetadata } = useCreateShoppinglistMetadata()
const store = useShoppinglistStore()
const toast = useToast()

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
    createToast({
      severity: 'success',
      summary: 'Añadida lista de la compra',
      detail: 'Codigo de la nueva lista de la compra: ' + shoppinglistMetadata.code,
      life: 3000,
    })
  } else {
    createToast({
      severity: 'error',
      summary: 'Error añadiendo lista de la compra',
      detail: 'Se ha producido un error añadiendo la lista de la compra',
      life: 3000,
    })
  }
}

function updateShoppinglistTables() {
  shoppinglistActiveTable.value = store.getActiveShoppinglist()
  shoppinglistNoActiveTable.value = store.getNoActiveShoppinglist()
}

function createToast(toastOptions: ToastMessageOptions) {
  toast.add({
    severity: toastOptions.severity,
    summary: toastOptions.summary,
    detail: toastOptions.detail,
    life: toastOptions.life,
  })
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
    <Tabs value="0">
      <TabList>
        <Tab value="0">Todas</Tab>
        <Tab value="1">Activas</Tab>
        <Tab value="2">Archivadas</Tab>
      </TabList>
      <TabPanels>
        <TabPanel value="0">
          <div class="cardOrganization">
            <div v-for="shoppinglistData of store.shoppinglistArray">
              <ShoppinglistCardInfo :shoppinglist="shoppinglistData"></ShoppinglistCardInfo>
            </div>
          </div>
        </TabPanel>
        <TabPanel value="1">
          <div class="cardOrganization">
            <div v-for="shoppinglistData of shoppinglistActiveTable">
              <ShoppinglistCardInfo :shoppinglist="shoppinglistData"></ShoppinglistCardInfo>
            </div>
          </div>
        </TabPanel>
        <TabPanel value="2">
          <div class="cardOrganization">
            <div v-for="shoppinglistData of shoppinglistNoActiveTable">
              <ShoppinglistCardInfo :shoppinglist="shoppinglistData"></ShoppinglistCardInfo>
            </div>
          </div>
        </TabPanel>
      </TabPanels>
    </Tabs>
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
