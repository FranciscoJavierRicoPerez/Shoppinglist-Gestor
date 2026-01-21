<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar from '@/Shared/components/Navbar.vue'
</script>

<template>
  <div class="flex flex-column justify-content-between">
    <div class="bg-green-400 w-full p-3">
      <div class="flex text-6xl font-italic justify-content-center">GestCompras</div>
    </div>
    <header class="p-3">
      <Navbar></Navbar>
    </header>
    <div class="grow-0 mb-6 p-3">
      <RouterView />
    </div>

    <footer class="text-3xl bg-green-400 mt-9 w-full p-3">
      <div class="flex text-3xl font-italic justify-content-center">
        Francisco Javier Rico Pérez - 2026 v0.0.1
      </div>
    </footer>
  </div>
</template>
