<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar from '@/Shared/components/Navbar.vue'
</script>

<template>
  <div class="custom-header custom-text">Gestor de Listas de Compra</div>
  <header>
    <Navbar></Navbar>
  </header>
  <RouterView />
  <footer class="custom-footer custom-text">Francisco Javier Rico Pérez - 2025/2026 v0.0.1</footer>
</template>

<style scoped>
.custom-header {
  background-color: rgb(51, 255, 153);
  margin-bottom: 10px;
  height: 50px;
}
.custom-footer {
  background-color: rgb(51, 255, 153);
  margin-top: 10px;
  height: 50px;
}
.custom-text {
  text-align: center;
  font-size: xx-large;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  font-style: italic;
}
</style>
