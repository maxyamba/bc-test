package com.example.bceducation.service;

import com.example.bceducation.model.Department;
import com.example.bceducation.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
  private final DepartmentRepository departmentRepository;

  public String displayAll() {
    return String.join("\n",  departmentRepository.findAll().stream().map(Department::getName).toList());
  }

  public boolean isDepartmentFoundByName(String departmentName) {
    Optional<Department> department = departmentRepository.findByName(departmentName);
    return department.isPresent();
  }
}
