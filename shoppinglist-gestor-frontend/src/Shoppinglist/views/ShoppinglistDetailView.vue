<script setup lang="ts">
import { onMounted, ref } from 'vue'
import {
  defaultShoppinglistDetails,
  type ShoppinglistDetails,
} from '@/Shoppinglist/domain/ShoppinglistDetails'
import { useGetShoppinglistDetails } from '@/Shoppinglist/application/useGetShoppinglistDetails'

const { refetch: getShoppinglistDetails } = useGetShoppinglistDetails()

const shoppinglistDetails = ref<ShoppinglistDetails>({ ...defaultShoppinglistDetails })

onMounted(async () => {
  // We have to obtain the object ShoppinglistDetails
  shoppinglistDetails.value = await getShoppinglistDetails()
})
</script>
<template>
  <p>DETALLE DE LA LISTA DE LA COMPRA</p>
</template>
