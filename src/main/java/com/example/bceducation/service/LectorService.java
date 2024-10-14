package com.example.bceducation.service;

import com.example.bceducation.model.Lector;
import com.example.bceducation.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectorService {
  private final LectorRepository lectorRepository;
  private final DepartmentService departmentService;

  public String findHeadOfDepartment(String departmentName) {
    if (!departmentService.isDepartmentFoundByName(departmentName)) {
      return String.format("Department is not found by name %s", departmentName);
    }
    Optional<Lector> lector = lectorRepository.findHeadOfDepartment(departmentName);
    return lector.isPresent() ?
        String.format("Head of %s department is %s", departmentName, lector.get().getFullName()) :
        "Head of department is not found";
  }

  public String showStatistics(String departmentName) {
    if (!departmentService.isDepartmentFoundByName(departmentName)) {
      return String.format("Department is not found by name %s", departmentName);
    }
    List<Lector> lectors = lectorRepository.findByDepartment(departmentName);
    int assistants = 0;
    int associateProfessors = 0;
    int professors = 0;

    for (Lector lector : lectors) {
      switch (lector.getLectorType()) {
        case ASSISTANT -> assistants++;
        case ASSOCIATE_PROFESSOR -> associateProfessors++;
        case PROFESSOR -> professors++;
      }
    }

    return String.format("assistants - %d \n" +
        "associate professors - %d \n" +
        "professors - %d \n", assistants, associateProfessors, professors);
  }

  public String countOfEmployeesPerDepartment(String departmentName) {
    if (!departmentService.isDepartmentFoundByName(departmentName)) {
      return String.format("Department is not found by name %s", departmentName);
    }
    return String.valueOf(lectorRepository.findByDepartment(departmentName).size());
  }

  public String findAverageSalaryByDepartment(String departmentName) {
    if (!departmentService.isDepartmentFoundByName(departmentName)) {
      return String.format("Department is not found by name %s", departmentName);
    }
    return String.valueOf(lectorRepository.findAverageSalaryByDepartment(departmentName));
  }

  public String globalSearchByTemplate(String template) {
    return String.join("\n", lectorRepository.globalSearchByTemplate(template));
  }
}
