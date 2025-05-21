package es.franricodev.shopping_list_gestor_service.shoppinglistitem.service;

import es.franricodev.shopping_list_gestor_service.product.exception.ProductException;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import es.franricodev.shopping_list_gestor_service.product.service.ProductService;
import es.franricodev.shopping_list_gestor_service.shoppinglist.exception.ShoppinglistException;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.shoppinglist.service.ShoppinglistService;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.ShoppinglistItemDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.dto.request.RequestCreateShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.exception.ShoppinglistItemException;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.model.ShoppinglistItem;
import es.franricodev.shopping_list_gestor_service.shoppinglistitem.repository.ShoppinglistItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShoppinglistItemServiceImpl implements ShoppinglistItemService{

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppinglistService shoppinglistService;

    @Autowired
    private ShoppinglistItemRepository shoppinglistItemRepository;

    private final static Logger logger = LoggerFactory.getLogger(ShoppinglistItemServiceImpl.class);

    @Override
    public ShoppinglistItemDTO createShoppinglistItem(
            RequestCreateShoppinglistItem requestCreateShoppinglistItem,
            Long idShoppinglist) throws ShoppinglistItemException {
        logger.info("Creating a new shoppinglist item");
        try {
            ShoppinglistItem shoppinglistItem = new ShoppinglistItem();
            Shoppinglist shoppinglist = shoppinglistService.findShoppinglistById(idShoppinglist);
            /**
             *  TODO: PASOS PARA LA CORRECTA IMPLEMENTACION DE UN SHOPPINGLIST_ITEM
             * - ¿QUE DEBERIA TENER UN SHOPPINGLIST_ITEM?
             *      -> Nombre del item (NOMBRE DEL PRODUCTO)
             *      -> Fecha de asignacion a la lista
             *      -> El producto asociado (1 - *) => 1 producto puede estar en muchas listas
             *      -> El tipo de sistema de calculo (PRECIO_UNITARIO o KG/€) -> ENUM??
             *      -> UNIDADES_DEL_ITEM
             *
             *      1.- Primero hay que comprobar si el PRODUCTO que viene en el request object existe o no
             *          Si existe se asigna a la nueva instancia de SHOPPINGLIST_ITEM
             *          si no existe se crea y se asigna
             *
             *      2.- Del producto que se ha asignado a la lista se obtiene el nombre del item
             *      3.- Añadir fecha de asignacion a la lista
             */

            // TODO: PASO_1
            Product product = null;
            if(requestCreateShoppinglistItem.getProductId() == null) {
                product = productService.createProduct(requestCreateShoppinglistItem.getProductName());
            } else {
                product = productService.findProductByName(requestCreateShoppinglistItem.getProductName());
            }
            // TODO: PASO_2
            if(product != null) {
                shoppinglistItem.setProduct(product);
                shoppinglistItem.setName(product.getName());
            }
            shoppinglistItem.setAssignationToListDate(new Date());


        } catch (ShoppinglistException | ProductException e) {
            throw new ShoppinglistItemException("ERR_CREATING_SHOPPINGLIST_ITEM");
        }
        return null;
    }
}
