package com.quizz.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {

  @Id
  @Column(name = "subject_id")
  private int id;

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
}
