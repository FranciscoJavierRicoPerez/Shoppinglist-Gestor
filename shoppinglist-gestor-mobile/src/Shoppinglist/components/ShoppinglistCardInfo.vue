<script setup lang="ts">
import type { PropType } from 'vue'
import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { RouterLink } from 'vue-router'
import { useUpdateIsActiveShoppinglist } from '@/Shoppinglist/application/useUpdateIsAct
import { useDeleteShoppinglistData } from '@/Shoppinglist/application/useDeleteShoppinglistData'
const { refetch: updateIsActive } = useUpdateIsActiveShoppinglist()
const { refetch: deleteShoppinglist } = useDeleteShoppinglistData()
const store = useShoppinglistStore()
const emit = defineEmits(['updateShoppinglistTables'])
const props = defineProps({
  shoppinglist: {
    type: Object as PropType<Shoppinglist>,
    default: () => null,
  },
})

async function archiveShoppinglist() {
  let response: boolean = await updateIsActive()
  if (response) {
    store.updateShoppinglistActive(props.shoppinglist.id)
    emit('updateShoppinglistTables')
  }
}

async function removeShoppinglist() {
  debugger
  let response: boolean = await deleteShoppinglist(props.shoppinglist.id)
  if (response) {
    store.removeShoppinglist(props.shoppinglist.id)
    emit('updateShoppinglistTables')
  }
}

</script>
<template>
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
