<script setup lang="ts">
import Tag from 'primevue/tag'
import { onMounted, ref, type PropType } from 'vue'
import type { ItemUnitWpMetadata } from '../domain/ItemUnitWpMetadata'
import { useGetItemUnitWpMetadata } from '../application/useGetItemUnitWpMetadata'
import { useUpdateItemWpFormStore } from '@/ItemUnit/store/updateItemWpFormStore'

const itemUnitWpMetadata = ref<ItemUnitWpMetadata>()
const { refetch: getItemUnitWpMetadata } = useGetItemUnitWpMetadata()
const store = useUpdateItemWpFormStore()

const props = defineProps({
  isUpdateInfo: {
    type: Boolean as PropType<boolean>,
  },
  idShoppinglistItem: {
    type: Number as PropType<number>,
  },
})

onMounted(async () => {
  if (props.idShoppinglistItem)
    itemUnitWpMetadata.value = await getItemUnitWpMetadata(props.idShoppinglistItem)
})
</script>
<template>
  <div v-if="props.isUpdateInfo">
    <div class="flex flex-column justify-content-start gap-2">
      <div class="flex flex-row gap-2">
        <Tag class="w-full">Nuevo Precio Kg/€: {{ store.newPriceKg }}€</Tag>
        <Tag class="w-full">Nuevo Peso: {{ store.newWeight }} Kg</Tag>
      </div>
      <div class="flex flex-row gap-2">
        <Tag class="w-full">Nuevo Precio del producto: {{ store.newProductPrice }}</Tag>
        <!-- <Tag class="w-full"
          >Nuevo Coste de lista de la compra: {{ store.newShoppinglistPrice }}</Tag
        > -->
      </div>
    </div>
  </div>
  <div v-else>
    <div class="flex flex-row gap-2 justify-content-center">
      <Tag>{{ itemUnitWpMetadata?.priceKg }} Kg/€</Tag>
      <Tag>{{ itemUnitWpMetadata?.weight }} Kg</Tag>
      <Tag>Total {{ itemUnitWpMetadata?.calculatedPrice }} €</Tag>
    </div>
  </div>
</template>
