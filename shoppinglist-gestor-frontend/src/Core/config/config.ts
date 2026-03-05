// DNS ENV
export const LOCAL_BASE_URL = ''
export const PRODUCTION_BASE_URL = ''
// -----------------------------------------------------------------------------------------------------
// --------- SHOPPINGLIST_ENDPOINTS --------------------------------------------------------------------
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
  ADD_SHOPPINGLIST_ITEM_V1: (idShoppinglist: number, idShoppinglistItem: number): string => {
    return (
      SHOPPINGLIST_ENDPOINTS.BASE_URL +
      `/v1/${idShoppinglist}/addShoppinglistItem?idShoppinglistItem=${idShoppinglistItem}`
    )
  },
}
// ----------------------------------------------------------------------------------------------
// --------- SHOPPINGLIST_VIEW_ENDPOINTS --------------------------------------------------------
export const SHOPPINGLIST_VIEW_ENDPOINTS = {
  BASE_URL: '/api/shoppinglistview/',
  GET_ALL_V1: (): string => {
    return SHOPPINGLIST_VIEW_ENDPOINTS.BASE_URL + `v1/all`
  },
  GET_DETAILS_V1: (idShoppinglist: number): string => {
    return SHOPPINGLIST_VIEW_ENDPOINTS.BASE_URL + `v1/${idShoppinglist}/details`
  },
}
// -----------------------------------------------------------------------------------------------
// ---------- CALCULATE_SYSTEM_ENDPOINTS ---------------------------------------------------------
export const CALCULATE_SYSTEM_ENDPOINTS = {
  BASE_URL: '/api/calculateSystem/',
  GET_ALL_V1: (): string => {
    return CALCULATE_SYSTEM_ENDPOINTS.BASE_URL + `v1/all`
  },
}
// ------------------------------------------------------------------------------------------------
// ---------- PRODUCTS_ENDPOINTS ------------------------------------------------------------------
export const PRODUCTS_ENDPOINTS = {
  BASE_URL: '/api/product/',
  GET_NAMES_V1: (): string => {
    return PRODUCTS_ENDPOINTS.BASE_URL + `v1/names`
  },
}
// -------------------------------------------------------------------------------------------------
// ---------- SHOPPINGLIST_ITEMS_ENDPOINTS ---------------------------------------------------------
export const SHOPPINGLIST_ITEMS_ENDPOINTS = {
  BASE_URL: '/api/shoppinglistitem',
  DELETE_V1: (idShoppinglistItem: number): string => {
    return SHOPPINGLIST_ITEMS_ENDPOINTS.BASE_URL + `/v1/${idShoppinglistItem}/delete`
  },
  GET_ITEM_UNIT_WP_V1: (idShoppinglistItem: number): string => {
    return SHOPPINGLIST_ITEMS_ENDPOINTS.BASE_URL + `/v1/${idShoppinglistItem}/itemsUnitsWpInfo`
  },
}
