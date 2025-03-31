<script setup lang="ts">
import { ref } from 'vue'
import Menubar from 'primevue/menubar'
import { RouterLink } from 'vue-router'

const items = ref([
  {
    label: 'Listas de la compra',
    icon: 'pi pi-home',
    to: '/',
  },
  {
    label: 'Información',
    icon: 'pi pi-star',
    to: '/information',
  },
])
</script>

<template>
  <nav>
    <Menubar :model="items" class="menubar">
      <template #item="{ item, props }">
        <RouterLink v-if="item.to" v-bind="props" :to="item.to" class="menu-item">
          <i :class="item.icon"></i>
          <span>{{ item.label }}</span>
        </RouterLink>
      </template>
    </Menubar>
  </nav>
</template>

<style scoped>
.menubar {
  margin-bottom: 1rem;
}

.menu-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  text-decoration: none;
  color: inherit;
}
</style>
