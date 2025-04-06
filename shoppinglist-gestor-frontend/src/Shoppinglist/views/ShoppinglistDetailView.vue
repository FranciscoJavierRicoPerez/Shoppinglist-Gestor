<script setup lang="ts">
import { onMounted, ref } from 'vue'
import {
  defaultShoppinglistDetails,
  type ShoppinglistDetails,
} from '@/Shoppinglist/domain/ShoppinglistDetails'
import { useGetShoppinglistDetails } from '@/Shoppinglist/application/useGetShoppinglistDetails'
import Panel from 'primevue/panel'
import Tag from 'primevue/tag'
import Button from 'primevue/button'
import ShoppinglistItemCard from '@/ShoppinglistItem/components/ShoppinglistItemCard.vue'
const { refetch: getShoppinglistDetails } = useGetShoppinglistDetails()

const shoppinglistDetails = ref<ShoppinglistDetails>({ ...defaultShoppinglistDetails })

onMounted(async () => {
  // We have to obtain the object ShoppinglistDetails
  shoppinglistDetails.value = await getShoppinglistDetails()
})
</script>
<template>
  <Panel style="margin-bottom: 1rem">
    <template #header><span class="panelHeader">Informacion lista de la compra</span> </template>
    <p>
      <Tag style="font-size: large; margin-right: 1rem">
        <div v-if="shoppinglistDetails.isActive">
          <strong>ABIERTA</strong>
        </div>
        <div v-else>
          <strong>CERRADA</strong>
        </div>
      </Tag>
      <Tag severity="info" style="font-size: larger; margin-right: 1rem">{{
        shoppinglistDetails.code
      }}</Tag>
      <Tag severity="danger" style="font-size: larger"
        >Precio total {{ shoppinglistDetails.totalPrice }} €</Tag
      >
      <Tag
        severity="warn"
        style="font-size: larger"
        :class="{ multiDate: shoppinglistDetails.closeDate !== null }"
      >
        Lista de la compra del {{ shoppinglistDetails.creationDate }}
        <div v-if="shoppinglistDetails.closeDate !== null">
          <strong> al {{ shoppinglistDetails.closeDate }}</strong>
        </div>
      </Tag>
    </p>
  </Panel>
  <Panel>
    <template #header
      ><span class="panelHeader">Lista de items en la lista de la compra</span></template
    >
    <Button
      severity="info"
      label="Añadir nuevo item"
      style="margin-right: 10px; margin-bottom: 10px"
    ></Button>
    <Button severity="danger" label="Quitar items de la lista"></Button>
    <div class="cardOrganization">
      <!-- <ProductDataview :productList="shoppinglistDetails.products"></ProductDataview> -->
      <ShoppinglistItemCard
        :shoppinglistItemList="shoppinglistDetails.items"
      ></ShoppinglistItemCard>
    </div>
  </Panel>
</template>
<style lang="css">
.panelHeader {
  font-size: xx-large;
  font-weight: bold;
}
.multiDate {
  margin-left: 1rem;
}
.panelSeparations {
  margin-right: 0.2rem;
}

.cardOrganization {
  display: flex;
  flex-wrap: wrap;
}
</style>
