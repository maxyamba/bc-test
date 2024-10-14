package com.example.bceducation;

import com.example.bceducation.model.Lector;
import com.example.bceducation.model.LectorType;
import com.example.bceducation.repository.LectorRepository;
import com.example.bceducation.service.DepartmentService;
import com.example.bceducation.service.LectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class LectorServiceTest {

  @Mock
  private LectorRepository lectorRepository;

  @Mock
  private DepartmentService departmentService;

  @InjectMocks
  private LectorService lectorService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testFindHeadOfDepartment_whenDepartmentFound() {
    String departmentName = "Computer Science";
    Lector headLector = new Lector();
    headLector.setFullName("Dr. Smith");

    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(true);
    when(lectorRepository.findHeadOfDepartment(departmentName)).thenReturn(Optional.of(headLector));

    String result = lectorService.findHeadOfDepartment(departmentName);

    assertEquals("Head of Computer Science department is Dr. Smith", result);
  }

  @Test
  public void testFindHeadOfDepartment_whenDepartmentNotFound() {
    String departmentName = "Mathematics";

    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(false);
    when(lectorRepository.findHeadOfDepartment(departmentName)).thenReturn(Optional.empty());

    String result = lectorService.findHeadOfDepartment(departmentName);

    assertEquals("Department is not found by name " + departmentName, result);
  }

  @Test
  public void testFindHeadOfDepartment_whenLectorNotFound() {
    String departmentName = "Mathematics";

    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(true);
    when(lectorRepository.findHeadOfDepartment(departmentName)).thenReturn(Optional.empty());

    String result = lectorService.findHeadOfDepartment(departmentName);

    assertEquals("Head of department is not found", result);
  }

  @Test
  public void testShowStatistics() {
    String departmentName = "Physics";
    Lector lector1 = new Lector();
    lector1.setLectorType(LectorType.ASSISTANT);
    Lector lector2 = new Lector();
    lector2.setLectorType(LectorType.PROFESSOR);
    Lector lector3 = new Lector();
    lector3.setLectorType(LectorType.PROFESSOR);
    Lector lector4 = new Lector();
    lector4.setLectorType(LectorType.ASSOCIATE_PROFESSOR);
    List<Lector> lectors = List.of(lector1, lector2, lector3, lector4);

    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(true);
    when(lectorRepository.findByDepartment(departmentName)).thenReturn(lectors);

    String result = lectorService.showStatistics(departmentName);

    assertTrue(result.contains("assistants - 1"));
    assertTrue(result.contains("professors - 2"));
    assertTrue(result.contains("associate professors - 1"));
  }

  @Test
  public void testCountOfEmployeesPerDepartment() {
    String departmentName = "History";
    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(true);
    when(lectorRepository.findByDepartment(departmentName)).thenReturn(List.of(new Lector(), new Lector()));

    String result = lectorService.countOfEmployeesPerDepartment(departmentName);

    assertEquals("2", result);
  }

  @Test
  public void testFindAverageSalaryByDepartment() {
    String departmentName = "Biology";
    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(true);
    when(lectorRepository.findAverageSalaryByDepartment(departmentName)).thenReturn(50000.0);

    String result = lectorService.findAverageSalaryByDepartment(departmentName);

    assertEquals("50000.0", result);
  }

  @Test
  public void testGlobalSearchByTemplate() {
    String template = "searchTerm";
    when(lectorRepository.globalSearchByTemplate(template)).thenReturn(List.of("Lector A", "Lector B"));

    String result = lectorService.globalSearchByTemplate(template);

    assertTrue(result.contains("Lector A"));
    assertTrue(result.contains("Lector B"));
  }

  @Test
  public void testFindHeadOfDepartment_whenDepartmentNotFoundByName() {
    String departmentName = "Biochemistry";
    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(false);

    String result = lectorService.findHeadOfDepartment(departmentName);

    assertEquals("Department is not found by name Biochemistry", result);
  }

  @Test
  public void testShowStatistics_whenDepartmentNotFoundByName() {
    String departmentName = "Geography";
    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(false);

    String result = lectorService.showStatistics(departmentName);

    assertEquals("Department is not found by name Geography", result);
  }

  @Test
  public void testCountOfEmployeesPerDepartment_whenDepartmentNotFoundByName() {
    String departmentName = "Engineering";
    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(false);

    String result = lectorService.countOfEmployeesPerDepartment(departmentName);

    assertEquals("Department is not found by name Engineering", result);
  }

  @Test
  public void testFindAverageSalaryByDepartment_whenDepartmentNotFoundByName() {
    String departmentName = "Psychology";
    when(departmentService.isDepartmentFoundByName(departmentName)).thenReturn(false);

    String result = lectorService.findAverageSalaryByDepartment(departmentName);

    assertEquals("Department is not found by name Psychology", result);
  }
}
