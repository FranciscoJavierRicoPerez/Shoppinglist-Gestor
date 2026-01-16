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
      <!-- <template #footer>
        <div class="d-flex flex-row justify-content-center flex-wrap">
          <Button style="margin-right: 1rem">Archivar</Button>
          <Button style="margin-right: 1rem">Borrar</Button>
          <Button>Exportar Informacion</Button>
        </div> 
      </template> -->
      <div class="d-flex flex-column justify-content-start flex-wrap">
        <div class="d-flex flex-row justify-content-center flex-wrap" style="margin-bottom: 1rem">
          <Tag style="min-width: 5rem; margin-right: 1rem">
            <div v-if="shoppinglistMetadata?.isActive">
              <strong>ABIERTA</strong>
            </div>
            <div v-else>
              <strong>CERRADA</strong>
            </div>
          </Tag>
          <Tag severity="info" style="min-width: 13rem">{{ shoppinglistMetadata?.code }}</Tag>
        </div>
        <Tag style="margin-bottom: 1rem" severity="danger"
          >Precio total {{ shoppinglistMetadata?.totalPrice }} €</Tag
        >
        <Tag
          style="margin-bottom: 1rem"
          severity="warn"
          :class="{ multiDate: shoppinglistMetadata?.closeDate !== null }"
        >
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
