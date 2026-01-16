<script setup lang="ts">
import CalculateSystemSelector from '@/CalculateSystem/components/CalculateSystemSelector.vue'
import CreateProductForm from '@/Product/components/CreateProductForm.vue'
import Card from 'primevue/card'
import { useShoppinglistItemStore } from '@/ShoppinglistItem/stores/shoppinlistItemStore'
import type { RequestCreateShoppinglistItemForm } from '../infrastructure/models/request/RequestCreateShoppinglistItemForm'
import { ref } from 'vue'

const shoppinglistItemStore = useShoppinglistItemStore()

// const createShoppinglistItemForm = ref<RequestCreateShoppinglistItemForm>()
</script>
<template>
  <Card>
    <template #header>
      <h5>Creacion nuevo producto</h5>
    </template>
    <template #content>
      <!-- Formulario de creacion de un SLI -->
      <!-- Formulario con todos los productos introducidos alguna vez -->
      <CreateProductForm></CreateProductForm>
      <!-- Selector con los sistemas de calculo -->
      <CalculateSystemSelector></CalculateSystemSelector>
      <!-- Formulario de valores para el calculo del precio en funcion del sistema de calculo -->
    </template>
  </Card>
</template>
<style lang="css"></style>
