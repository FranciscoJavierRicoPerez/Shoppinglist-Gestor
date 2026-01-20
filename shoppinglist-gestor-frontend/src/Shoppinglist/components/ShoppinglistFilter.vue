<script setup lang="ts">
import Panel from 'primevue/panel'
import DatePicker from 'primevue/datepicker'
import { ref } from 'vue'
import InputText from 'primevue/inputtext'
import InputNumber from 'primevue/inputnumber'
import { FloatLabel } from 'primevue'
import Button from 'primevue/button'
import {
  defaultShoppinglistFilter,
  type ShoppinglistFilter,
} from '@/Shoppinglist/domain/ShoppinglistFilter'
import { useGetShoppinglistFiltered } from '@/Shoppinglist/application/useGetShoppinglistFiltered'
import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { useShoppinglistStore } from '@/Shoppinglist/stores/shoppinglistStore'
import { useGetAllShoppinglist } from '@/Shoppinglist/application/useGetAllShoppinglist'
import { useShoppinglistFilterStore } from '@/Shoppinglist/stores/shoppinglistFilterStore'
import { useShoppinglistTableStore } from '@/Shoppinglist/stores/shoppinglistTableStore'
import type { ShoppinglistMetadata } from '../domain/ShoppinglistMetadata'
import {
  defaultShoppinglistTable,
  type ShoppinglistTable,
} from '@/Shoppinglist/domain/ShoppinglistTable'
import { useGetShoppinglistTableMetadata } from '../application/useGetShoppinglistTableMetadata'
const storeShoppinglistFilter = useShoppinglistFilterStore()
// const store = useShoppinglistStore()

const shoppinglistTableStore = useShoppinglistTableStore()

const { refetch: getAllShoppinglistFiltered } = useGetShoppinglistFiltered()
const { refetch: getShoppinglistTableMetadata } = useGetShoppinglistTableMetadata()
const shoppinglistFilterForm = ref<ShoppinglistFilter>({ ...defaultShoppinglistFilter })

const filteredShoppinglistTable = ref<ShoppinglistTable>({ ...defaultShoppinglistTable })

function verifyFilterForm() {
  return (
    shoppinglistFilterForm.value.code === '' &&
    shoppinglistFilterForm.value.closeDate === null &&
    shoppinglistFilterForm.value.creationDate === null &&
    shoppinglistFilterForm.value.totalPrice === null &&
    shoppinglistFilterForm.value.isActive === null
  )
}

async function searchShoppinglistByFilter() {
  shoppinglistFilterForm.value.isActive =
    storeShoppinglistFilter.actualPanelId === 0
      ? null
      : storeShoppinglistFilter.actualPanelId === 1
        ? true
        : false
  if (verifyFilterForm()) {
    filteredShoppinglistTable.value = await getShoppinglistTableMetadata()
  } else {
    filteredShoppinglistTable.value = await getAllShoppinglistFiltered(shoppinglistFilterForm.value)
  }
  if (filteredShoppinglistTable.value.shoppinglistTable.length > 0) {
    shoppinglistTableStore.setShoppinglistTable(filteredShoppinglistTable.value.shoppinglistTable)
  }
}
</script>
<template>
  <Panel class="grew-0 shadow-5 w-full h-full mt-2" toggleable>
    <template #header>
      <div class="text-2xl italic">Filtrado de listas de compras</div>
    </template>
    <template #footer>
      <Button
        class="w-full bg-green-500"
        icon="pi pi-search"
        aria-label="Search"
        @click="searchShoppinglistByFilter()"
      />
    </template>
    <!-- Shoppinglist filter form -->
    <!-- New Version of the form with Flexbox -->
    <div class="flex flex-column align-items-start justify-content-start flex-wrap w-full">
      <FloatLabel class="flex w-full mb-1" style="width: 25rem" variant="on">
        <DatePicker
          id="datepicker-24h"
          v-model="shoppinglistFilterForm.creationDate"
          showTime
          hourFormat="24"
          class="w-full"
        />
        <label for="shoppinglistCreationDate">Fecha de creación</label>
      </FloatLabel>
      <FloatLabel class="flex w-full mb-1" variant="on">
        <DatePicker
          id="datepicker-24h"
          v-model="shoppinglistFilterForm.closeDate"
          showTime
          hourFormat="24"
          class="w-full"
        />
        <label for="shoppinglistCloseDate">Fecha de cierre</label>
      </FloatLabel>
      <FloatLabel class="flex w-full mb-1" variant="on">
        <InputText
          id="shoppinglistCode"
          v-model="shoppinglistFilterForm.code"
          class="w-full"
        ></InputText>
        <label for="shoppinglistCode">Código</label>
      </FloatLabel>
      <FloatLabel class="flex w-full mb-1" variant="on">
        <InputNumber
          id="shoppinglistTotalPrice"
          v-model="shoppinglistFilterForm.totalPrice"
          class="w-full"
        ></InputNumber>
        <label for="shoppinglistTotalPrice">Precio Total</label>
      </FloatLabel>
    </div>
  </Panel>
</template>
