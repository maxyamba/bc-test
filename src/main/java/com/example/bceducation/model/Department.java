package com.example.bceducation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
@Builder
@Entity
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
  private UUID id;
  private String name;
  @OneToMany(mappedBy = "department")
  private Set<LectorDepartment> lectorDepartments;
}
