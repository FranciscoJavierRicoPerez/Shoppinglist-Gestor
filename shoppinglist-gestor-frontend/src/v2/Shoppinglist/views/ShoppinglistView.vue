<script setup lang="ts">
import ShoppinglistFilter from '@/v2/Shoppinglist/components/ShoppinglistFilters.vue'
import ShoppinglistTable from '@/v2/Shoppinglist/components/ShoppinglistTable.vue'
import { Divider } from 'primevue'
</script>
<template>
  <div style="display: flex">
    <ShoppinglistFilter class="filters"></ShoppinglistFilter>
    <Divider layout="vertical" />
    <ShoppinglistTable class="table"></ShoppinglistTable>
  </div>
</template>
<style lang="css">
/** Explicacion de flex:
  -> flex: grow shrink basis 
    -> grow -> puede crecer para ocupar el espacio sobrante
    -> shrink -> puede reducirse si no hay espacio suficiente
    -> basis -> tamaño base inicial
*/

.filters {
  display: flex;
  flex: 1 0 200px;
}
.table {
  display: flex;
  flex: 1 1 1000px;
}
</style>
