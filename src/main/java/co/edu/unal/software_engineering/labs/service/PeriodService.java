package co.edu.unal.software_engineering.labs.service;

import org.springframework.stereotype.Service;

import co.edu.unal.software_engineering.labs.model.Period;
import co.edu.unal.software_engineering.labs.repository.PeriodRepository;

@Service
public class PeriodService{
    private final PeriodRepository periodRepository;

    public PeriodService(PeriodRepository periodRepository){
        this.periodRepository=periodRepository;

    }

    public Period findById(Integer Id){
        return this.periodRepository.findById(Id).orElse(null);
    }
    

}
