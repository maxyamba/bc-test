package com.example.bceducation.util;

public class ExtractStringUtil {
  public static String extractDepartmentName(String question, String... keywords) {
    for (String keyword : keywords) {
      question = question.replace(keyword, "").trim();
    }
    return question;
  }

  public static String extractTemplate(String question) {
    return question.substring(question.indexOf("by") + 3).trim();
  }
}
