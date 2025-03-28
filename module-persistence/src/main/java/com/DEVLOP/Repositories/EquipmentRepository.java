package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Interfaces.IEquipmentClassRepository;
import com.DEVLOP.Interfaces.IEquipmentRepository;
import com.DEVLOP.Interfaces.IEquipmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentRepository  {
    private final IEquipmentRepository iEquipmentRepository;
    private final IEquipmentClassRepository iEquipmentClassRepository;
    private final IEquipmentTypeRepository iEquipmentTypeRepository;

    @Autowired
    public EquipmentRepository(IEquipmentRepository iEquipmentRepository,IEquipmentTypeRepository iEquipmentTypeRepository, IEquipmentClassRepository iEquipmentClassRepository){
        this.iEquipmentRepository = iEquipmentRepository;
        this.iEquipmentClassRepository = iEquipmentClassRepository;
        this.iEquipmentTypeRepository = iEquipmentTypeRepository;
    }

    /**
     * Method to return all valid equipment entities from the database
     * @return list of equipment entities
     */
    public List<Equipment> FindAll(){
        return iEquipmentRepository.findAll();
    }

    public Optional<Equipment> FindByID(int id){
        return iEquipmentRepository.findById(id);
    }


    public Equipment PersistEquipment(Equipment eq){
        iEquipmentRepository.save(eq);
        iEquipmentRepository.flush();
        return eq;
    }

    public void PersistEquipmentClass(EquipmentClass eqClass){
        iEquipmentClassRepository.save(eqClass);
        iEquipmentClassRepository.flush();
    }

    public void PersistEquipmentType(EquipmentType eqType){
        iEquipmentTypeRepository.save(eqType);
        iEquipmentTypeRepository.flush();
    }

    //tem de ser por id?
    public void UpdateEquipment(Equipment eq) {
        iEquipmentRepository.save(eq);
        iEquipmentRepository.flush();
    }

    /*
    todo
    delete all equipments should soft delete entities, not erase them from db
    imples tests should eventually return false to "isDeleted"
     */
    public void DeleteAllEquipments(){
        iEquipmentRepository.deleteAll();
        iEquipmentRepository.flush();

        iEquipmentTypeRepository.deleteAll();
        iEquipmentTypeRepository.flush();

        iEquipmentClassRepository.deleteAll();
        iEquipmentClassRepository.flush();
    }

    /*
    return equipments by filter. eventually replaces the method to return all equipments
     */
    public List<Equipment> ReturnEquipmentListFiltered(Specification<Equipment> spec){
        return iEquipmentRepository.findAll(spec);
    }


}

