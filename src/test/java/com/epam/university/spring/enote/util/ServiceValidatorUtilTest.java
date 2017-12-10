package com.epam.university.spring.enote.util;

import static org.junit.Assert.*;

import com.epam.university.spring.enote.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;

public class ServiceValidatorUtilTest {

  private Integer ID = 1234;

  @Test(expected = NotFoundException.class)
  public void exceptionCatchingTestValidateNotFoundBooleanString() {
    ServiceValidatorUtil.validateNotFound(false,"exception have catched");
  }

  @Test(expected = NotFoundException.class)
  public void exceptionCatchingWithIDTestValidateNotFoundWithIdBooleanInteger() {
    ServiceValidatorUtil.validateNotFoundWithId( false, ID);
  }

  @Test
  public void passingNotNullObjectValidateNotFoundParametrizedArgString() {
    ServiceValidatorUtil.validateNotFound(new Object(), "object passing");
  }

  @Test(expected = NotFoundException.class)
  public void passingNullObjectValidateNotFoundParametrizedArgString() {
    ServiceValidatorUtil.validateNotFound(null, "object passing");
  }


  @Test(expected = NotFoundException.class)
  public void passingNullWithIDValidateNotFoundWithIdParametrizedArgInteger() {
    ServiceValidatorUtil.validateNotFoundWithId(null, ID);
  }

}