package com.automation.exception;

public class InvalidLocatorRuntimeException extends RuntimeException
{
  private static final long serialVersionUID = 1L;

  
  public InvalidLocatorRuntimeException(String locatorName)
  {
	  
	  super("Invalid Locator : "+locatorName );
  }
}
