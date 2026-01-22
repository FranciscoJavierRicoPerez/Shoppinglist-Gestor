<script setup lang="ts">
import Dialog from 'primevue/dialog'
import { ref } from 'vue'
import Button from 'primevue/button'

const visible = ref<boolean>(false)
</script>
<template>
  <Button class="w-full" severity="info" label="Uds/€" raised @click="visible = true"></Button>
  <Dialog v-model:visible="visible">
    <template #header>PRUEBA UP</template>
    <p>ESTO ES UNA PRUEBA PARA UP</p>
    <div class="flex justify-end gap-2">
      <Button type="button" label="Cancel" severity="secondary" @click="visible = false"></Button>
      <Button type="button" label="Save" @click="visible = false"></Button>
    </div>
  </Dialog>
</template>
