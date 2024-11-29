package com.devlop.devlop_containercontrol_project.ContainerMovements.Persistence;

import com.devlop.devlop_containercontrol_project.Domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repositorio faz as operaçoes CRUD com a base de dados
 * extende a interface JpaRepository que contem os metodos crud default
 * queries mais complexas usam a tag @Query
 */

@Repository
public interface IContainerMovementQueries extends JpaRepository <Equipment, Integer> {
/**
 * QUERY 1 CONTAINER INFORMATION
 * função: vai buscar a lista de entidades e retorna automaticamente para DTO com os detalhes necessários
 * também tem de retornar todos os movimentos associados a este container
 * @Query
 * SELECT (
 * e.prefix, (equipment) as prefix
 * e.number, AS number
 * e.insideHeight AS insideHeight,
 * e.grossWeight AS grossWeight,
 * e.payload AS payload,
 * e.tareWeight AS tareWeight,
 * e.insideLength AS insideLength,
 * e.insideCubic AS insideCubic,
 * e.yearOfManufacture AS yearOfManufacture,
 * e.comment AS comment,
 * eType.ID AS typeID, (equipmentType)
 * eType.equipmentType AS equipmentType,
 * eType.code AS typeCode,
 * eType.length AS typeLength,
 * eType.tare AS typeTare,
 * eLine.code AS lineCode, (equipmentLine)
 * eLine.name AS lineName,
 * eLeasing.ownerName AS equipmentOwner, (equipmentLeasing),
 *
 *FROM
 * equipment e
 * JOIN
 *  equipmentType eType ON e.equipmentTypeID = eType.ID
 * JOIN
 *  equipmentLine eLine ON e.lineID = eLine.ID
 * JOIN
 *  equipmentLeasing eLeasing ON e.ownerID = eLeasing.ID)
 *
 *  List<Equipment> getEquipmentInformation(); //retorna todos os equipamentos
 */

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
}
