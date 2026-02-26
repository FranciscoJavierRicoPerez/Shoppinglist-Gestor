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
      UPDATE_IS_ACTIVE_V1: '/v1/{idShoppinglist}/update/isActive',
      DELETE_LOGIC_V1: '/v1/{idShoppinglist}/delete/logic',
      ADD_SHOPPINGLIST_ITEM_V1: '/v1/{idShoppinglist}/addShoppinglistItem',
      UPDATE_TOTAL_PRICE_V1: '/v1/{idShoppinglist}/updateTotalPrice',
    },
    SHOPPINGLIST_VIEW: {
      BASE_URL: '/api/shoppinglistview/',
      GET_ALL_V1: 'v1/all',
      GET_DETAILS_V1: 'v1/{id}/details',
    },
    SHOPPINGLIST_ITEM: {},
    ITEM_UNIT: {},
    PRODUCT: {},
    CALCULATE_SYSTEM: {},
    UP_ITEM_UNIT: {},
  },
} 
*/

export const SHOPPINGLIST_ENDPOINTS = {}

export const SHOPPINGLIST_VIEW_ENDPOINTS = {
  BASE_URL: '/api/shoppinglistview/',
  GET_DETAILS_V1: (idShoppinglist: number): string => {
    return SHOPPINGLIST_VIEW_ENDPOINTS.BASE_URL + `v1/${idShoppinglist}/details`
  },
}
