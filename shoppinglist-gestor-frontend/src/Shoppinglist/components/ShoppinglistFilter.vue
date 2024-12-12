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

const store = useShoppinglistStore()
const { refetch: getAllShoppinglistFiltered } = useGetShoppinglistFiltered()
const { refetch: getAllShoppinglist } = useGetAllShoppinglist()
const shoppinglistFilterForm = ref<ShoppinglistFilter>({ ...defaultShoppinglistFilter })

const filteredShoppinglist = ref<Shoppinglist[]>([])

function verifyFilterForm() {
  return (
    shoppinglistFilterForm.value.code === '' &&
    shoppinglistFilterForm.value.closeDate === null &&
    shoppinglistFilterForm.value.creationDate === null &&
    shoppinglistFilterForm.value.totalPrice === null
  )
}

async function searchShoppinglistByFilter() {
  if (verifyFilterForm()) {
    filteredShoppinglist.value = await getAllShoppinglist()
  } else {
    filteredShoppinglist.value = await getAllShoppinglistFiltered(shoppinglistFilterForm.value)
  }
  if (filteredShoppinglist.value.length > 0) {
    store.setShoppinglistArray(filteredShoppinglist.value)
  }
}
</script>
<template>
  <Panel class="panelFilter" toggleable>
    <template #header>
      <span class="panelHeader">Filtro de listas de compra</span>
    </template>
    <template #footer>
      <Button
        icon="pi pi-search"
        severity="success"
        aria-label="Search"
        @click="searchShoppinglistByFilter()"
      />
    </template>
    <!-- Shoppinglist filter form -->
    <div class="container-xxlg">
      <div class="row">
        <div class="col-4">
          <FloatLabel variant="on">
            <DatePicker
              id="datepicker-24h"
              v-model="shoppinglistFilterForm.creationDate"
              showTime
              hourFormat="24"
              class="bigInputs"
            />
            <label for="shoppinglistCreationDate">Fecha de creación</label>
          </FloatLabel>
        </div>
        <div class="col-4">
          <FloatLabel variant="on">
            <DatePicker
              id="datepicker-24h"
              v-model="shoppinglistFilterForm.closeDate"
              showTime
              hourFormat="24"
              class="bigInputs"
            />
            <label for="shoppinglistCloseDate">Fecha de cierre</label>
          </FloatLabel>
        </div>
        <div class="col-2">
          <FloatLabel variant="on">
            <InputText
              id="shoppinglistCode"
              v-model="shoppinglistFilterForm.code"
              class="smallInputs"
            ></InputText>
            <label for="shoppinglistCode">Código</label>
          </FloatLabel>
        </div>
        <div class="col-2">
          <FloatLabel variant="on">
            <InputNumber
              id="shoppinglistTotalPrice"
              v-model="shoppinglistFilterForm.totalPrice"
              class="smallInputs"
            ></InputNumber>
            <label for="shoppinglistTotalPrice">Precio Total</label>
          </FloatLabel>
        </div>
      </div>
    </div>
    <!--  End of the Shoppinglist filter form  -->
  </Panel>
</template>
<style lang="css">
.panelFilter {
  margin-top: 1rem;
}
.panelHeader {
  font-size: xx-large;
  font-weight: bold;
}
.bigInputs {
  width: 100%;
}
.smallInputs {
  width: 100%;
}
</style>
