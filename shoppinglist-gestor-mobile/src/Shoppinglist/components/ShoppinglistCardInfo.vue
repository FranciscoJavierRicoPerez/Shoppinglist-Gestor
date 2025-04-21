<script setup lang="ts">
import type { PropType } from "vue";
import type { Shoppinglist } from "@/Shoppinglist/domain/Shoppinglist";
import { useUpdateIsActiveShoppinglist } from "@/Shoppinglist/application/useUpdateIsActiveShoppinglist";
import { useDeleteShoppinglistData } from "@/Shoppinglist/application/useDeleteShoppinglistData";
import {
  IonButton,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonChip,
} from "@ionic/vue";
import { useShoppinglistStore } from "@/Shoppinglist/stores/shoppinglistStore";
const { refetch: updateIsActive } = useUpdateIsActiveShoppinglist();
const { refetch: deleteShoppinglist } = useDeleteShoppinglistData();
const store = useShoppinglistStore();
const emit = defineEmits(["updateShoppinglistTables"]);
const props = defineProps({
  shoppinglist: {
    type: Object as PropType<Shoppinglist>,
    default: () => null,
  },
});

async function archiveShoppinglist() {
  let response: boolean = await updateIsActive();
  if (response) {
    store.updateShoppinglistActive(props.shoppinglist.id);
    emit("updateShoppinglistTables");
  }
}

async function removeShoppinglist() {
  console.log(
    "INFO: Borrando la lista de la compra con id: " + props.shoppinglist?.id
  );
  let response: boolean = await deleteShoppinglist(props.shoppinglist.id);
  if (response) {
    store.removeShoppinglist(props.shoppinglist.id);
    emit("updateShoppinglistTables");
  }
}
</script>
<template>
  <IonCard
    :class="
      props.shoppinglist?.isActive === false ||
      props.shoppinglist?.isActive === null
        ? 'card-background-no-actives-card'
        : 'card-background-actives-card'
    "
  >
    <IonCardHeader>
      <IonCardTitle>{{ props.shoppinglist?.code }}</IonCardTitle>
      <IonCardSubtitle>{{ props.shoppinglist?.creationDate }}</IonCardSubtitle>
    </IonCardHeader>
    <IonCardContent>
      <div>{{ props.shoppinglist?.totalPrice }}</div>
      <div v-if="props.shoppinglist?.isActive">
        <IonChip :color="'success'">Activo</IonChip>
      </div>
      <div v-else>
        <IonChip :color="'warning'">Archivado</IonChip>
      </div>
    </IonCardContent>
    <IonButton
      class="buttons-separation"
      shape="round"
      color="tertiary"
      @click="archiveShoppinglist"
      :disabled="!props.shoppinglist.isActive"
      >Archivar</IonButton
    >
    <IonButton class="buttons-separation" shape="round" color="primary"
      >Ver</IonButton
    >
    <IonButton
      class="buttons-separation"
      shape="round"
      color="danger"
      @click="removeShoppinglist()"
      >Eliminar</IonButton
    >
  </IonCard>
</template>
<style lang="css">
.card-general {
  margin-right: 1rem;
  margin-top: 10px;
  max-width: 20rem;
}

.card-background-actives-card {
  background-color: aliceblue !important;
}

.card-background-no-actives-card {
  background-color: rgb(231, 230, 233) !important;
}

.buttons-separation {
  margin-left: 1rem;
  margin-bottom: 1rem;
}
.tag-custom {
  font-size: large;
  font-weight: bold;
}
</style>
