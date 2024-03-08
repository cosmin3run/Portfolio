package epicodeCapstone.portfolio.services;

import epicodeCapstone.portfolio.entities.Education;
import epicodeCapstone.portfolio.exceptions.NotFoundException;
import epicodeCapstone.portfolio.payloads.EducationDTO;
import epicodeCapstone.portfolio.repositories.EducationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EducationService {
    @Autowired
    EducationDAO educationDAO;

    public Page<Education> getAllEducation(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return educationDAO.findAll(pageable);
    }

    public Education getEducationById(UUID id){
        return educationDAO.findById(id).orElseThrow(() -> new NotFoundException("educazione con id '"+id+"' non trovata"));
    }

    public Education saveEducation(EducationDTO payload){
        Education education = new Education();
        education.setTrainingInstitution(payload.trainingInstitution());
        education.setDescription(payload.description());
        education.setEducationLevel(payload.educationLevel());
        education.setGraduationDate(payload.graduationDate());
        education.setUserInfo(payload.userInfo());
        return educationDAO.save(education);
    }

    public Education updateEducationById(UUID id, EducationDTO payload){
        Education education = educationDAO.findById(id).orElseThrow(()->new NotFoundException("Education non trovata."));
        education.setTrainingInstitution(payload.trainingInstitution());
        education.setDescription(payload.description());
        education.setEducationLevel(payload.educationLevel());
        education.setGraduationDate(payload.graduationDate());
        education.setUserInfo(payload.userInfo());
        return educationDAO.save(education);
    }

    public void deleteEducationById(UUID id){
        Education found = educationDAO.findById(id).orElseThrow(()->new NotFoundException("Education non trovata."));
        educationDAO.delete(found);
    }




}
