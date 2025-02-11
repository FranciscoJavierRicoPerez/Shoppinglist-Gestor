<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { defaultProductDetails, type ProductDetails } from '@/Product/domain/ProductDetails'
import { useGetProductDetails } from '@/Product/application/useGetProductDetails'

import Panel from 'primevue/panel'
import DataView from 'primevue/dataview'
import Tag from 'primevue/tag'

const { refetch: getProductDetails } = useGetProductDetails()

const productDetails = ref<ProductDetails>({ ...defaultProductDetails })

onMounted(async () => {
  productDetails.value = await getProductDetails()
})
</script>
<template>
  <Panel>
    <template #header><span class="panelHeader">Detalle del producto</span></template>
    <p>
      <Tag severity="success" style="margin-right: 1rem">{{ productDetails.name }}</Tag>
      <Tag severity="danger" style="margin-right: 1rem"
        >Precio total: {{ productDetails.price }}
      </Tag>
      <Tag severity="warn">Unidades: {{ productDetails.quantity }}</Tag>
    </p>
    <DataView :value="productDetails.productUnitList">
      <template #list="slotProps">
        <div class="flex flex-col">
          <div v-for="(item, index) in slotProps.items" :key="index">{{ item.id }}</div>
        </div>
      </template>
    </DataView>
  </Panel>
</template>
<style lang="css">
.panelHeader {
  font-size: xx-large;
  font-weight: bold;
}
</style>
