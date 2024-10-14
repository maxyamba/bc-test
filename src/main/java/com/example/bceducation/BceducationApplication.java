package com.example.bceducation;

import com.example.bceducation.service.DepartmentService;
import com.example.bceducation.service.LectorService;
import com.example.bceducation.util.ExtractStringUtil;
import com.example.bceducation.util.StringConst;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.example.bceducation.util.ExtractStringUtil.extractDepartmentName;
import static com.example.bceducation.util.ExtractStringUtil.extractTemplate;
import static com.example.bceducation.util.StringConst.*;

@SpringBootApplication
@RequiredArgsConstructor
public class BceducationApplication implements CommandLineRunner {

  private final LectorService lectorService;
  private final DepartmentService departmentService;

  public static void main(String[] args) {
    SpringApplication.run(BceducationApplication.class, args);
  }

  @Override
  public void run(String... args) {
    String question;
    Scanner scanner = new Scanner(System.in);
    do {
      System.out.println("Print your question. \nTo display all departments print 'Display all departments'.\nTo quit print 'q'");
      question = scanner.nextLine();
      if (question.startsWith(WHO_IS_A_HEAD_OF_DEPARTMENT)) {
        System.out.println(lectorService.findHeadOfDepartment(extractDepartmentName(question,
            WHO_IS_A_HEAD_OF_DEPARTMENT)));
      } else if (question.startsWith(SHOW_THE_AVERAGE_SALARY_FOR_THE_DEPARTMENT)) {
        System.out.println(lectorService.findAverageSalaryByDepartment(extractDepartmentName(question,
            SHOW_THE_AVERAGE_SALARY_FOR_THE_DEPARTMENT)));
      } else if (question.startsWith(SHOW_COUNT_OF_EMPLOYEE_FOR)) {
        System.out.println(lectorService.countOfEmployeesPerDepartment(extractDepartmentName(question,
            SHOW_COUNT_OF_EMPLOYEE_FOR)));
      } else if (question.startsWith(GLOBAl_SEARCH_BY)) {
        System.out.println(lectorService.globalSearchByTemplate(extractTemplate(question)));
      } else if (question.startsWith(SHOW) && question.contains(STATISTICS)) {
        System.out.println(lectorService.showStatistics(extractDepartmentName(question, SHOW, STATISTICS)));
      } else if (question.startsWith(DISPLAY_ALL_DEPARTMENTS)) {
        System.out.println(departmentService.displayAll());
      }
    } while (!question.contains(Q));
  }
}
