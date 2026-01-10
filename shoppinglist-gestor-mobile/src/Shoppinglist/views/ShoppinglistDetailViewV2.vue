<script setup lang="ts">
import { useGetShoppinglistDetailsView } from "@/Shoppinglist/application/useGetShoppinglistDetailsView";
import { onMounted, ref, watch } from "vue";
import { useRoute } from "vue-router";
import { useShoppinglistDetailsViewStore } from "@/Shoppinglist/stores/shoppinglistDetailsViewStore";
import ShoppinglistItemAddDialog from "@/ShoppinglistItem/components/ShoppinglistItemAddDialog.vue";
import {
  InfiniteScrollCustomEvent,
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonChip,
  IonContent,
  IonFab,
  IonFabButton,
  IonInfiniteScroll,
  IonInfiniteScrollContent,
  IonList,
  IonPage,
  IonIcon,
  IonLabel,
} from "@ionic/vue";
import Header from "@/Shared/components/Header.vue";
import Footer from "@/Shared/components/Footer.vue";
import Information from "@/Shared/components/Information.vue";

import ShoppinglistItemCard from "@/ShoppinglistItem/components/ShoppinglistItemCard.vue";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import { ShoppinglistMetadata } from "../domain/ShoppinglistMetadata";

const shoppinglistDetailsViewStore = useShoppinglistDetailsViewStore();
const { refetch: getShoppinglistDetailsView } = useGetShoppinglistDetailsView();
const route = useRoute();
const openModal = ref<boolean>(false);
const actualShoppinglistItemsVisible = ref<ShoppinglistItemMetadata[]>([]);

const shoppinglistMetadata = ref<ShoppinglistMetadata>();
// const totalPrice = ref<number>(-1)
/* const updateShoppinglistItemList = ref<boolean>(false);

watch(updateShoppinglistItemList, (newValue) => {
  debugger;
  // updateShoppinglistItemList.value = newValue;
  if (updateShoppinglistItemList.value) {
    actualShoppinglistItemsVisible.value =
      shoppinglistDetailsViewStore.shoppinglistDetailsViewItems;
  }
}); */

onMounted(async () => {
  const param = Number(route.params.id);
  const response = await getShoppinglistDetailsView(param);
  shoppinglistDetailsViewStore.setShoppinglistMetadata(
    response.shoppinglistMetadata
  );
  shoppinglistDetailsViewStore.setShoppinglistDetailsView(response);
  shoppinglistDetailsViewStore.setShoppinglistDetailsViewItems(response.items);
  shoppinglistDetailsViewStore.setTotalPrice(
    shoppinglistDetailsViewStore.shoppinglistDetailsViewItems
  );
  shoppinglistMetadata.value =
    shoppinglistDetailsViewStore.shoppinglistMetadata;
  updateShoppinglistItemsElementsVisible(false);
});

// Evento infinito
const ionInfinite = (event: InfiniteScrollCustomEvent) => {
  updateShoppinglistItemsElementsVisible(false);
  setTimeout(() => {
    event.target.complete();
  }, 500);
};

function updateShoppinglistItemsElementsVisible(removedObject: boolean) {
  if (removedObject) {
    actualShoppinglistItemsVisible.value = [];
  }
  let elementsList: ShoppinglistItemMetadata[] =
    shoppinglistDetailsViewStore.shoppinglistDetailsViewItems;
  const start = actualShoppinglistItemsVisible.value.length;
  const chunkSize = 50;

  const nextChunk = elementsList.slice(start, start + chunkSize);

  actualShoppinglistItemsVisible.value.push(...nextChunk);
}

function updateShoppinglistElementsVisible(removedObject: boolean = true) {
  updateShoppinglistItemsElementsVisible(removedObject);
}
</script>
<template>
  <IonPage>
    <Header :title="'Detalle de la lista'"></Header>
    <Footer></Footer>
    <IonContent>
      <IonCard>
        <IonCardHeader>
          <IonCardTitle>
            <IonChip color="primary">{{ shoppinglistMetadata?.code }}</IonChip>
            <div v-if="shoppinglistMetadata?.isActive">
              <IonChip color="success">Activa</IonChip>
            </div>
            <div v-else>
              <IonChip color="success">Archivado</IonChip>
            </div>
            <IonChip color="danger"
              >Precio total:
              {{ shoppinglistDetailsViewStore.totalPrice }}</IonChip
            >
          </IonCardTitle>
          <IonCardSubtitle>
            <IonChip color="warning"
              >Lista de la compra del
              {{ shoppinglistMetadata?.creationDate }}
            </IonChip>
          </IonCardSubtitle>
        </IonCardHeader>
        <IonCardContent>
          <div
            v-if="
              shoppinglistDetailsViewStore.shoppinglistDetailsViewItems
                .length === 0
            "
          >
            <Information
              :title="'ARTICULOS'"
              :message="'No hay articulos en esta lista de la compra'"
            ></Information>
          </div>
          <div v-else>
            <IonList>
              <div
                v-for="value in actualShoppinglistItemsVisible"
                :key="value.id"
              >
                <IonLabel>
                  <ShoppinglistItemCard
                    :shoppinglistItem="value"
                    @update-visible-elements="updateShoppinglistElementsVisible"
                  ></ShoppinglistItemCard>
                </IonLabel>
              </div>
            </IonList>
            <IonInfiniteScroll @ionInfinite="ionInfinite">
              <IonInfiniteScrollContent></IonInfiniteScrollContent>
            </IonInfiniteScroll>
          </div>
        </IonCardContent>
      </IonCard>
      <IonFab horizontal="end" vertical="bottom" slot="fixed">
        <IonFabButton @click="openModal = !openModal">
          <IonIcon name="add-outline"></IonIcon>
        </IonFabButton>
      </IonFab>
      <ShoppinglistItemAddDialog
        :open-modal="openModal"
        @update-modal-open-value="openModal = !openModal"
      ></ShoppinglistItemAddDialog>
    </IonContent>
  </IonPage>
</template>
<style lang="css"></style>
