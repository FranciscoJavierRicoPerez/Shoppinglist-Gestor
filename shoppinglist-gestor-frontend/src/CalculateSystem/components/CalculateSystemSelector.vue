<script setup lang="ts">
import Select from 'primevue/select'
import { onMounted, ref, watch } from 'vue'
import { useGetAllCalculateSystems } from '../application/useGetAllCalculateSystems'
import { defaultCalculateSystem, type CalculateSystem } from '../domain/CalculateSystem'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'

const calculateSystemOptions = ref<CalculateSystem[]>([])

const { refetch: getAllCalculateSystems } = useGetAllCalculateSystems()

const createShoppinglistItemFormStore = useCreateShoppinglistItemFormStore()

const calculateSystemSelected = ref<CalculateSystem>({ ...defaultCalculateSystem })
onMounted(async () => {
  // Esta es una asignacion forzosa para asegurar que el selectedCalculateSystem no tiene ningun valor a inicializar el componente
  // ya que sin esta linea se asignaba por defecto el valor 2, REVISAR EN EL FUTURO!!
  createShoppinglistItemFormStore.selectedCalculateSystem = -1
  calculateSystemOptions.value = await getAllCalculateSystems()
})
watch(calculateSystemSelected, (newSelectedValue) => {
  createShoppinglistItemFormStore.selectedCalculateSystem = newSelectedValue?.id
})
</script>
<template>
  <Select
    v-model="calculateSystemSelected"
    :options="calculateSystemOptions"
    optionLabel="name"
    placeholder="Selecciona un sistema de calculo"
  ></Select>
</template>
