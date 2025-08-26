<script setup lang="ts">
import {
  IonCard,
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
  IonSegment,
  IonSegmentButton,
  IonSegmentContent,
  IonSegmentView,
  SegmentChangeEventDetail,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
} from "@ionic/vue";
import { onMounted, watch } from "vue";
import { ref } from "vue";
import ShoppinglistCardInfo from "./ShoppinglistCardInfo.vue";
import { defaultShoppinglist, Shoppinglist } from "@/Shoppinglist/domain/Shoppinglist";
import { useGetAllShoppinglist } from "@/Shoppinglist/application/useGetAllShoppinglist";
import { useCreateShoppinglistMetadata } from "@/Shoppinglist/application/useCreateShoppinglistMetadata";
import { useShoppinglistStore } from "@/Shoppinglist/stores/shoppinglistStore";
import Header from "@/Shared/components/Header.vue";
import Footer from "@/Shared/components/Footer.vue";
import Information from "@/Shared/components/Information.vue";
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

// Map con las secciones
const mapSections = ref(
  new Map<string, string>([
    ["all", "Todas"],
    ["actives", "Activas"],
    ["archives", "Archivadas"],
  ])
);

let selectedTab = ref<string>("all");

// Este listado representa los elementos que son visibles actualmente en la pantalla
const actualShoppinglistVisible = ref<Shoppinglist[]>([]);

onMounted(async () => {
  shoppinglistTable.value = await getAllShoppinglist();
  store.setShoppinglistArray(shoppinglistTable.value);
  updateShoppinglistTables(false);
});

watch(selectedTab, (newSelectedTab) => {
  selectedTab.value = newSelectedTab;
  actualShoppinglistVisible.value = [];
  updateShoppinglistElementsVisible(false);
});

// Este metodo realiza el calculo de los elementos que van a ser visible, va de 50 en 50
function updateShoppinglistElementsVisible(removedObject: boolean) {
  if (removedObject) {
    // Si se va a borrar un elemento, necesito volver a cargar la lista entera
    // para que asi la lista de actuales visibles corresponda
    actualShoppinglistVisible.value = [];
  }
  const start = actualShoppinglistVisible.value.length;
  // Indicamos con que lista se debe trabajar en funcion del tab seleccionado
  let selected_list: Shoppinglist[] = [];
  if (selectedTab.value === "all") {
    selected_list = store.shoppinglistArray;
  }
  if (selectedTab.value === "actives") {
    selected_list = shoppinglistActiveTable.value;
  }
  if (selectedTab.value === "archives") {
    selected_list = shoppinglistNoActiveTable.value;
  }
  for (let i = 0; i < 50; i++) {
    // ShoppinglistTable.vue (LINEA 105) => Revisar si hay alguna forma de obviar el !== undefined
    if (selected_list[start + i] !== undefined) {
      actualShoppinglistVisible.value.push(selected_list[start + i]);
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

async function addNewShoppinglist() {
  let shoppinglistMetadata: Shoppinglist = await createShoppinglistMetadata();
  if (shoppinglistMetadata) {
    store.addShoppinglist(shoppinglistMetadata);
    updateShoppinglistTables(false);
  }
}

function updateShoppinglistTables(removedObject: boolean = true) {
  shoppinglistActiveTable.value = store.getActiveShoppinglist();
  shoppinglistNoActiveTable.value = store.getNoActiveShoppinglist();
  updateShoppinglistElementsVisible(removedObject);
}

function assignActualSelectedTabBySlide(data: SegmentChangeEventDetail) {
  selectedTab.value = String(data.value);
}
</script>
<template>
  <IonPage>
    <Header :title="'Listas de la compra'"></Header>
    <Footer></Footer>
    <IonContent>
      <div v-if="actualShoppinglistVisible.length === 0">
        <Information
          :title="'LISTA DE LA COMPRA'"
          :message="'No hay listas de la compra para esta opción'"
        ></Information>
      </div>
      <div v-else>
        <IonSegment @ion-change="assignActualSelectedTabBySlide($event.detail)">
          <IonSegmentButton
            v-for="value in mapSections"
            :value="value[0]"
            :content-id="value[0]"
            @click="selectedTab = value[0]"
          >
            <IonLabel>{{ value[1] }}</IonLabel>
          </IonSegmentButton>
        </IonSegment>
        <IonSegmentView>
          <IonSegmentContent v-for="value in mapSections" :id="value[0]">
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
          </IonSegmentContent>
        </IonSegmentView>
      </div>
      <IonFab horizontal="end" vertical="bottom" slot="fixed">
        <IonFabButton @click="addNewShoppinglist">
          <IonIcon name="add-outline" />
        </IonFabButton>
      </IonFab>
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
