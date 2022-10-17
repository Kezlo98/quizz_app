package com.quizz.app.request;

import lombok.Data;

@Data
public class SubjectDto {

  private Integer id;
  private String subjectName;
  private String category;
  private boolean featured;
  private String description;
  private String status;
  private Integer duration;
  private Integer price;
  private Integer salePrice;
}
