package com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence;

import com.devlop.devlop_containercontrol_project.Domain.Equipment;
import com.devlop.devlop_containercontrol_project.Domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * repositorio faz as operaçoes CRUD com a base de dados
 * extende a interface JpaRepository que contem os metodos crud default
 * queries mais complexas usam a tag @Query
 */

@Repository
public interface
IEquipmentQueries extends JpaRepository <Equipment, Integer> {
/*
 * QUERY 1 CONTAINER INFORMATION
 * função: vai buscar a lista de entidades e retorna automaticamente para DTO com os detalhes necessários
 * também tem de retornar todos os movimentos associados a este container
 * */

  @Query("SELECT \n" +
          "e.prefix AS prefix,\n" +
          "e.checkDigit AS checkDigit,\n" +
          "e.number AS number,\n" +
          "e.insideHeight AS insideHeight,\n" +
          "e.grossWeight AS grossWeight,\n" +
          "e.payload AS payload,\n" +
          "e.equipmentTareWeight AS tareWeight,\n" +
          "e.insideLength AS insideLength,\n" +
          "e.insideCubic AS insideCubic,\n" +
          "e.yearOfManufacture AS yearOfManufacture,\n" +
          "e.comment AS comment,\n" +
          "eType.id AS typeID,\n" +
          "eType.equipmentTypeName AS equipmentTypeName,\n" +
          "eType.equipmentTypeCode AS typeCode,\n" +
          "eType.equipmentTypeLength AS typeLength,\n" +
          "eType.equipmentTypeTareWeight AS typeTare\n" +
          "FROM \n" +
          "Equipment e \n" +
          "JOIN\n" +
          "EquipmentType eType")
  List<Equipment> getEquipmentInformation(); //retorna todos os equipamentos

/**
 * só é retornado quando se clica num container que foi retornado acima.
 * m-movement
 * t-transport
 * l-location
 * c-companies
 * bp-bookingProcess
 * cu-customs
 * a-audit
 * d-depot
 *
 * @Query (por ID/prefixo do equipamento) retorna:
 * FAZER DUPLA VERIFICAÇÂO DE NOMENCLATURA AQUI DEFINIDA
 * SELECT:
 *m.key AS movementKey,
 * m.code AS movementCode,
 * m.status AS movementStatus,
 * m.date AS movementDate,
 * m.time AS movementTime,
 * m.Service.code AS equipmentServiceCode,
 * m.ConditionCode AS conditionCode,
 * m.bound AS movementBound (enum? Dropdown),
 * m.code AS leasingCode
 * m.transhipmentCode AS transhipmentCode (boolean),
 * m.empty AS empty, (boolean)
 * m.shippingCode AS shippingCode,
 * m.sealNumberA AS sealNumberA,
 * m.sealNumberB AS sealNumberB,
 * m.comments AS comments,
 * t.details AS transportDetails,
 * t.releaseReference AS releaseReference,
 * t.response AS transportResponse (dropdown),
 * t.meansCode AS transportMeansCode,
 * t.haulier AS haulier,
 * t.vehicleReference AS vehicleReference,
 * l.from AS from,
 * l.fromName AS fromName,
 * l.to AS to,
 * l.toName AS toName,
 * l.final AS final,
 * l.finalName AS finalName,
 * c.line AS line,
 * c.equipmentOwnerCode AS equipmentOwnerCode,
 * c.offHire AS offHire,
 * c.agentCode AS agentCode,
 * c.clientCode AS clientCode,
 * c.shipperCode AS shipperCode,
 * c.consigneeCode AS consigneeCode,
 * bp.transportCompany AS transportCompany,
 * bp.vessel AS vessel,
 * bp.voyage AS voyage,
 * bp.transportBound AS transportBound,
 * bp.transportService AS transportService,
 * bp.bookingProcess AS bookingProcess,
 * bp.billOfLandingNumber AS billOfLandingNumber,
 * bp.bookingReference AS bookingReference,
 * bp.UCN AS UCN,
 * bp.numberOfPacks AS numberOfPacks,
 * bp.netWeight AS netWeight,
 * bp.grossWeight AS grossWeight,
 * bp.temperature AS temperature,
 * bp.temperatureUnit AS temperatureUnit (dropdown, suponho que seja um enum para Kelvin ou Celsius),
 * bp.commodityCode AS commodityCode,
 * bp.commodityName AS commodityName,
 * bp.commodityExpireDate AS commodityExpireDate,
 * bp.equipmentDescription AS equipmentDescription,
 * cu.goodsExportDeclaration AS goodsExportDeclaration,
 * cu.customsDispatchDocument AS customsDispatchDocument,
 * cu.goodsValue AS goodsValue,
 * cu.situation AS situation,
 * cu.customSeal AS customSeal,
 * a.userID AS userID,
 * a.date AS date,
 * a.time AS time
 * d.depotCode AS depotCode,
 * d.areaCode AS areaCode,
 * d.locationCode AS locationCode,
 * d.street AS street,
 * d.row AS row,
 * d.stack AS stack,
 * --COMMENT--> we need to get these values from the movement foreign key in the equipment table
 * FROM
 * movement m
 * JOIN
 * transport t ON m.transportID = t.ID
 * JOIN
 * location l ON m.locationID = l.ID
 * JOIN
 * companies c ON m.companiesID = c.ID
 * JOIN
 * bookingProcess bp ON m.bookingProcessID = bp.ID
 * LEFT JOIN
 * customs cu ON bp.customsID = cu.ID (vem de booking ID?)
 * LEFT JOIN
 * clause cl ON bp.clauseID = cl.ID
 * LEFT JOIN
 * audit a ON bp.auditID = a.ID
 * WHERE
 * m.equipmentID = ID;
 *
 * public List<Movement> getEquipmentMovementInformation(int ID){
 *     return List<Movement>;
 * };
 */

/**
 * esta query retorna os movimentos de um equipamento através de um ID e uma data (movement date)
 * Objetivo é retornar o movimento especifico e os dados das tabelas acessórias quando se carrega na data
 * @return
 */

/*@Query("SELECT m from Movement m where m.equipmentID = :ID AND m.movementDate = :date")
Movement findMovementByIDAndDate (@Param("id") Integer equipmentID, @Param("date")Timestamp movementDate);*/

//verificação de existencia da entidade- não precisa de estar aqui porque vem pelo repositorio
boolean existsById(Integer id);
}
