<script setup lang="ts">
import { RouterView } from 'vue-router'
import Navbar from '@/Shared/components/Navbar.vue'
</script>

<template>
  <header>
    <Navbar></Navbar>
  </header>
  <RouterView />
</template>

<style scoped></style>
