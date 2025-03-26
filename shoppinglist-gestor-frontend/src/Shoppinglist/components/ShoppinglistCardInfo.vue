<script setup lang="ts">
import Card from 'primevue/card'
import Button from 'primevue/button'
import type { PropType } from 'vue'
import type { Shoppinglist } from '@/Shoppinglist/domain/Shoppinglist'
import { RouterLink } from 'vue-router'
import Tag from 'primevue/tag'

defineProps({
  shoppinglist: {
    type: Object as PropType<Shoppinglist>,
    default: () => null,
  },
})
</script>
<template>
  <Card class="card-general">
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
      <Button label="Archivar" severity="help" :disabled="!shoppinglist.isActive"></Button>
      <RouterLink :to="`/shoppinglist/${shoppinglist.id}`">
        <Button class="buttons-separation" label="Ver" severity="info"></Button>
      </RouterLink>
      <Button class="buttons-separation" label="Eliminar" severity="danger"></Button>
    </template>
  </Card>
</template>
<style lang="css">
.card-general {
  margin-right: 1rem;
  margin-top: 10px;
  max-width: 20rem;
  background-color: aliceblue !important;
}
.buttons-separation {
  margin-left: 1rem;
}
.tag-custom {
  font-size: large;
  font-weight: bold;
}
</style>
