<script setup lang="ts">
import CalculateSystemSelector from '@/CalculateSystem/components/CalculateSystemSelector.vue'
import CreateProductForm from '@/Product/components/CreateProductForm.vue'
import Card from 'primevue/card'
import { useCreateShoppinglistItemFormStore } from '../stores/createShoppinglistItemFormStore'
import ItemUnitUpCreateForm from '@/ItemUnit/components/ItemUnitUpCreateForm.vue'
import ItemUnitWpCreateForm from '@/ItemUnit/components/ItemUnitWpCreateForm.vue'

const createShoppinglistItemFormStore = useCreateShoppinglistItemFormStore()
</script>
<template>
  <Card>
    <template #header>
      <div class="text-2xl font-italic ml-3 mt-2">Información nuevo producto</div>
    </template>
    <template #content>
      <!-- Formulario de creacion de un SLI -->
      <div class="flex flex-column justify-content-center">
        <!-- Formulario con todos los productos introducidos alguna vez -->
        <div class="text-sm font-semibold mb-2">
          Selecciona un producto del listado o introduce el nombre del producto que quieres añadir
        </div>
        <CreateProductForm></CreateProductForm>
        <!-- Selector con los sistemas de calculo -->
        <div class="text-sm font-semibold mb-2">Selecciona el sistema para calcular el precio</div>
        <CalculateSystemSelector class="mb-2"></CalculateSystemSelector>
        <!-- Formulario de valores para el calculo del precio en funcion del sistema de calculo -->
        <div v-if="createShoppinglistItemFormStore.selectedCalculateSystem !== -1">
          <div class="text-sm font-semibold mb-2">Introduce los datos</div>
          <div v-if="createShoppinglistItemFormStore.selectedCalculateSystem === 1">
            <ItemUnitUpCreateForm></ItemUnitUpCreateForm>
          </div>
          <div v-else>
            <ItemUnitWpCreateForm></ItemUnitWpCreateForm>
          </div>
        </div>
      </div>
    </template>
  </Card>
</template>
<style lang="css"></style>
