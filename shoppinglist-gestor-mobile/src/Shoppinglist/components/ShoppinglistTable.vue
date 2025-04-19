<script setup lang="ts">
import {
  InfiniteScrollCustomEvent,
  IonContent,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
  IonItem,
  IonLabel,
  IonList,
} from "@ionic/vue";
import { onMounted } from "vue";

/*import {
  defaultShoppinglist,
  type Shoppinglist,
} from "@/Shoppinglist/domain/Shoppinglist";
import { onMounted, reactive, ref } from "vue";
import { useGetAllShoppinglist } from "@/Shoppinglist/application/useGetAllShoppinglist";
import { useShoppinglistStore } from "@/Shoppinglist/stores/shoppinglistStore";
import { useCreateShoppinglistMetadata } from "@/Shoppinglist/application/useCreateShoppinglistMetadata";
import {
  InfiniteScrollCustomEvent,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
  IonItem,
  IonList,
  IonListHeader,
} from "@ionic/vue";
import ShoppinglistCardInfo from "./ShoppinglistCardInfo.vue";
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


onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist();
  store.setShoppinglistArray(shoppinglistTable.value);
  updateShoppinglistTables();
});

async function addNewShoppinglist() {
  console.log("INFO: Añadiendo una nueva lista de la compra");
  let shoppinglistMetadata: Shoppinglist = await createShoppinglistMetadata();
  if (shoppinglistMetadata) {
    store.addShoppinglist(shoppinglistMetadata);
    updateShoppinglistTables();
  }
}

function updateShoppinglistTables() {
  shoppinglistActiveTable.value = store.getActiveShoppinglist();
  shoppinglistNoActiveTable.value = store.getNoActiveShoppinglist();
}*/

import { ref } from 'vue';
import ShoppinglistCardInfo from "./ShoppinglistCardInfo.vue";
import { defaultShoppinglist, Shoppinglist } from "../domain/Shoppinglist";
import { useGetAllShoppinglist } from "../application/useGetAllShoppinglist";
import { useCreateShoppinglistMetadata } from "../application/useCreateShoppinglistMetadata";
import { useShoppinglistStore } from "../stores/shoppinglistStore";
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

// Lista auxiliar en la cual construyo dinamicamente el listado de listas de la compra que necesito que se
// vayan mostrando en la pantalla del dispositivo movil
const items = ref<Shoppinglist[]>([]);

// Construye dinamicamente el listado de las shoppinglist
function generateItems() {
  const start = items.value.length + 1;
  for (let i = 0; i < 50; i++) {
    items.value.push(store.shoppinglistArray[start + i]);
  }
};

// Evento infinito
const ionInfinite = (event: InfiniteScrollCustomEvent) => {
  generateItems();
  setTimeout(() => {
    event.target.complete();
  }, 500);
};

onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist();
  store.setShoppinglistArray(shoppinglistTable.value);
  //updateShoppinglistTables();
  generateItems()
});

</script>
<template>
  <IonContent :fullscreen="true" class="ion-padding">
    <IonList>
      <IonItem v-for="(item) in items">
        <IonLabel>
          <ShoppinglistCardInfo :shoppinglist="item"></ShoppinglistCardInfo>
        </IonLabel>
      </IonItem>
    </IonList>
    <IonInfiniteScroll @ionInfinite="ionInfinite">
      <IonInfiniteScrollContent></IonInfiniteScrollContent>
    </IonInfiniteScroll>
  </IonContent>
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
