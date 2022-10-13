package com.quizz.app.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

  @Id
  @Column(name = "subject_id")
  private Integer id;

  @Column(name = "subject_name")
  private String name;

  @Column(name = "category")
  private String category;

  @Column(name = "duration")
  private Integer duration;

  @Column(name = "price")
  private Integer price;

  @Column(name = "sale_price")
  private Integer salePrice;

  @Column(name = "featured")
  private boolean featured;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  private String status;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Subject subject = (Subject) o;
    return id != null && Objects.equals(id, subject.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
