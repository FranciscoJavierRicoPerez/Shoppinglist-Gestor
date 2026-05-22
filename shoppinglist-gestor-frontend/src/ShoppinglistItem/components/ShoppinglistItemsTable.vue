<script setup lang="ts">
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import ShoppinglistItemInfoCard from './ShoppinglistItemInfoCard.vue'
import Panel from 'primevue/panel'
import ScrollPanel from 'primevue/scrollpanel'
import InformationCard from '@/Shared/components/InformationCard.vue'
import { computed, type PropType } from 'vue'

const shoppinglistDetailsStore = useShoppinglistDetailStore()

const props = defineProps({
  isSLActive: {
    type: Boolean as PropType<boolean>,
    default: () => false,
  },
})

const listHeaderText = computed(() => {
  return 'Listado de productos'
})
</script>
<template>
  <Panel toggleable>
    <template #header>
      <div class="text-2xl italic">{{ listHeaderText }}</div>
    </template>
    <div v-if="shoppinglistDetailsStore.items.length === 0">
      <InformationCard
        :information="{
          header: 'Información',
          content:
            'Aun no has añadido nigun producto, rellena el formulario para añadir productos a la lista.',
        }"
      ></InformationCard>
    </div>
    <div v-else>
      <ScrollPanel style="width: 100%; height: 50rem">
        <div v-for="item in shoppinglistDetailsStore.items">
          <ShoppinglistItemInfoCard
            :isSLActive="isSLActive"
            class="mb-2"
            :shoppinglistItem="item"
          ></ShoppinglistItemInfoCard>
        </div>
      </ScrollPanel>
    </div>
  </Panel>
</template>
