package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Interfaces.IEquipmentClass;
import com.DEVLOP.Interfaces.IEquipment;
import com.DEVLOP.Interfaces.IEquipmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Equipment {
    private final IEquipment iEquipment;
    private final IEquipmentClass iEquipmentClass;
    private final IEquipmentType iEquipmentType;

    @Autowired
    public Equipment(IEquipment iEquipment, IEquipmentType iEquipmentType, IEquipmentClass iEquipmentClass){
        this.iEquipment = iEquipment;
        this.iEquipmentClass = iEquipmentClass;
        this.iEquipmentType = iEquipmentType;
    }

    /**
     * Method to return all valid equipment entities from the database
     * @return list of equipment entities
     */
    public List<com.DEVLOP.Entities.Equipment> FindAll(){
        return iEquipment.findAll();
    }

    public Optional<com.DEVLOP.Entities.Equipment> FindByID(int id){
        return iEquipment.findById(id);
    }


    public com.DEVLOP.Entities.Equipment PersistEquipment(com.DEVLOP.Entities.Equipment eq){
        iEquipment.save(eq);
        iEquipment.flush();
        return eq;
    }

    public void PersistEquipmentClass(EquipmentClass eqClass){
        iEquipmentClass.save(eqClass);
        iEquipmentClass.flush();
    }

    public void PersistEquipmentType(EquipmentType eqType){
        iEquipmentType.save(eqType);
        iEquipmentType.flush();
    }

    //tem de ser por id?
    public void UpdateEquipment(com.DEVLOP.Entities.Equipment eq) {
        iEquipment.save(eq);
        iEquipment.flush();
    }

    /*
    todo
    delete all equipments should soft delete entities, not erase them from db
    imples tests should eventually return false to "isDeleted"
     */
    public void DeleteAllEquipments(){
        iEquipment.deleteAll();
        iEquipment.flush();

        iEquipmentType.deleteAll();
        iEquipmentType.flush();

        iEquipmentClass.deleteAll();
        iEquipmentClass.flush();
    }

    /*
    return equipments by filter. eventually replaces the method to return all equipments
     */
    public List<com.DEVLOP.Entities.Equipment> ReturnEquipmentListFiltered(Specification<com.DEVLOP.Entities.Equipment> spec){
        return iEquipment.findAll(spec);
    }
    public List<com.DEVLOP.Entities.Equipment> GetEquipmentListFromID(List<Integer> idList){
        return iEquipment.findAllById(idList);
    }


}

