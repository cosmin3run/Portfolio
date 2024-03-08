package epicodeCapstone.portfolio.controllers;

import epicodeCapstone.portfolio.entities.Education;
import epicodeCapstone.portfolio.payloads.EducationDTO;
import epicodeCapstone.portfolio.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/education")
public class EducationController {

    @Autowired
    EducationService educationService;


    @GetMapping
    public Page<Education> getEducations(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "graduationDate") String sort){
        return educationService.getAllEducation(page, size, sort);
    }

    @GetMapping("/{id}")
    public Education getEducationById(UUID id){
        return educationService.getEducationById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Education createEducation(@RequestBody @Validated EducationDTO payload){
        return educationService.saveEducation(payload);
    }

    @PostMapping("/{id}")
    Education updateEducationById(@PathVariable UUID id, @RequestBody EducationDTO payload){
        return educationService.updateEducationById(id,payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEducationById(@PathVariable UUID id){
        educationService.deleteEducationById(id);
    }


}
