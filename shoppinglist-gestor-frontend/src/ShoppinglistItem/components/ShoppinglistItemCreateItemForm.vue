<script setup lang="ts">
import CalculateSystemSelector from '@/CalculateSystem/components/CalculateSystemSelector.vue'
import CreateProductForm from '@/Product/components/CreateProductForm.vue'
import Card from 'primevue/card'
import { useCreateShoppinglistItemFormStore } from '../stores/createShoppinglistItemFormStore'

const createShoppinglistItemFormStore = useCreateShoppinglistItemFormStore()
</script>
<template>
  <Card>
    <template #header>
      <h5>Creacion nuevo producto</h5>
    </template>
    <template #content>
      <!-- Formulario de creacion de un SLI -->
      <div class="d-flex flex-column">
        <!-- Formulario con todos los productos introducidos alguna vez -->
        <div style="font-size: small">
          <p>
            <strong
              >Selecciona un producto del listado o introduce el nombre del producto que quieres
              añadir</strong
            >
          </p>
        </div>
        <CreateProductForm></CreateProductForm>
        <!-- Selector con los sistemas de calculo -->
        <div style="font-size: small">
          <p>
            <strong>Selecciona el sistema para calcular el precio</strong>
          </p>
        </div>
        <CalculateSystemSelector></CalculateSystemSelector>
        <!-- Formulario de valores para el calculo del precio en funcion del sistema de calculo -->
        <div v-if="createShoppinglistItemFormStore.selectedCalculateSystem !== -1">
          <div v-if="createShoppinglistItemFormStore.selectedCalculateSystem === 1">
            <p>SE PROCEDE UTILIZAR EL UP TIPO</p>
          </div>
          <div v-else>
            <p>SE PROCEDE A UTILIZAR EL WP TIPO</p>
          </div>
        </div>
      </div>
    </template>
  </Card>
</template>
<style lang="css"></style>
