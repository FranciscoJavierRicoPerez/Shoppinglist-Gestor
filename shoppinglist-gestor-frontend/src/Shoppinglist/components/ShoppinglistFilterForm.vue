<script lang="ts">
import DatePicker from 'primevue/datepicker'
import { ref } from 'vue'

const date = ref()
</script>
<template>
  <DatePicker v-model="date"></DatePicker>
</template>
