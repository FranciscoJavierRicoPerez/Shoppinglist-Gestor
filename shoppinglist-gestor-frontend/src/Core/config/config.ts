// DNS ENV
export const LOCAL_BASE_URL = ''
export const PRODUCTION_BASE_URL = ''

/* export const API = {
  ENDPOINTS: {
    SHOPPINGLIST: {
      BASE_URL: '/api/shoppinglist',
      GET_ALL_V1: '/v1/all',
      CREATE_V1: '/v1/create',
      CREATE_V2: '/v2/create',
      DELETE_V1: '/v1/delete/{idShoppinglist}',
      UPDATE_V1: '/v1/update',
      FILTER_V1: '/v1/filter',
      GET_DETAILS_V1: '/v1/{idShoppinglist}/details',
      UPDATE_IS_ACTIVE_V1: '/v1/{idShoppinglist}/update/isActive', X
      DELETE_LOGIC_V1: '/v1/{idShoppinglist}/delete/logic',
      ADD_SHOPPINGLIST_ITEM_V1: '/v1/{idShoppinglist}/addShoppinglistItem',
      UPDATE_TOTAL_PRICE_V1: '/v1/{idShoppinglist}/updateTotalPrice',
    },
    SHOPPINGLIST_VIEW: {
      BASE_URL: '/api/shoppinglistview/',
      GET_ALL_V1: 'v1/all', X
      GET_DETAILS_V1: 'v1/{id}/details', X
    },
    SHOPPINGLIST_ITEM: {},
    ITEM_UNIT: {},
    PRODUCT: {},
    CALCULATE_SYSTEM: {},
    UP_ITEM_UNIT: {},
  },
} 
*/

// --------- SHOPPINGLIST_ENDPOINTS -------------------------
export const SHOPPINGLIST_ENDPOINTS = {
  BASE_URL: '/api/shoppinglist',
  CREATE_V2: (): string => {
    return SHOPPINGLIST_ENDPOINTS.BASE_URL + `/v2/create`
  },
  UPDATE_IS_ACTIVE_V1: (idShoppinglist: number): string => {
    return SHOPPINGLIST_ENDPOINTS.BASE_URL + `/v1/${idShoppinglist}/update/isActive`
  },
  FILTER_V1: (
    code: string | null,
    creationDate: Date | null,
    closeDate: Date | null,
    totalPrice: number | null,
    isActive: boolean | null,
  ): string => {
    let params: string = ''
    if (code && code !== '') {
      params += `code=${code}`
    }
    if (creationDate) {
      if (params !== '') {
        params += '&'
      }
      let dateFormatted: string = ''
      let args: string[] = creationDate.toLocaleDateString().split('/')
      dateFormatted = args[2] + '-' + (args[1].length < 2 ? '0' + args[1] : args[1]) + '-' + args[0]
      params += `creationDate=${dateFormatted}`
    }
    if (closeDate) {
      if (params !== '') {
        params += '&'
      }
      let dateFormatted: string = ''
      let args: string[] = closeDate.toLocaleDateString().split('/')
      dateFormatted = args[2] + '-' + (args[1].length < 2 ? '0' + args[1] : args[1]) + '-' + args[0]
      params += `closeDate=${dateFormatted}`
    }
    if (totalPrice) {
      if (params !== '') {
        params += '&'
      }
      params += `totalPrice=${totalPrice}`
    }
    if (isActive) {
      if (params !== '') {
        params += '&'
      }
      params += `isActive=${isActive}`
    }
    return SHOPPINGLIST_ENDPOINTS.BASE_URL + `/v1/filter?` + params
  },
  DELETE_LOGIC_V1: (idShoppinglist: number): string => {
    return SHOPPINGLIST_ENDPOINTS.BASE_URL + `/v1/${idShoppinglist}/delete/logic`
  },
}

// --------- SHOPPINGLIST_VIEW_ENDPOINTS --------------------
export const SHOPPINGLIST_VIEW_ENDPOINTS = {
  BASE_URL: '/api/shoppinglistview/',
  GET_ALL_V1: (): string => {
    return SHOPPINGLIST_VIEW_ENDPOINTS.BASE_URL + `v1/all`
  },
  GET_DETAILS_V1: (idShoppinglist: number): string => {
    return SHOPPINGLIST_VIEW_ENDPOINTS.BASE_URL + `v1/${idShoppinglist}/details`
  },
}
