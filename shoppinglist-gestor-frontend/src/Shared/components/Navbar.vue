<script setup lang="ts">
import { RouterLink } from 'vue-router'
import { Menubar } from 'primevue'

import { ref } from 'vue'

const items = ref([
  {
    label: 'Listas de la compra',
    icon: 'pi pi-home',
    url: '/',
  },
  {
    label: 'Información',
    icon: 'pi pi-star',
    url: '/information',
  },
])
</script>
<template>
  <div>
    <nav>
      <Menubar :model="items" class="menubar">
        <template #item="{ item }">
          <RouterLink :to="item.url"><i :class="item.icon"></i>{{ item.label }}</RouterLink>
        </template>
      </Menubar>
    </nav>
  </div>
</template>
<style lang="css">
.menubar {
  margin-bottom: 1rem;
}
</style>
