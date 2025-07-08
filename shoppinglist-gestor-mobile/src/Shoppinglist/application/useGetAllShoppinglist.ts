import type { Shoppinglist } from "@/Shoppinglist/domain/Shoppinglist";
import { getAllShoppinglist } from "@/Shoppinglist/infrastructure/useCases/getAllShoppinglist";
import { ref } from "vue";
export function useGetAllShoppinglist() {
  const loading = ref(false);
  const data = ref<Shoppinglist[]>([]);
  async function refetch(): Promise<Shoppinglist[]> {
    loading.value = true;
    try {
      const response = await getAllShoppinglist();
      data.value = response;
      return response;
    } finally {
      loading.value = false;
    }
  }
  return { refetch, loading, data };
}
