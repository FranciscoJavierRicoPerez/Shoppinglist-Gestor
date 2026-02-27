<script setup lang="ts">
import Card from 'primevue/card'
import Button from 'primevue/button'
import { computed, type PropType } from 'vue'
import { RouterLink } from 'vue-router'
import Tag from 'primevue/tag'
import { useUpdateIsActiveShoppinglist } from '@/Shoppinglist/application/useUpdateIsActiveShoppinglist'
import { type ToastMessageOptions } from 'primevue/toast'
import { useToast } from 'primevue/usetoast'
import { useDeleteShoppinglistData } from '@/Shoppinglist/application/useDeleteShoppinglistData'
import type { ShoppinglistMetadata } from '@/Shoppinglist/domain/ShoppinglistMetadata'
import { useShoppinglistTableStore } from '@/Shoppinglist/stores/shoppinglistTableStore'
const { refetch: updateIsActive } = useUpdateIsActiveShoppinglist()
const { refetch: deleteShoppinglist } = useDeleteShoppinglistData()
const shoppinglistTableStore = useShoppinglistTableStore()
const emit = defineEmits(['updateShoppinglistTables'])
const props = defineProps({
  shoppinglist: {
    type: Object as PropType<ShoppinglistMetadata>,
    default: () => null,
  },
})
const toast = useToast()

const slCode = computed(() => {
  return props.shoppinglist.code
})

const slCreationDate = computed(() => {
  return props.shoppinglist.creationDate
})

const slTotalPrice = computed(() => {
  return props.shoppinglist.totalPrice + '€'
})

const slIsActive = computed(() => {
  return props.shoppinglist.isActive
})

async function archiveShoppinglist() {
  let response: boolean = await updateIsActive(props.shoppinglist.idShoppinglist)
  if (response) {
    shoppinglistTableStore.updateShoppinglistActive(props.shoppinglist.idShoppinglist)
    emit('updateShoppinglistTables')
  }
}

async function removeShoppinglist() {
  let response: boolean = await deleteShoppinglist(props.shoppinglist.idShoppinglist)
  if (response) {
    shoppinglistTableStore.removeShoppinglist(props.shoppinglist.idShoppinglist)
    emit('updateShoppinglistTables')
    createToast({
      severity: 'success',
      summary: 'Borrada lista de la compra',
      detail: 'Codigo de la lista de la compra borrada: ' + props.shoppinglist.code,
      life: 3000,
    })
  } else {
    createToast({
      severity: 'error',
      summary: 'Error borrando la lista de la compra',
      detail:
        'Se ha producido un error en el borrado de la lista de la compra: ' +
        props.shoppinglist.code,
      life: 3000,
    })
  }
}

function createToast(toastOptions: ToastMessageOptions) {
  toast.add({
    severity: toastOptions.severity,
    summary: toastOptions.summary,
    detail: toastOptions.detail,
    life: toastOptions.life,
  })
}
</script>
<template>
  <Card
    class="card-general"
    :class="slIsActive ? 'card-background-actives-card' : 'card-background-no-actives-card'"
  >
    <template #title>{{ slCode }}</template>
    <template #subtitle>{{ slCreationDate }}</template>
    <template #content>
      <p>{{ slTotalPrice }}</p>
      <div v-if="slIsActive">
        <Tag severity="success">
          <span class="tag-custom">Activo</span>
        </Tag>
      </div>
      <div v-else>
        <Tag severity="warn">
          <span class="tag-custom">Archivado</span>
        </Tag>
      </div>
    </template>
    <template #footer>
      <div class="d-flex flex-row justify-content-start flex-wrap">
        <Button
          class="buttons-separation"
          label="Archivar"
          severity="help"
          :disabled="!slIsActive"
          @click="archiveShoppinglist()"
        ></Button>
        <RouterLink :to="`/shoppinglist/${shoppinglist.idShoppinglist}`">
          <Button class="buttons-separation" label="Ver" severity="info"></Button>
        </RouterLink>
        <Button
          class="buttons-separation"
          label="Eliminar"
          severity="danger"
          @click="removeShoppinglist()"
        ></Button>
      </div>
    </template>
  </Card>
</template>
<style lang="css">
.card-general {
  margin-right: 1rem;
  margin-top: 10px;
}

.card-background-actives-card {
  background-color: aliceblue !important;
}

.card-background-no-actives-card {
  background-color: rgb(231, 230, 233) !important;
}

.buttons-separation {
  margin-top: 6px;
  margin-right: 1rem;
}
.tag-custom {
  font-size: large;
  font-weight: bold;
}
</style>
