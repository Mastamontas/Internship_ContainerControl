package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS;


//DTO especifico para retornar informação completa de equipamentos e movimentos
//tem de retornar toda a informação do equipment info e do equipment movement
public class AggregatedInformationDTO {
    private EquipmentMovementDTO equipmentMovementDTO;
    private EquipmentInformationDTO equipmentInformationDTO;

    public AggregatedInformationDTO(EquipmentMovementDTO equipmentMovementDTO, EquipmentInformationDTO equipmentInformationDTO) {
        this.equipmentMovementDTO = equipmentMovementDTO;
        this.equipmentInformationDTO = equipmentInformationDTO;
    }

    //getters and setters
    public EquipmentMovementDTO getEquipmentMovementDTO() {
        return equipmentMovementDTO;
    }

    public void setEquipmentMovementDTO(EquipmentMovementDTO equipmentMovementDTO) {
        this.equipmentMovementDTO = equipmentMovementDTO;
    }

    public EquipmentInformationDTO getEquipmentInformationDTO() {
        return equipmentInformationDTO;
    }

    public void setEquipmentInformationDTO(EquipmentInformationDTO equipmentInformationDTO) {
        this.equipmentInformationDTO = equipmentInformationDTO;
    }
}
