<script setup lang="ts">
import Card from 'primevue/card'
import Button from 'primevue/button'
import type { PropType } from 'vue'
import { RouterLink } from 'vue-router'
import Tag from 'primevue/tag'
import { useUpdateIsActiveShoppinglist } from '@/v2/Shoppinglist/application/useUpdateIsActiveShoppinglist'
import { useShoppinglistStore } from '@/v2/Shoppinglist/stores/shoppinglistStore'
import Toast, { type ToastMessageOptions } from 'primevue/toast'
import { useToast } from 'primevue/usetoast'
import { useDeleteShoppinglistData } from '@/v2/Shoppinglist/application/useDeleteShoppinglistData'
import type { ShoppinglistMetadata } from '@/v2/Shoppinglist/domain/ShoppinglistMetadata'
const { refetch: updateIsActive } = useUpdateIsActiveShoppinglist()
const { refetch: deleteShoppinglist } = useDeleteShoppinglistData()
const store = useShoppinglistStore()
const emit = defineEmits(['updateShoppinglistTables'])
const props = defineProps({
  shoppinglist: {
    type: Object as PropType<ShoppinglistMetadata>,
    default: () => null,
  },
})
const toast = useToast()

async function archiveShoppinglist() {
  let response: boolean = await updateIsActive(props.shoppinglist.idShoppinglist)
  if (response) {
    store.updateShoppinglistActive(props.shoppinglist.idShoppinglist)
    emit('updateShoppinglistTables')
  }
}

async function removeShoppinglist() {
  let response: boolean = await deleteShoppinglist(props.shoppinglist.idShoppinglist)
  if (response) {
    store.removeShoppinglist(props.shoppinglist.idShoppinglist)
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
    :class="
      shoppinglist.isActive ? 'card-background-actives-card' : 'card-background-no-actives-card'
    "
  >
    <template #title>{{ shoppinglist.code }}</template>
    <template #subtitle>{{ shoppinglist.creationDate }}</template>
    <template #content>
      <p>{{ shoppinglist.totalPrice }}</p>
      <div v-if="shoppinglist.isActive">
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
      <Button
        label="Archivar"
        severity="help"
        :disabled="!shoppinglist.isActive"
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
    </template>
  </Card>
</template>
<style lang="css">
.card-general {
  margin-right: 1rem;
  margin-top: 10px;
  max-width: 20rem;
}

.card-background-actives-card {
  background-color: aliceblue !important;
}

.card-background-no-actives-card {
  background-color: rgb(231, 230, 233) !important;
}

.buttons-separation {
  margin-left: 1rem;
}
.tag-custom {
  font-size: large;
  font-weight: bold;
}
</style>
