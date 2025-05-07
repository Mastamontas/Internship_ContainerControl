package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Interfaces.IEquipmentClassRepo;
import com.DEVLOP.Interfaces.IEquipmentRepo;
import com.DEVLOP.Interfaces.IEquipmentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//todo: passar metodos de equipment class e equipment type para os repositorios especificos
@Repository
public class EquipmentRepo {
    private final IEquipmentRepo iEquipmentRepo;
    private final IEquipmentClassRepo iEquipmentClassRepo;
    private final IEquipmentTypeRepo iEquipmentTypeRepo;

    @Autowired
    public EquipmentRepo(IEquipmentRepo iEquipmentRepo, IEquipmentTypeRepo iEquipmentType, IEquipmentClassRepo iEquipmentClass){
        this.iEquipmentRepo = iEquipmentRepo;
        this.iEquipmentClassRepo = iEquipmentClass;
        this.iEquipmentTypeRepo = iEquipmentType;
    }

    /**
     * Method to return all valid equipment entities from the database
     * @return list of equipment entities
     */
    public List<Equipment> FindAll(){
        return iEquipmentRepo.findAll();
    }

    public Optional<Equipment> FindByID(int id){
        return iEquipmentRepo.findById(id);
    }


    public Equipment PersistEquipment(Equipment eq){
        iEquipmentRepo.save(eq);
        iEquipmentRepo.flush();
        return eq;
    }

    public void PersistEquipmentClass(EquipmentClass eqClass){
        iEquipmentClassRepo.save(eqClass);
        iEquipmentClassRepo.flush();
    }

    public void PersistEquipmentType(EquipmentType eqType){
        iEquipmentTypeRepo.save(eqType);
        iEquipmentTypeRepo.flush();
    }

    //tem de ser por id?
    public void UpdateEquipment(Equipment eq) {
        iEquipmentRepo.save(eq);
        iEquipmentRepo.flush();
    }

    /*
    todo
    delete all equipments should soft delete entities, not erase them from db
    imples tests should eventually return false to "isDeleted"
     */
    public void DeleteAllEquipments(){
        iEquipmentRepo.deleteAll();
        iEquipmentRepo.flush();

        iEquipmentTypeRepo.deleteAll();
        iEquipmentTypeRepo.flush();

        iEquipmentClassRepo.deleteAll();
        iEquipmentClassRepo.flush();
    }

    /*
    return equipments by filter. eventually replaces the method to return all equipments
     */
    public List<Equipment> ReturnEquipmentListFiltered(Specification<Equipment> spec){
        return iEquipmentRepo.findAll(spec);
    }
    public List<Equipment> GetEquipmentListFromID(List<Integer> idList){
        return iEquipmentRepo.findAllById(idList);
    }


}

