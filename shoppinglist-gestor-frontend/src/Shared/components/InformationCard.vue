<script setup lang="ts">
import Card from 'primevue/card'
import { computed, type PropType } from 'vue'
import type { Information } from '@/Shared/models/Information'

const props = defineProps({
  information: {
    type: Object as PropType<Information>,
  },
})

const headerText = computed(() => {
  return props.information?.header
})

const contentText = computed(() => {
  return props.information?.content
})
</script>
<template>
  <Card id="informationCard" class="flex w-full bg-red-200">
    <template #header>
      <div class="text-2xl ml-3 mt-2">{{ headerText }}</div>
    </template>
    <template #content>
      <div class="text-xl">{{ contentText }}</div>
    </template>
  </Card>
</template>
