package com.example.bceducation;

import com.example.bceducation.model.Department;
import com.example.bceducation.repository.DepartmentRepository;
import com.example.bceducation.service.DepartmentService;
import com.example.bceducation.service.LectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {

  @Mock
  private DepartmentRepository departmentRepository;

  @InjectMocks
  private DepartmentService departmentService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testDisplayAllDepartments() {
    Department department1 = new Department();
    department1.setName("Math");
    Department department2 = new Department();
    department2.setName("Geo");
    Department department3 = new Department();
    department3.setName("Music");
    Department department4 = new Department();
    department4.setName("Computer Science");
    List<Department> departments = List.of(department1, department2, department3, department4);

    when(departmentRepository.findAll()).thenReturn(departments);
    String result = departmentService.displayAll();

    assertTrue(result.contains(department1.getName()));
    assertTrue(result.contains(department2.getName()));
    assertTrue(result.contains(department3.getName()));
    assertTrue(result.contains(department4.getName()));
    assertFalse(result.contains(department4.getName() + "a"));
  }

  @Test
  void testIsDepartmentFoundByName_whenDepartmentIsFound() {
    String departmentName = "Math";
    Department department1 = new Department();
    department1.setName("Math");

    when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(department1));

    assertTrue(departmentService.isDepartmentFoundByName(departmentName));
  }

  @Test
  void testIsDepartmentFoundByName_whenDepartmentIsNotFound() {
    String departmentName = "Math";

    when(departmentRepository.findByName(departmentName)).thenReturn(Optional.empty());

    assertFalse(departmentService.isDepartmentFoundByName(departmentName));
  }
}
