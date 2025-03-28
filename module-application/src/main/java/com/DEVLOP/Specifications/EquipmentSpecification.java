package com.DEVLOP.Specifications;

import com.DEVLOP.Entities.Equipment;
import org.springframework.data.jpa.domain.Specification;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class EquipmentSpecification extends AbstractSpecification<Equipment> {
    @Override
    public Specification<Equipment> BuildSpecification(Map<String, Object> filters) {
        return super.BuildSpecification(filters);
    }
}
