package es.franricodev.shopping_list_gestor_service.shoppinglist.specifications;

import es.franricodev.shopping_list_gestor_service.shoppinglist.dto.RequestFilterShoppinglistDTO;
import es.franricodev.shopping_list_gestor_service.shoppinglist.model.Shoppinglist;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppinglistSpecifications {

    private static final String CREATION_DATE = "creationDate";
    private static final String TOTAL_PRICE = "totalPrice";
    private static final String CODE = "code";
    private static final String CLOSE_DATE = "closeDate";

    private ShoppinglistSpecifications() {
        throw new IllegalStateException("ShoppinglistSpecifications.class");
    }

    public static Specification<Shoppinglist> withFilter(final RequestFilterShoppinglistDTO filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicatesWithFilter(predicateList, root, criteriaBuilder, filter);
            return criteriaBuilder.and(predicateList.toArray(new Predicate[] {}));
        };
    }

    private static void predicatesWithFilter(
            List<Predicate> predicates,
            final Root<Shoppinglist> root,
            final CriteriaBuilder builder,
            final RequestFilterShoppinglistDTO filter) {
        if(!Objects.isNull(filter.getCode()) && !filter.getCode().isEmpty()) {
            predicates.add(builder.equal(builder.lower(root.get(CODE).as(String.class)), filter.getCode().toLowerCase()));
        }
        if(!Objects.isNull(filter.getTotalPrice())) {
            predicates.add(builder.equal(root.get(TOTAL_PRICE).as(Double.class), filter.getTotalPrice()));
        }
        if(!Objects.isNull(filter.getCreateDate()) && !filter.getCreateDate().isEmpty()) {
            predicates.add(builder.equal(root.get(CREATION_DATE).as(String.class), filter.getCreateDate()));
        }
        if(!Objects.isNull(filter.getCloseDate()) && !filter.getCloseDate().isEmpty()) {
            predicates.add(builder.equal(root.get(CLOSE_DATE).as(String.class), filter.getCloseDate()));
        }
    }

}
