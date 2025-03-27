package com.DEVLOP.Specifications;

import com.DEVLOP.Entities.Movement;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.Predicate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Component
public class MovementSpecification extends AbstractSpecification<Movement> {

    private static final List<String> ALLOWED_FIELDS = Arrays.asList(
            "equipment.prefix","equipment.number", "equipmentType.equipmentTypeCode","equipmentType.equipmentTypeLength"
    );

    @Override
    public Specification<Movement> BuildSpecification(Map<String,Object> filters){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            filters.forEach((field, value) -> {
                if (value != null && ALLOWED_FIELDS.contains(field)) {
                    String[] fieldParts = field.split("\\."); // Split by "." for nested fields

                    if (fieldParts.length == 2) {
                        // Handling "equipment.prefix" or "equipmentType.name"
                        Path<Object> path = root.get(fieldParts[0]).get(fieldParts[1]);
                        predicates.add(criteriaBuilder.equal(path, value));
                    } else if (fieldParts.length == 1) {
                        // Handling normal fields like "status"
                        predicates.add(criteriaBuilder.equal(root.get(field), value));
                    }
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
