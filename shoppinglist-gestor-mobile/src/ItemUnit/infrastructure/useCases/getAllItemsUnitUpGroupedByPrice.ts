import responseGettAllItemUnitUpGroupedByPrice from '@/ItemUnit/infrastructure/mocks/responseGetAllItemUnitUpGroupedByPrice.json'
import { createItemUnitUpGroupedByPriceList } from '../services/itemUnitService';

async function InMemory() : Promise<ResponseGetAllItemUnitUpGroupedByPrice> {
    return responseGettAllItemUnitUpGroupedByPrice as ResponseGetAllItemUnitUpGroupedByPrice;
}

async function getAllItemsUnitUpGroupedByPrice() : Promise<ItemUnitUpGroupedByPriceList> {
 return createItemUnitUpGroupedByPriceList(await InMemory());
} 

export {getAllItemsUnitUpGroupedByPrice}