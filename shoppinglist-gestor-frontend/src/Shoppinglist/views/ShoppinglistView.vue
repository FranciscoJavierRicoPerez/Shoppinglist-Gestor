<script setup lang="ts">
import ShoppinglistTable from '@/Shoppinglist/components/ShoppinglistTable.vue'
import ShoppinglistFilter from '@/Shoppinglist/components/ShoppinglistFilter.vue'
</script>
<template>
  <div class="flex flex-row gap-2 justify-content-start flex-wrap md:flex-nowrap">
    <ShoppinglistFilter></ShoppinglistFilter>
    <ShoppinglistTable></ShoppinglistTable>
  </div>
</template>
