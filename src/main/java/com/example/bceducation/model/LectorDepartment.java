package com.example.bceducation.model;

import com.example.bceducation.model.embedded.LectorDepartmentKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lector_department")
@Getter
@Setter
public class LectorDepartment {
  @EmbeddedId
  private LectorDepartmentKey id;

  @ManyToOne
  @MapsId("departmentId")
  @JoinColumn(name = "department_id", columnDefinition = "VARCHAR(36)")
  private Department department;

  @ManyToOne
  @MapsId("lectorId")
  @JoinColumn(name = "lector_id", columnDefinition = "VARCHAR(36)")
  private Lector lector;

  @Column(name = "is_head", nullable = false, columnDefinition = "bool default false")
  private Boolean isHead;
}
