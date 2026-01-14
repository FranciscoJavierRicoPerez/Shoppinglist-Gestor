<script setup lang="ts">
import ShoppinglistTable from '@/Shoppinglist/components/ShoppinglistTable.vue'
import ShoppinglistFilter from '@/Shoppinglist/components/ShoppinglistFilter.vue'
import Splitter from 'primevue/splitter'
import SplitterPanel from 'primevue/splitterpanel'
</script>
<template>
  <!-- <div class="d-flex flex-row justify-content-sm-start">
    <div class="d-flex flex-column" style="margin-right: 50px">
      <ShoppinglistFilter></ShoppinglistFilter>
    </div>
    <div class="d-flex flex-column">
      <ShoppinglistTable></ShoppinglistTable>
    </div>
  </div> -->
  <div class="d-flex flex-column">
    <ShoppinglistFilter></ShoppinglistFilter>
    <ShoppinglistTable></ShoppinglistTable>
  </div>
</template>
