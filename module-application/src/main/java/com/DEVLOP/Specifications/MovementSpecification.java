package com.DEVLOP.Specifications;

import com.DEVLOP.Entities.Movement;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class MovementSpecification extends AbstractSpecification<Movement> {
    public Specification<Movement> GetMovementSpecification(Map<String,Object> filters){
        return super.BuildSpecification(filters);
    }
}
