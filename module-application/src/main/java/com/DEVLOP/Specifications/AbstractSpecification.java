package com.DEVLOP.Specifications;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//todo: adicionar consição de retorno apenas de entidades não apagadas (isDeleted = false)
public abstract class AbstractSpecification<T>  {
    protected Specification<T> BuildSpecification(Map<String, Object> filters){
        return ((root, query, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach((field, value)->{
                if (value != null){
                    predicates.add(criteriaBuilder.equal(root.get(field), value));
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
