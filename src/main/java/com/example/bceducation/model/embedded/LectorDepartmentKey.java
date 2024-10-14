package com.example.bceducation.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectorDepartmentKey implements Serializable {
  @Column(name = "department_id")
  private UUID departmentId;
  @Column(name = "lector_id")
  private UUID lectorId;

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    LectorDepartmentKey that = (LectorDepartmentKey) o;
    return departmentId != null && Objects.equals(departmentId, that.departmentId)
        && lectorId != null && Objects.equals(lectorId, that.lectorId);
  }

  @Override
  public final int hashCode() {
    return Objects.hash(departmentId, lectorId);
  }
}
