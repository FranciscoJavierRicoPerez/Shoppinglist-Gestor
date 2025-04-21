<script setup lang="ts">
import {
  InfiniteScrollCustomEvent,
  IonContent,
  IonFab,
  IonFabButton,
  IonIcon,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
  IonItem,
  IonLabel,
  IonList,
  IonPage,
} from "@ionic/vue";
import { onMounted } from "vue";
import { ref } from "vue";
import ShoppinglistCardInfo from "./ShoppinglistCardInfo.vue";
import { defaultShoppinglist, Shoppinglist } from "../domain/Shoppinglist";
import { useGetAllShoppinglist } from "../application/useGetAllShoppinglist";
import { useCreateShoppinglistMetadata } from "../application/useCreateShoppinglistMetadata";
import { useShoppinglistStore } from "../stores/shoppinglistStore";
import Header from "@/Shared/components/Header.vue";
import Footer from "@/Shared/components/Footer.vue";
const { refetch: getAllShoppinglist } = useGetAllShoppinglist();
const shoppinglistTable = ref<Shoppinglist[]>([{ ...defaultShoppinglist }]);
const shoppinglistActiveTable = ref<Shoppinglist[]>([
  { ...defaultShoppinglist },
]);
const shoppinglistNoActiveTable = ref<Shoppinglist[]>([
  { ...defaultShoppinglist },
]);
const { refetch: createShoppinglistMetadata } = useCreateShoppinglistMetadata();
const store = useShoppinglistStore();

// Este listado representa los elementos que son visibles actualmente en la pantalla
const actualShoppinglistVisible = ref<Shoppinglist[]>([]);

// Este metodo realiza el calculo de los elementos que van a ser visible, va de 50 en 50
function updateShoppinglistElementsVisible(removedObject: boolean) {
  if (removedObject) {
    // Si se va a borrar un elemento, necesito volver a cargar la lista entera
    // para que asi la lista de actuales visibles corresponda
    actualShoppinglistVisible.value = [];
  }
  const start = actualShoppinglistVisible.value.length + 1;
  for (let i = 0; i < 50; i++) {
    // ShoppinglistTable.vue (LINEA 105) => Revisar si hay alguna forma de obviar el !== undefined
    if (store.shoppinglistArray[start + i] !== undefined) {
      actualShoppinglistVisible.value.push(store.shoppinglistArray[start + i]);
    }
  }
}

// Evento infinito
const ionInfinite = (event: InfiniteScrollCustomEvent) => {
  updateShoppinglistElementsVisible(false);
  setTimeout(() => {
    event.target.complete();
  }, 500);
};

onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist();
  store.setShoppinglistArray(shoppinglistTable.value);
  updateShoppinglistTables(false);
});

async function addNewShoppinglist() {
  console.log("INFO: Añadiendo una nueva lista de la compra");
  let shoppinglistMetadata: Shoppinglist = await createShoppinglistMetadata();
  console.log(shoppinglistMetadata);
  if (shoppinglistMetadata) {
    store.addShoppinglist(shoppinglistMetadata);
    updateShoppinglistTables(false);
  }
}

function updateShoppinglistTables(removedObject: boolean = true) {
  console.log("INFO: Actualizando las listas de la compra");
  shoppinglistActiveTable.value = store.getActiveShoppinglist();
  shoppinglistNoActiveTable.value = store.getNoActiveShoppinglist();
  updateShoppinglistElementsVisible(removedObject);
}
</script>
<template>
  <IonPage>
    <Header :title="'Listas de la compra'"></Header>
    <Footer></Footer>
    <IonContent>
      <IonFab horizontal="end" vertical="bottom" slot="fixed">
        <IonFabButton @click="addNewShoppinglist">
          <IonIcon name="add-outline" />
        </IonFabButton>
      </IonFab>
      <IonList>
        <IonItem v-for="shoppinglist in actualShoppinglistVisible">
          <IonLabel>
            <ShoppinglistCardInfo
              :shoppinglist="shoppinglist"
              @updateShoppinglistTables="updateShoppinglistTables"
            ></ShoppinglistCardInfo>
          </IonLabel>
        </IonItem>
      </IonList>
      <IonInfiniteScroll @ionInfinite="ionInfinite">
        <IonInfiniteScrollContent></IonInfiniteScrollContent>
      </IonInfiniteScroll>
    </IonContent>
  </IonPage>
</template>
<style>
.panelHeader {
  font-size: xx-large;
  font-weight: bold;
}
.panelShoppinglistTable {
  margin-top: 1rem;
}
.cardOrganization {
  display: flex;
  flex-wrap: wrap;
}
</style>
