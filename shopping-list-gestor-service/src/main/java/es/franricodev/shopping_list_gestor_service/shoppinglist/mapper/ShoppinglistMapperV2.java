package es.franricodev.shopping_list_gestor_service.shoppinglist.mapper;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestCreateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestUpdateShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.ShoppinglistDetailsDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.response.ShoppinglistMetadata;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import es.franricodev.shopping_list_gestor_service.utils.DateUtils;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

import static es.franricodev.shopping_list_gestor_service.shoppinglistitem.mapper.lombock.ShoppinglistItemMapper.*;

@NoArgsConstructor
public class ShoppinglistMapperV2 {

    public static List<ShoppinglistDTO> toShoppinglistDtoList(List<Shoppinglist> list) {
        return list.stream().map(ShoppinglistMapperV2::toShoppinglistDto).toList();
    }

     public static ShoppinglistDTO toShoppinglistDto(Shoppinglist shoppinglist) {
         return ShoppinglistDTO
                 .builder()
                 .id(shoppinglist.getId())
                 .code(shoppinglist.getCode())
                 .creationDate(Date.from(shoppinglist.getCreationDate().atStartOfDay(ZoneOffset.UTC).toInstant()))
                 .closeDate(Date.from(shoppinglist.getCloseDate().atStartOfDay(ZoneOffset.UTC).toInstant()))
                 .isActive(shoppinglist.getIsActive())
                 .totalPrice(shoppinglist.getTotalPrice())
                 .build();
     }

     public static Shoppinglist generateShoppinglist(RequestCreateShoppinglistDTO request) {
         Shoppinglist shoppinglist = new Shoppinglist();
         shoppinglist.setCode(generateShoppinglistCode(request.getCode()));
         shoppinglist.setInfoBlock(false);
         shoppinglist.setTotalPrice(request.getTotalPrice());
         shoppinglist.setIsActive(request.getIsActive());
         shoppinglist.setCreationDate(LocalDate.now());
         return shoppinglist;
     }

     public static void toUpdateShoppinglist(Shoppinglist shoppinglist, RequestUpdateShoppinglistDTO request) {
        shoppinglist.setId(request.getId());
        shoppinglist.setCode(request.getCode());
        shoppinglist.setTotalPrice(request.getTotalPrice());
        shoppinglist.setIsActive(request.getIsActive());
     }

     public static ShoppinglistDetailsDTO toShoppinglistDetailsDto(Shoppinglist shoppinglist) {
        return ShoppinglistDetailsDTO
                .builder()
                .id(shoppinglist.getId())
                .code(shoppinglist.getCode())
                .totalPrice(shoppinglist.getTotalPrice())
                .creationDate(Date.from(shoppinglist.getCreationDate().atStartOfDay(ZoneOffset.UTC).toInstant()))
                .closeDate(Date.from(shoppinglist.getCloseDate().atStartOfDay(ZoneOffset.UTC).toInstant()))
                .isActive(shoppinglist.getIsActive())
                .items(toShoppinglistItemMetadataDTOList(shoppinglist.getItems()))
                .build();
     }

     public static ShoppinglistMetadata toShoppinglistMetadata(Shoppinglist shoppinglist) {
         return ShoppinglistMetadata.builder()
                 .code(shoppinglist.getCode())
                 .creationDate(DateUtils.formatLocalDate(shoppinglist.getCreationDate(), "dd/MM/yyyy"))
                 .closeDate(DateUtils.formatLocalDate(shoppinglist.getCloseDate(), "dd/MM/yyyy"))
                 .isActive(shoppinglist.getIsActive())
                 .totalPrice(shoppinglist.getTotalPrice())
                 .idShoppinglist(shoppinglist.getId())
                 .build();
     }

     private static String generateShoppinglistCode(String code) {
         return "SL-" + code + "_" + DateUtils.formatLocalDate(LocalDate.now(), "dd/MM/yyyy");
     }

}
