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
import { onMounted, ref } from "vue";
import {
  defaultShoppinglistDetails,
  type ShoppinglistDetails,
} from "@/Shoppinglist/domain/ShoppinglistDetails";
import { useGetShoppinglistDetails } from "@/Shoppinglist/application/useGetShoppinglistDetails";
import { ShoppinglistItem } from "@/ShoppinglistItem/domain/ShoppinglistItem";
import ShoppinglistItemCard from "@/ShoppinglistItem/components/ShoppinglistItemCard.vue";
import { useRoute } from "vue-router";
import ShoppinglistItemAddDialog from "@/ShoppinglistItem/components/ShoppinglistItemAddDialog.vue";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";
import Information from "@/Shared/components/Information.vue";
const { refetch: getShoppinglistDetails } = useGetShoppinglistDetails();

const shoppinglistDetails = ref<ShoppinglistDetails>({
  ...defaultShoppinglistDetails,
});

const actualShoppinglistItemsVisible = ref<ShoppinglistItemMetadata[]>([]);
const route = useRoute();

const openModal = ref<boolean>(false);

onMounted(async () => {
  // We have to obtain the object ShoppinglistDetails
  const param = Number(route.params.id);
  shoppinglistDetails.value = await getShoppinglistDetails(param);
  updateShoppinglistItemsElementsVisible();
});

// Evento infinito
const ionInfinite = (event: InfiniteScrollCustomEvent) => {
  updateShoppinglistItemsElementsVisible();
  setTimeout(() => {
    event.target.complete();
  }, 500);
};

function updateShoppinglistItemsElementsVisible() {
  const start = actualShoppinglistItemsVisible.value.length + 1;
  for (let i = 0; i < 50; i++) {
    actualShoppinglistItemsVisible.value.push(
      shoppinglistDetails.value.items[start + i]
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
              >Precio total: {{ shoppinglistDetails.totalPrice }}</IonChip
            >
          </IonCardTitle>
          <IonCardSubtitle>
            <IonChip color="warning"
              >Lista de la compra del {{ shoppinglistDetails.creationDate }} al
              {{ shoppinglistDetails.closeDate }}</IonChip
            >
          </IonCardSubtitle>
        </IonCardHeader>
        <IonCardContent>
         <div v-if="shoppinglistDetails.items.length === 0">
          <Information :title="'ARTICULOS'" :message="'No hay articulos en esta lista de la compra'"></Information>
         </div>
         <div v-else>
           <IonList>
            <ShoppinglistItemCard
              :shoppinglistItemList="shoppinglistDetails.items"
            ></ShoppinglistItemCard>
          </IonList>
          <IonInfiniteScroll @ionInfinite="ionInfinite">
            <IonInfiniteScrollContent></IonInfiniteScrollContent>
          </IonInfiniteScroll>
         </div>
        </IonCardContent>
      </IonCard>
      <IonFab horizontal="end" vertical="bottom" slot="fixed">
        <IonFabButton @click="openModal = !openModal
        ">
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
