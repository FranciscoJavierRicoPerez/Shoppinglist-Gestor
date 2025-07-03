<script setup lang="ts">
import { useGetAllItemUnitFromShoppinglistItem } from "@/ItemUnit/application/useGetAllItemUnitFromShoppinglistItem";
import { useRemoveItemUnit } from "@/ItemUnit/application/useRemoveItemUnit";
import { ItemUnit } from "@/ItemUnit/domain/ItemUnit";
import {
  IonButton,
  IonContent,
  IonItem,
  IonList,
  IonPopover,
} from "@ionic/vue";
import { ref } from "vue";

const itemsUnits = ref<ItemUnit[]>([]);

const { refetch: getAllItemUnitFromShoppinglistItem } =
  useGetAllItemUnitFromShoppinglistItem();
const { refetch: removeItemUnit } = useRemoveItemUnit();

const params = defineProps({
  idShoppinglistItem: {
    type: Number,
  },
});

async function getAllItemUnits() {
  if (params.idShoppinglistItem) {
    let response = await getAllItemUnitFromShoppinglistItem(
      params.idShoppinglistItem
    );
    itemsUnits.value = response.itemUnitList;
  }
}

async function deleteItemUnit(idItemUnit: number) {
  if (params.idShoppinglistItem) {
    await removeItemUnit(idItemUnit, params.idShoppinglistItem);
  }
}
</script>
<template>
  <IonButton
    @click="getAllItemUnits"
    color="warning"
    :id="'click-trigger-remove-item-' + params.idShoppinglistItem"
    >Borrar unidad</IonButton
  >
  <IonPopover
    :trigger="'click-trigger-remove-item-' + params.idShoppinglistItem"
    trigger-action="click"
  >
    <IonContent class="ion-padding">
      <IonList>
        <IonItem v-for="itemUnit in itemsUnits">
          <p>precio unitario: {{ itemUnit.unitPrice }}</p>
          <IonButton color="danger" @click="deleteItemUnit(itemUnit.id)"
            >Borrar</IonButton
          >
        </IonItem>
      </IonList>
    </IonContent>
  </IonPopover>
</template>
<style lang="css"></style>
