package co.edu.unal.software_engineering.labs.service;

import org.springframework.stereotype.Service;

import co.edu.unal.software_engineering.labs.model.Association;
import co.edu.unal.software_engineering.labs.pojo.RegisterAssociationPOJO;
import co.edu.unal.software_engineering.labs.repository.AssociationRepository;

@Service
public class AssociationService {
    public final AssociationRepository associationRepository;

    public AssociationService(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    public void save(Association association) {
        this.associationRepository.save(association);
    }

    public boolean isRigth(RegisterAssociationPOJO associationPOJO) {
     try{
        boolean correctness = associationPOJO.getCourseId().compareTo(0) > 0
        && associationPOJO.getRoleId().compareTo(0) > 0 
       
        && associationPOJO.getPeriodId().compareTo(0) > 0;
        return correctness;
     }catch(Exception e){//this is for expception in case that the atrributes are not integer and there
         return false;//is an error cast
     }
        
        
    }

}
