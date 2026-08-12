import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { RequestUpItemUnitUpUpdateMetadata } from '../infrastructure/models/request/RequestUpItemUnitUpdateMetadata'

export const useUpdateItemUnitUpdateMetadataStore = defineStore(
  'upItemUnitUpdateMetadataStore',
  () => {
    const requestUpItemUnitUpdateMetadataList = ref<RequestUpItemUnitUpUpdateMetadata[]>([])

    function add(data: RequestUpItemUnitUpUpdateMetadata) {
      requestUpItemUnitUpdateMetadataList.value.push(data)
    }

    function removeOldValue(idItemUnit: number, idItemUnitUp: number) {
      requestUpItemUnitUpdateMetadataList.value.forEach((element, index) => {
        if (idItemUnit === element.idItemUnit && idItemUnitUp === element.idItemUnitUp) {
          requestUpItemUnitUpdateMetadataList.value.splice(index, 1)
        }
      })
    }

    function clear() {
      requestUpItemUnitUpdateMetadataList.value = []
    }

    return {
      requestUpItemUnitUpdateMetadataList,
      removeOldValue,
      add,
      clear,
    }
  },
)
