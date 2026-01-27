<script setup lang="ts">
import Dialog from 'primevue/dialog'
import { computed, onMounted, ref, type PropType } from 'vue'
import Button from 'primevue/button'
import Divider from 'primevue/divider'
import ItemUnitWpUpdateForm from './ItemUnitWpUpdateForm.vue'
import ItemUnitWpResume from './ItemUnitWpResume.vue'
import { useUpdateItemWpFormStore } from '@/ItemUnit/store/updateItemWpFormStore'
const visible = ref<boolean>(false)

// /v1/{idShoppinglistItem}/itemsUnitsWpInfo -> LLAMAR A ESTO EN EL mounted()
// crear un wpItemDetailsStore el cual me sirva para mostrar y actualizar la informacion del modal
const store = useUpdateItemWpFormStore()
// /v1/{idShoppinglistItem}/addItemUnitWP -> ENDPOINT PARA LA ACTUALIZACION DEL ITEM WP

const props = defineProps({
  idShoppinglistItem: {
    type: Number as PropType<number>,
  },
})

const emit = defineEmits(['newSliCalculatedPrice'])

const modalHeaderText = computed(() => {
  return 'Detalle del producto'
})

function updateShoppinglistPrice() {
  console.log('ACTUALIZANDO PRECIOS')
  /**
   * AQUI TENGO QUE ACTUALIZAR 2 VALORES.
   * 1 - EL VALOR DEL SLI TIENE QUE CAMBIAR POR EL NUEVO
   *   => ENVIAR A LA CAPA SUPERIOR (ShoppinglistItemInfoCard.vue calculatedPrice) UN EVENTO CON EL NUEVO VALOR
   *   Y ASIGNARLO A LA VARIABLE
   * 2 - EL VALOR DE LA LISTA DE LA COMPRA
   */
  // PASO - 1
  emit('newSliCalculatedPrice', store.newProductPrice)
}
</script>
<template>
  <Button class="w-full" severity="info" label="Kg/€" raised @click="visible = true"></Button>
  <Dialog class="" v-model:visible="visible" :position="'top'" :modal="true" :draggable="false">
    <template #header>
      <div class="ml-3 mt-2 text-2xl text-left font-italic">{{ modalHeaderText }}</div>
    </template>
    <Divider align="center" type="solid">
      <b>Información del Producto</b>
    </Divider>
    <ItemUnitWpResume
      :isUpdateInfo="false"
      :idShoppinglistItem="props.idShoppinglistItem"
    ></ItemUnitWpResume>
    <Divider align="center" type="solid">
      <b>Actualizar Producto</b>
    </Divider>
    <ItemUnitWpUpdateForm></ItemUnitWpUpdateForm>
    <Divider align="center" type="solid">
      <b>Producto Actualizado</b>
    </Divider>
    <ItemUnitWpResume :isUpdateInfo="true"></ItemUnitWpResume>
    <Divider> </Divider>

    <div class="flex justify-content-end gap-2">
      <Button
        class="w-full"
        type="button"
        label="Cancel"
        severity="danger"
        @click="visible = false"
      ></Button>
      <Button
        class="w-full"
        severity="info"
        type="button"
        label="Actualizar"
        @click="((visible = false), updateShoppinglistPrice())"
      ></Button>
    </div>
  </Dialog>
</template>
