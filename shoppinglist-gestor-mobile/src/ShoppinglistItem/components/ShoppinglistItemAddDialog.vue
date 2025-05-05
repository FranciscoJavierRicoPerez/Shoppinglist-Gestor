<script setup lang="ts">
import { IonButton, IonButtons, IonContent, IonHeader, IonModal, IonTitle, IonToolbar } from '@ionic/vue';

defineProps({
  openModal: {
    type: Boolean,
  },
});

const emit = defineEmits(["updateModalOpenValue"])

function closeModal() {
    emit("updateModalOpenValue")
}

</script>
<template>
    <!-- <div v-if="openModal">
        <p>MODAL ABIERTO</p>
    </div> -->
    <IonModal :is-open="openModal">
        <IonHeader>
            <IonToolbar>
                <IonTitle>Añadir Item</IonTitle>
                <IonButtons slot="end">
                    <IonButton @click="closeModal()">Close</IonButton>
                </IonButtons>
            </IonToolbar>
        </IonHeader>
        <IonContent>
            <p>Body del modal</p>
        </IonContent>
    </IonModal>
</template>
<style lang="css"></style>
