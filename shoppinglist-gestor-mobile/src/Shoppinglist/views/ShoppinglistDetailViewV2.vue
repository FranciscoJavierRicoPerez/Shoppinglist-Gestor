<script setup lang="ts">
import { useGetShoppinglistDetailsView } from "@/Shoppinglist/application/useGetShoppinglistDetailsView";
import { onMounted, ref } from "vue";
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
} from "@ionic/vue";
import Header from "@/Shared/components/Header.vue";
import Footer from "@/Shared/components/Footer.vue";
import Information from "@/Shared/components/Information.vue";

import ShoppinglistItemCard from "@/ShoppinglistItem/components/ShoppinglistItemCard.vue";
import { ShoppinglistItemMetadata } from "@/ShoppinglistItem/domain/ShoppinglistItemMetadata";

const shoppinglistDetailsViewStore = useShoppinglistDetailsViewStore();
const { refetch: getShoppinglistDetailsView } = useGetShoppinglistDetailsView();
const route = useRoute();
const openModal = ref<boolean>(false);
onMounted(async () => {
  const param = Number(route.params.id);
  const response = await getShoppinglistDetailsView(param);
  shoppinglistDetailsViewStore.setShoppinglistDetailsView(response);
  shoppinglistDetailsViewStore.setShoppinglistDetailsViewItems(response.items);
  shoppinglistDetailsViewStore.setTotalPrice(
    shoppinglistDetailsViewStore.shoppinglistDetailsViewItems
  );
  updateShoppinglistItemsElementsVisible();
});

const actualShoppinglistItemsVisible = ref<ShoppinglistItemMetadata[]>([]);

// Evento infinito
const ionInfinite = (event: InfiniteScrollCustomEvent) => {
  updateShoppinglistItemsElementsVisible();
  setTimeout(() => {
    event.target.complete();
  }, 500);
};

function updateShoppinglistItemsElementsVisible() {
  const start = actualShoppinglistItemsVisible.value.length;
  for (let i = 0; i < 50; i++) {
    actualShoppinglistItemsVisible.value.push(
      shoppinglistDetailsViewStore.shoppinglistDetailsViewItems[start + i]
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
            <IonChip color="primary">{{
              shoppinglistDetailsViewStore.shoppinglistDetailsView
                ?.shoppinglistMetadata.code
            }}</IonChip>
            <div
              v-if="
                shoppinglistDetailsViewStore.shoppinglistDetailsView
                  ?.shoppinglistMetadata.isActive
              "
            >
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
              {{
                shoppinglistDetailsViewStore.shoppinglistDetailsView
                  ?.shoppinglistMetadata.creationDate
              }}
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
              <ShoppinglistItemCard
                :shoppinglistItemList="
                  shoppinglistDetailsViewStore.shoppinglistDetailsViewItems
                "
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
        @update-modal-open-value="openModal = !openModal"
      ></ShoppinglistItemAddDialog>
    </IonContent>
  </IonPage>
</template>
<style lang="css"></style>
