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
  IonInfiniteScroll,
  IonInfiniteScrollContent,
  IonList,
  IonPage,
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
import ShoppinglistItemCard from '@/ShoppinglistItem/components/ShoppinglistItemCard.vue';
import { useRoute } from "vue-router";
const { refetch: getShoppinglistDetails } = useGetShoppinglistDetails();

const shoppinglistDetails = ref<ShoppinglistDetails>({
  ...defaultShoppinglistDetails,
});

const actualShoppinglistItemsVisible = ref<ShoppinglistItem[]>([]);
const route = useRoute();

onMounted(async () => {
  // We have to obtain the object ShoppinglistDetails
  const param = Number(route.params.id);
  console.log(param);
  debugger;
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
          <IonList>
            <ShoppinglistItemCard :shoppinglistItemList="shoppinglistDetails.items"></ShoppinglistItemCard>
          </IonList>
          <IonInfiniteScroll @ionInfinite="ionInfinite">
            <IonInfiniteScrollContent></IonInfiniteScrollContent>
          </IonInfiniteScroll>
        </IonCardContent>
      </IonCard>
    </IonContent>
  </IonPage>
</template>
<style lang="css">
/* .panelHeader {
  font-size: xx-large;
  font-weight: bold;
}
.multiDate {
  margin-left: 1rem;
}
.panelSeparations {
  margin-right: 0.2rem;
}

.cardOrganization {
  display: flex;
  flex-wrap: wrap;
} */
</style>
