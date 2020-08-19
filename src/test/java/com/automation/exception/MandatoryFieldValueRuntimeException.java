package com.automation.exception;

public class MandatoryFieldValueRuntimeException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  
  public MandatoryFieldValueRuntimeException(String fieldName)
  {
	  
	  super(fieldName + " is Mandatory " );
  }
}
