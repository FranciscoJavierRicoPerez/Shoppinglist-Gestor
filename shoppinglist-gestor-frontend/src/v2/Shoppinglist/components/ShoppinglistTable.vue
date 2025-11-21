<script setup lang="ts">
import { useShoppinglistStore } from '@/v2/Shoppinglist/stores/shoppinglistStore'
import { onMounted, ref } from 'vue'
import { useGetShoppinglistTableMetadata } from '@/v2/Shoppinglist/application/useGetShoppinglistTableMetadata'
import ShoppinglistCardInfo from '@/v2/Shoppinglist/components/ShoppinglistCardInfo.vue'

import Panel from 'primevue/panel'
import Tabs from 'primevue/tabs'
import TabList from 'primevue/tablist'
import Tab from 'primevue/tab'
import TabPanels from 'primevue/tabpanels'
import TabPanel from 'primevue/tabpanel'
import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'

const tabsPanelIds = ref<string[]>(['0', '1', '2'])
const actualPanelSelected = ref<number>(-1)

const shoppinglistTable = ref<ShoppinglistMetadata[]>([])
const shoppinglistActiveTable = ref<ShoppinglistMetadata[]>([])
const shoppinglistNoActiveTable = ref<ShoppinglistMetadata[]>([])

const shoppinglistStore = useShoppinglistStore()
const { refetch: getShoppinglistTableMetadata } = useGetShoppinglistTableMetadata()

onMounted(async () => {
  shoppinglistTable.value = await getShoppinglistTableMetadata()
  shoppinglistStore.setShoppinglistArray(shoppinglistTable.value)
  updateShoppinglistTables()
})

/* watch(actualPanelSelected, (newActualPanelSelected) => {
  storeShoppinglistFilter.setActualPanelId(newActualPanelSelected)
}) */

/* async function addNewShoppinglist() {
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
} */

function updateShoppinglistTables() {
  shoppinglistActiveTable.value = shoppinglistStore.getActiveShoppinglist()
  shoppinglistNoActiveTable.value = shoppinglistStore.getNoActiveShoppinglist()
}

/* function createToast(toastOptions: ToastMessageOptions) {
  toast.add({
    severity: toastOptions.severity,
    summary: toastOptions.summary,
    detail: toastOptions.detail,
    life: toastOptions.life,
  })
} */

function selectTableToShow(element: string) {
  return element === '0'
    ? shoppinglistStore.shoppinglistArray
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
    <!-- <div class="cardOrganization">
      <Button label="Nueva lista" icon="pi pi-plus" @click="addNewShoppinglist"></Button>
    </div> -->
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
<style lang="css">
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
