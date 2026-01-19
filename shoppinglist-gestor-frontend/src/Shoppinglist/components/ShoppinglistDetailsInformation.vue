<script setup lang="ts">
import Panel from 'primevue/panel'
import Tag from 'primevue/tag'
// import Button from 'primevue/button'
import { useShoppinglistDetailStore } from '@/Shoppinglist/stores/shoppinglistDetailStore'
import type { ShoppinglistMetadata } from '@/Shoppinglist/domain/ShoppinglistMetadata'
import { onMounted, ref } from 'vue'

const shoppinglistDetailsStore = useShoppinglistDetailStore()
const shoppinglistMetadata = ref<ShoppinglistMetadata>()
onMounted(() => {
  shoppinglistMetadata.value = shoppinglistDetailsStore.shoppinglistDetails?.shoppinglistMetadata
})
</script>
<template>
  <div>
    <Panel toggleable>
      <template #header>
        <h3>Información</h3>
      </template>
      <div class="flex flex-column flex-wrap gap-2">
        <div class="flex flex-row gap-2 justify-content-between">
          <Tag>
            <div v-if="shoppinglistMetadata?.isActive">
              <strong>ABIERTA</strong>
            </div>
            <div v-else>
              <strong>CERRADA</strong>
            </div>
          </Tag>
          <Tag severity="info">{{ shoppinglistMetadata?.code }}</Tag>
        </div>
        <Tag severity="danger">Precio total {{ shoppinglistMetadata?.totalPrice }} €</Tag>
        <Tag severity="warn" :class="{ multiDate: shoppinglistMetadata?.closeDate !== null }">
          Lista de la compra del {{ shoppinglistMetadata?.creationDate }}
          <div v-if="shoppinglistMetadata?.closeDate !== null">
            <strong> al {{ shoppinglistMetadata?.closeDate }}</strong>
          </div>
        </Tag>
      </div>
    </Panel>
  </div>
</template>
<style lang="css"></style>
