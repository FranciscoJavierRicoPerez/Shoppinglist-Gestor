<script setup lang="ts">
import type { PropType } from 'vue'
import type { Product } from '@/Product/domain/Product'
import Card from 'primevue/card'
import Button from 'primevue/button'
import Tag from 'primevue/tag'
import { RouterLink } from 'vue-router'
defineProps({
  productList: {
    type: Object as PropType<Product[]>,
    default: () => null,
  },
})
</script>
<template>
  <div v-for="product in productList">
    <Card class="mainCard" style="max-width: 25rem">
      <template #title>{{ product.name }}</template>
      <template #content>
        <Tag severity="info" style="margin-right: 1rem">Cantidad {{ product.quantity }}</Tag>
        <Tag severity="primary">Precio {{ product.price }} €</Tag>
      </template>
      <template #footer>
        <RouterLink :to="`/product/${product.id}}`">
          <Button label="Ver" severity="help" style="width: 11.4rem"></Button>
        </RouterLink>
      </template>
    </Card>
  </div>
</template>
<style lang="css">
.mainCard {
  margin-bottom: 1rem;
  margin-right: 1rem;
}
.cardOrganization {
  display: flex;
  flex-wrap: wrap;
}
</style>
