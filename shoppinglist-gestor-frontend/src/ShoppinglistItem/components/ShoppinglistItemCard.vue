<script setup lang="ts">
import type { PropType } from 'vue'
import Card from 'primevue/card'
import Button from 'primevue/button'
import Tag from 'primevue/tag'
import { RouterLink } from 'vue-router'
import type { ShoppinglistItem } from '@/ShoppinglistItem/domain/ShoppinglistItem'
defineProps({
  shoppinglistItemList: {
    type: Object as PropType<ShoppinglistItem[]>,
    default: () => null,
  },
})
</script>
<template>
  <div v-for="item in shoppinglistItemList">
    <Card class="mainCard" style="max-width: 25rem">
      <template #title>Titulo</template>
      <template #content>
        <Tag severity="info" style="margin-right: 1rem">Cantidad {{ item.quantity }}</Tag>
        <Tag severity="primary">Precio {{ item.calculatedPrice }} €</Tag>
      </template>
      <template #footer>
        <RouterLink :to="`/product/${item.id}}`">
          <Button label="Ver" severity="help" style="width: 6.5rem"></Button>
        </RouterLink>
        <Button label="Quitar" severity="danger" style="width: 6.5rem"></Button>
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
