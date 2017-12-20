package com.epam.university.spring.enote;

import com.epam.university.spring.enote.util.DateConverter;
import java.time.LocalDate;

public class Main {

  /*    public static void main(String[] args) {
          //Fast test if app loading ok
          ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext(
                  "spring/spring-app.xml");
      }*/
  public static void main(String[] args) {
    DateConverter dt = new DateConverter();
    System.out.println(dt.convertToDatabaseColumn(LocalDate.now()).toString());
  }
}
