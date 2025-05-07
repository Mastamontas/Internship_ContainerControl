package com.DEVLOP.Application.Mappers;


import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Entities.Movement;
import jakarta.persistence.EntityManager;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface IMovementMapper {

    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="movementType.movementTypeCode", target="movementTypeCode")
    //@Mapping(source="movementType.isEmpty", target="isEmpty")
    @Mapping(source="movementComment", target="comments")
    @Mapping(source="transportResponsibility", target="transportResponsibility")
    //equipment maps
    @Mapping(source="equipment.id", target="equipmentId")
    @Mapping(source ="equipment.prefix", target="prefix")
    @Mapping(source="equipment.number", target="number")
    @Mapping(source="equipment.checkDigit", target="checkDigit")
    //equipment service maps
    @Mapping(source="equipmentService.equipmentServiceCode", target="equipmentServiceCode")
    //equipment type maps
    @Mapping(source="equipmentType.equipmentTypeCode", target = "equipmentTypeCode")
    @Mapping(source="equipmentType.equipmentTypeLength", target= "equipmentTypeLength")
    @Mapping(source = "equipmentCondition.physicalConditionCode", target="physicalConditionCode")
    MovementDto MapToMovementDto(Movement movement);


    //---------------------------------------//
    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="movementStatus", target="movementStatus")
    //@Mapping(source="movementType.isEmpty", target="isEmpty")
    @Mapping(source="comments", target="movementComment")
    @Mapping(source="accessUserId", target="accessUserID")
    @Mapping(source="businessUnitId", target="businessUnitID")
    @Mapping(source="transportResponsibility", target="transportResponsibility")
    @Mapping(source="movementOfHire", target="movementOfHire")
    @Mapping(source="movementRestitutionCode", target="movementRestitutionCode")
    @Mapping(source="movementVoyageId", target="movementVoyageID")
    @Mapping(source="equipmentOwnerId", target="equipmentOwnerID")
    @Mapping(source="movementDays", target="movementDays")
    @Mapping(source="movementLast", target="movementLast")
    @Mapping(source="shipmentUCN", target="shipmentUCN")
    @Mapping(source="movementTransport", target="movementTransport")
    @Mapping(source = "transportMeansComment", target ="transportMeans.comment")
    //equipment status
    //equipment status id
    @Mapping(source="equipmentStatusId", target="equipmentStatus.id")
    @Mapping(source ="equipmentStatusCode", target="equipmentStatus.equipmentStatusCode")
    @Mapping(source ="equipmentStatusName", target="equipmentStatus.equipmentStatusName")
    @Mapping(source ="equipmentStatusLevel1", target="equipmentStatus.equipmentStatusLevel1")
    @Mapping(source ="equipmentStatusLevel2", target="equipmentStatus.equipmentStatusLevel2")
    //equipment condition
    //equipment condition id
    @Mapping(source="equipmentConditionId", target="equipmentCondition.id")
    @Mapping(source = "physicalConditionCode", target="equipmentCondition.physicalConditionCode")
    @Mapping(source ="physicalConditionName", target="equipmentCondition.physicalConditionName")
    @Mapping(source ="physicalConditionType", target="equipmentCondition.physicalConditionType")
    //equipment service
    //equipment service id
    @Mapping(source="equipmentServiceId",target = "equipmentService.id")
    @Mapping(source ="equipmentServiceCode", target="equipmentService.equipmentServiceCode")
    @Mapping(source ="equipmentServiceName", target="equipmentService.equipmentServiceName")
    //movement type
    //movement type id
    @Mapping(source= "movementTypeId", target ="movementType.id")
    @Mapping(source="movementTypeCode", target="movementType.movementTypeCode")
    @Mapping(source="movementTypeName", target="movementType.movementTypeName")
    //equipment leasing
    @Mapping(source="equipmentLeasingId", target="equipmentLeasing.id")
    @Mapping(source="equipmentLeasingCode", target="equipmentLeasing.leasingContractCode")
    @Mapping(source="equipmentLeasingName", target="equipmentLeasing.leasingContractName")
    //equipment
    @Mapping(source="equipmentId", target ="equipment.id")
    @Mapping(source ="prefix", target="equipment.prefix")
    @Mapping(source="number", target="equipment.number")
    @Mapping(source="checkDigit", target="equipment.checkDigit")
    //equipment type
    @Mapping(source = "equipmentTypeId", target = "equipmentType.id")
    @Mapping(source="equipmentTypeCode", target = "equipmentType.equipmentTypeCode")
    @Mapping(source="equipmentTypeLength", target= "equipmentType.equipmentTypeLength")
    Movement MapToMovementEntity(MovementDto movementDto);

    //---------------------------------------------------------------//
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="movementTypeCode", target="movementType.movementTypeCode")
    //@Mapping(source="movementType.isEmpty", target="isEmpty")
    @Mapping(source="comments", target="movementComment")
    @Mapping(source="transportResponsibility", target="transportResponsibility")
    //equipment maps
    @Mapping(source="equipmentId", target ="equipment.id")
    @Mapping(source ="prefix", target="equipment.prefix")
    @Mapping(source="number", target="equipment.number")
    @Mapping(source="checkDigit", target="equipment.checkDigit")
    //equipment service maps
    @Mapping(source="equipmentServiceCode", target="equipmentService.equipmentServiceCode")
    //equipment type maps
    @Mapping(source="equipmentTypeCode", target = "equipmentType.equipmentTypeCode")
    @Mapping(source="equipmentTypeLength", target= "equipmentType.equipmentTypeLength")
    @Mapping(source = "physicalConditionCode", target="equipmentCondition.physicalConditionCode")
    Movement UpdateMovementEntity(MovementDto moveDto, @MappingTarget Movement move);
    //----named mappings

}
