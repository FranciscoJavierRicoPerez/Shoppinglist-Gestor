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
      <Button security="info" label="Añadir producto" style="width: 100%"></Button>
    </template>
    <div class="d-flex flex-column justify-content-start">
      <div style="margin-bottom: 1rem">
        <ShoppinglistItemCreateItemForm></ShoppinglistItemCreateItemForm>
      </div>
      <ShoppinglistItemCreateFormResume></ShoppinglistItemCreateFormResume>
    </div>
  </Panel>
</template>
<style lang="css"></style>
