package com.example.bceducation.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lector")
@Builder
@Entity
public class Lector {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
  private UUID id;
  private String fullName;
  private BigDecimal salary;
  @Enumerated(EnumType.STRING)
  private LectorType lectorType;
  @OneToMany(mappedBy = "lector")
  private Set<LectorDepartment> lectorDepartments;
}
