import { defineStore } from 'pinia'
import { ref } from 'vue'
import type { RequestUpItemUnitUpUpdateMetadata } from '../infrastructure/models/request/RequestUpItemUnitUpdateMetadata'

export const useUpdateItemUnitUpdateMetadataStore = defineStore(
  'upItemUnitUpdateMetadataStore',
  () => {
    const updateButtonDisabled = ref<boolean>(true)
    const requestUpItemUnitUpdateMetadataList = ref<RequestUpItemUnitUpUpdateMetadata[]>([])

    function add(data: RequestUpItemUnitUpUpdateMetadata) {
      updateButtonDisabled.value = false
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
      updateButtonDisabled.value = false
      requestUpItemUnitUpdateMetadataList.value = []
    }

    function updateButtonDisabledValue() {
      updateButtonDisabled.value = !updateButtonDisabled.value
    }

    return {
      requestUpItemUnitUpdateMetadataList,
      updateButtonDisabled,
      removeOldValue,
      add,
      clear,
      updateButtonDisabledValue,
    }
  },
)
