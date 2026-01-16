<script setup lang="ts">
import ShoppinglistTable from '@/Shoppinglist/components/ShoppinglistTable.vue'
import ShoppinglistFilter from '@/Shoppinglist/components/ShoppinglistFilter.vue'
</script>
<template>
  <div class="d-flex flex-column">
    <ShoppinglistFilter></ShoppinglistFilter>
    <ShoppinglistTable></ShoppinglistTable>
  </div>
</template>
