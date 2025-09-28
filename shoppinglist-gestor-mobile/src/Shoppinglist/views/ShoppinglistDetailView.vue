<script setup lang="ts">
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
} from "@ionic/vue";
import Header from "@/Shared/components/Header.vue";
import Footer from "@/Shared/components/Footer.vue";
import { onMounted, ref, watch } from "vue";
import {
  defaultShoppinglistDetails,
  type ShoppinglistDetails,
} from "@/Shoppinglist/domain/ShoppinglistDetails";
import { useGetShoppinglistDetails } from "@/Shoppinglist/application/useGetShoppinglistDetails";
import ShoppinglistItemCard from "@/ShoppinglistItem/components/ShoppinglistItemCard.vue";
import { useRoute } from "vue-router";
import ShoppinglistItemAddDialog from "@/ShoppinglistItem/components/ShoppinglistItemAddDialog.vue";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import Information from "@/Shared/components/Information.vue";
import { useShoppinglistItemStore } from "@/ShoppinglistItem/stores/shoppinglistItemStore";
import { useShoppinglistDetailsStore } from "@/Shoppinglist/stores/shoppinglistDetailsStore";
const { refetch: getShoppinglistDetails } = useGetShoppinglistDetails();

const shoppinglistDetails = ref<ShoppinglistDetails>({
  ...defaultShoppinglistDetails,
});

const store = useShoppinglistItemStore();
const shoppinglistDetailsStore = useShoppinglistDetailsStore();
const actualShoppinglistItemsVisible = ref<ShoppinglistItemMetadata[]>([]);
const route = useRoute();

const openModal = ref<boolean>(false);

onMounted(async () => {
  // We have to obtain the object ShoppinglistDetails
  const param = Number(route.params.id);
  shoppinglistDetails.value = await getShoppinglistDetails(param);
  shoppinglistDetailsStore.setShoppinglistDetails(shoppinglistDetails.value);
  store.setShoppinglistMetadataArray(shoppinglistDetails.value.items);
  updateShoppinglistItemsElementsVisible();
});

// Evento infinito
const ionInfinite = (event: InfiniteScrollCustomEvent) => {
  updateShoppinglistItemsElementsVisible();
  setTimeout(() => {
    event.target.complete();
  }, 500);
};

function updateTotalPrice() {
  shoppinglistDetailsStore.updateTotalPrice();
}

function updateShoppinglistItemsElementsVisible() {
  const start = actualShoppinglistItemsVisible.value.length + 1;
  for (let i = 0; i < 50; i++) {
    actualShoppinglistItemsVisible.value.push(
      store.shoppinglistItemMetadataArray[start + i]
    );
  }
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
            <IonChip color="primary">{{ shoppinglistDetails.code }}</IonChip>
            <div v-if="shoppinglistDetails.isActive">
              <IonChip color="success">Activa</IonChip>
            </div>
            <div v-else>
              <IonChip color="success">Archivado</IonChip>
            </div>
            <IonChip color="danger"
              >Precio total: {{ shoppinglistDetailsStore.totalPrice }}</IonChip
            >
          </IonCardTitle>
          <IonCardSubtitle>
            <IonChip color="warning"
              >Lista de la compra del {{ shoppinglistDetails.creationDate }}
            </IonChip>
          </IonCardSubtitle>
        </IonCardHeader>
        <IonCardContent>
          <div v-if="store.shoppinglistItemMetadataArray.length === 0">
            <Information
              :title="'ARTICULOS'"
              :message="'No hay articulos en esta lista de la compra'"
            ></Information>
          </div>
          <div v-else>
            <IonList>
              <ShoppinglistItemCard
                :shoppinglistItemList="store.shoppinglistItemMetadataArray"
              ></ShoppinglistItemCard>
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
        @update-shoppinglist-item-list="updateShoppinglistItemsElementsVisible"
        @update-modal-open-value="openModal = !openModal"
        @update-total-price="updateTotalPrice"
      ></ShoppinglistItemAddDialog>
    </IonContent>
  </IonPage>
</template>
<style lang="css"></style>
