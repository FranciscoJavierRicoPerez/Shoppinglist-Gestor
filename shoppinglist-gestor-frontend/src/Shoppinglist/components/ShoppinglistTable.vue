<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import ShoppinglistCardInfo from '@/Shoppinglist/components/ShoppinglistCardInfo.vue'
import Panel from 'primevue/panel'
import Button from 'primevue/button'
import Toast, { type ToastMessageOptions } from 'primevue/toast'
import { useCreateShoppinglistMetadata } from '@/Shoppinglist/application/useCreateShoppinglistMetadata'
import { useToast } from 'primevue/usetoast'
import Tabs from 'primevue/tabs'
import TabList from 'primevue/tablist'
import Tab from 'primevue/tab'
import TabPanels from 'primevue/tabpanels'
import TabPanel from 'primevue/tabpanel'
import VirtualScroller from 'primevue/virtualscroller'
import ScrollPanel from 'primevue/scrollpanel'
import { useShoppinglistFilterStore } from '@/Shoppinglist/stores/shoppinglistFilterStore'
import { defaultShoppinglistTable, type ShoppinglistTable } from '../domain/ShoppinglistTable'
import { useGetShoppinglistTableMetadata } from '@/Shoppinglist/application/useGetShoppinglistTableMetadata'
import { useShoppinglistTableStore } from '@/Shoppinglist/stores/shoppinglistTableStore'
import type { ShoppinglistMetadata } from '../domain/ShoppinglistMetadata'
const { refetch: getShoppinglistTableMetadata } = useGetShoppinglistTableMetadata()

const shoppinglistTable = ref<ShoppinglistTable>({ ...defaultShoppinglistTable })
const shoppinglistTableStore = useShoppinglistTableStore()

const shoppinglistActiveTable = ref<ShoppinglistMetadata[]>([])
const shoppinglistNoActiveTable = ref<ShoppinglistMetadata[]>([])
const { refetch: createShoppinglistMetadata } = useCreateShoppinglistMetadata()
const toast = useToast()
const tabsPanelIds = ref<string[]>(['0', '1', '2'])
const actualPanelSelected = ref<number>(-1)

// TODO: ES POSIBLE QUE HAYA QUE REFACTORIZAR ESTE STORE
const storeShoppinglistFilter = useShoppinglistFilterStore()

onMounted(async () => {
  shoppinglistTable.value = await getShoppinglistTableMetadata()
  shoppinglistTableStore.setShoppinglistTable(shoppinglistTable.value.shoppinglistTable)
  updateShoppinglistTables()
})

watch(actualPanelSelected, (newActualPanelSelected) => {
  storeShoppinglistFilter.setActualPanelId(newActualPanelSelected)
})

async function addNewShoppinglist() {
  console.log('INFO: Añadiendo una nueva lista de la compra')
  let shoppinglistMetadata: ShoppinglistMetadata = await createShoppinglistMetadata()
  if (shoppinglistMetadata) {
    shoppinglistTableStore.addShoppinglist(shoppinglistMetadata)
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
  shoppinglistActiveTable.value = shoppinglistTableStore.getActiveShoppinglist()
  shoppinglistNoActiveTable.value = shoppinglistTableStore.getNoActiveShoppinglist()
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
    ? shoppinglistTableStore.shoppinglistTable
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
  <Panel class="w-full h-full mt-2 grow shadow-5 max-w-content" toggleable>
    <template #header>
      <div class="text-2xl italic">Tus listas de la compra</div>
    </template>
    <template #footer>
      Seccion de informacion general sobre las listas, cantidad, precio de la suma de todas, etc...
      REVISAR
    </template>
    <div class="flex flex-wrap">
      <Button
        class="bg-green-500 w-3"
        label="Nueva lista"
        icon="pi pi-plus"
        @click="addNewShoppinglist"
      ></Button>
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
            <ScrollPanel style="width: 100%; height: 600px">
              <div class="flex flex-row flex-wrap justify-content-center">
                <div v-for="shoppinglistData of selectTableToShow(panelId)">
                  <ShoppinglistCardInfo
                    class="shadow-3"
                    :shoppinglist="shoppinglistData"
                    @update-shoppinglist-tables="updateShoppinglistTables"
                  ></ShoppinglistCardInfo>
                </div>
              </div>
            </ScrollPanel>
          </TabPanel>
        </div>
      </TabPanels>
    </Tabs>
  </Panel>
</template>
