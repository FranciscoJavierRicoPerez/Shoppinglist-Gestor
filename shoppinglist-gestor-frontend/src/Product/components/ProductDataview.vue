<script setup lang="ts">
import type { PropType } from 'vue'
import type { Product } from '@/Product/domain/Product'
import Card from 'primevue/card'
import Button from 'primevue/button'
defineProps({
  productList: {
    type: Object as PropType<Product[]>,
    default: () => null,
  },
})
</script>
<template>
  <div v-for="product in productList">
    <Card class="mainCard">
      <template #title>{{ product.name }}</template>
      <template #subtitle> Lorem ipsum </template>
      <template #content>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
        labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
        laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
        voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
        cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      </template>
      <template #footer>
        <Button severity="danger" label="Lorem ipsum"></Button>
      </template>
    </Card>
  </div>
</template>
<style lang="css">
.mainCard {
  margin-bottom: 1rem;
}
</style>
