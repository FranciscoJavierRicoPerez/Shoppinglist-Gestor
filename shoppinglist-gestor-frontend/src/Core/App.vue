<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar from '@/Shared/components/Navbar.vue'
import { computed, onErrorCaptured } from 'vue'
import { useToast } from 'primevue'

const toast = useToast()

const actualAppVersion = computed(() => {
  return import.meta.env.ACTUAL_APP_VERSION
})
const copyright = computed(() => {
  return 'GestCompras - Francisco Javier Rico Pérez'
})

const appName = computed(() => {
  return 'GestCompras'
})

onErrorCaptured((error) => {
  console.log(error.message)
  toast.add({
    summary: 'Found Error',
    detail: error.message,
    life: 3000,
    severity: 'error',
  })
})
</script>

<template>
  <div class="flex flex-column justify-content-between">
    <div class="bg-green-400 w-full p-3">
      <div class="flex text-6xl font-italic justify-content-center">{{ appName }}</div>
    </div>
    <header class="p-3">
      <Navbar></Navbar>
    </header>
    <div class="grow-0 mb-6 p-3">
      <RouterView />
    </div>

    <footer class="text-3xl bg-green-400 mt-9 w-full p-3">
      <div class="flex text-3xl font-italic justify-content-center">
        {{ copyright }}{{ actualAppVersion }}
      </div>
    </footer>
  </div>
</template>
