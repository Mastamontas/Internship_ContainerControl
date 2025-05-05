package com.DEVLOP.Specifications;

import com.DEVLOP.Entities.Movement;
import jakarta.persistence.criteria.Path;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import jakarta.persistence.criteria.Predicate;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Component
public class MovementSpecification extends AbstractSpecification<Movement> {

    private static final List<String> ALLOWED_FIELDS = Arrays.asList(
            "equipment.prefix","equipment.number", "equipmentType.equipmentTypeCode","equipmentType.equipmentTypeLength", "equipmentStatus.equipmentStatusCode"
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

    public Specification<Movement> SpecificationBetween (Map<String, Object> fromFilter, Map<String, Object> toFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Between dates
            if (fromFilter.containsKey("date") && toFilter.containsKey("date")) {
                LocalDate fromDate = (LocalDate) fromFilter.get("date");
                LocalDate toDate = (LocalDate) toFilter.get("date");
                predicates.add(criteriaBuilder.between(root.get("date"), fromDate, toDate));
            }

            // Additional fields - assume same keys in from/to for simplicity
            for (String key : fromFilter.keySet()) {
                if (key.equals("date")) continue; // Already handled

                Object fromValue = fromFilter.get(key);
                Object toValue = toFilter.get(key);

                if (fromValue != null && toValue != null) {
                    predicates.add(criteriaBuilder.between(root.get(key), (Comparable) fromValue, (Comparable) toValue));
                } else if (fromValue != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key), (Comparable) fromValue));
                } else if (toValue != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(key), (Comparable) toValue));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
