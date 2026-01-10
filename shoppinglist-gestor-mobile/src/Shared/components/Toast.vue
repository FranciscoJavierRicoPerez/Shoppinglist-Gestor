<script setup lang="ts">
import { IonToast } from "@ionic/vue";
import { useToastStore } from "@/Shared/stores/toastStore";

const toastStore = useToastStore();

function handleDismiss() {
  toastStore.reset();
}
</script>
<template>
  <IonToast
    :is-open="toastStore.toastData?.open"
    :duration="1000"
    :color="toastStore.toastData?.isInformation ? 'primary' : 'danger'"
    :message="toastStore.toastData?.message"
    @did-dismiss="handleDismiss()"
  ></IonToast>
</template>
<style lang="css"></style>
