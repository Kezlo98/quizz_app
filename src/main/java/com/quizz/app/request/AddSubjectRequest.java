package com.quizz.app.request;

import lombok.Data;

@Data
public class AddSubjectRequest {

  private String subjectName;
  private String category;
  private boolean featured;
  private String description;
  private String status;
}
