<script setup lang="ts">
import { defaultShoppinglist, type Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { onMounted, ref, watch } from 'vue'
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
import { useShoppinglistFilterStore } from '@/Shoppinglist/stores/shoppinglistFilterStore'
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const shoppinglistActiveTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const shoppinglistNoActiveTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }])
const { refetch: createShoppinglistMetadata } = useCreateShoppinglistMetadata()
const store = useShoppinglistStore()
const toast = useToast()
const tabsPanelIds = ref<string[]>(['0', '1', '2'])
const actualPanelSelected = ref<number>(-1)

const storeShoppinglistFilter = useShoppinglistFilterStore()

onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist()
  store.setShoppinglistArray(shoppinglistTable.value)
  updateShoppinglistTables()
})

watch(actualPanelSelected, (newActualPanelSelected) => {
  storeShoppinglistFilter.setActualPanelId(newActualPanelSelected)
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
  debugger
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

function selectTableToShow(element: string) {
  return element === '0'
    ? store.shoppinglistArray
    : element === '1'
      ? shoppinglistActiveTable.value
      : shoppinglistNoActiveTable.value
}

function generateTabTitleName(element: string) {
  // REFACTORIZAR
  // SERIA INTERESANTE QUE LA LISTA DE TABSID SEA UN MAP CLAVE - VALOR
  return element === '0' ? 'Todas' : element === '1' ? 'Activas' : 'Archivadas'
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
        <div v-for="panelId in tabsPanelIds">
          <Tab :value="panelId" @click="actualPanelSelected = Number(panelId)">
            {{ generateTabTitleName(panelId) }}</Tab
          >
        </div>
      </TabList>
      <TabPanels>
        <div v-for="panelId in tabsPanelIds">
          <TabPanel :value="panelId">
            <div class="cardOrganization">
              <div v-for="shoppinglistData of selectTableToShow(panelId)">
                <ShoppinglistCardInfo
                  :shoppinglist="shoppinglistData"
                  @update-shoppinglist-tables="updateShoppinglistTables"
                ></ShoppinglistCardInfo>
              </div>
            </div>
          </TabPanel>
        </div>
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
