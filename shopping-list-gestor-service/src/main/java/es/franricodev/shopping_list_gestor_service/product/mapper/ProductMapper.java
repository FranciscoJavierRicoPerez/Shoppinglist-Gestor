package es.franricodev.shopping_list_gestor_service.product.mapper;

import es.franricodev.shopping_list_gestor_service.calculateSystem.mapper.CalculateSystemMapper;
import es.franricodev.shopping_list_gestor_service.product.dto.ProductDTO;
import es.franricodev.shopping_list_gestor_service.product.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = { CalculateSystemMapper.class}
)
public interface ProductMapper {

    // ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDto(Product product);

    Product productDtoToProduct(ProductDTO productDTO);

    List<ProductDTO> productListToProductDtoList(List<Product> productList);

    List<Product> productDtoListToProductList(List<Product> productList);

}
