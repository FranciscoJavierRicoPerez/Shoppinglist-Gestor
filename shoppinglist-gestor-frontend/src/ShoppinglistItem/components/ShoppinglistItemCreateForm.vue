<script setup lang="ts">
import Panel from 'primevue/panel'
import Button from 'primevue/button'
import ShoppinglistItemCreateItemForm from './ShoppinglistItemCreateItemForm.vue'
import ShoppinglistItemCreateFormResume from './ShoppinglistItemCreateFormResume.vue'
</script>
<template>
  <Panel id="1" toggleable>
    <template #header>
      <h3>Nuevo Producto</h3>
    </template>
    <template #footer>
      <Button security="info" label="Añadir producto" class="w-full"></Button>
    </template>
    <ShoppinglistItemCreateItemForm class="mb-2 bg-green-100"></ShoppinglistItemCreateItemForm>
    <ShoppinglistItemCreateFormResume class="bg-green-100"></ShoppinglistItemCreateFormResume>
  </Panel>
</template>
<style lang="css"></style>
