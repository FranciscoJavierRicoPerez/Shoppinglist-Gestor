<script setup lang="ts">
import Select from 'primevue/select'
import { onMounted, ref } from 'vue'
import { useGetAllCalculateSystems } from '../application/useGetAllCalculateSystems'
import type { CalculateSystem } from '../domain/CalculateSystem'
import { useCreateShoppinglistItemFormStore } from '@/ShoppinglistItem/stores/createShoppinglistItemFormStore'

const calculateSystemOptions = ref<CalculateSystem[]>([])

const { refetch: getAllCalculateSystems } = useGetAllCalculateSystems()

const createShoppinglistItemFormStore = useCreateShoppinglistItemFormStore()

onMounted(async () => {
  calculateSystemOptions.value = await getAllCalculateSystems()
})
</script>
<template>
  <Select
    style="min-width: 100%"
    v-model="createShoppinglistItemFormStore.selectedCalculateSystem"
    :options="calculateSystemOptions"
    optionLabel="name"
    placeholder="Selecciona un sistema de calculo"
  ></Select>
</template>
