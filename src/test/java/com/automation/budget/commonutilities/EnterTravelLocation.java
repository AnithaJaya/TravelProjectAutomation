package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.automation.exception.InvalidLocatorRuntimeException;
import com.automation.exception.MandatoryFieldValueRuntimeException;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class EnterTravelLocation implements Task {
  private String pickUpLocation;
  
public EnterTravelLocation(String pickUpLocation) {
  this.pickUpLocation = pickUpLocation;
}

public static Performable on(String pickUpLocation) {
  return instrumented(EnterTravelLocation.class,pickUpLocation);
}

@Step("Enter Travel Location #pickUpLocation")
public <T extends Actor> void performAs(T actor){

	String pickUpLocationLocator       = "//div[@class='col-lg-12 res-inputFldFst ']//input[@id='PicLoc_value' and @placeholder ='Enter your pick-up location or zip code']";
	String pickUpLocationSelectLocator = "//div[@class='angucomplete-results']//div[@class='angucomplete-description']//span[contains(text(),'"+pickUpLocation+"')]";

	if(!pickUpLocation.trim().isEmpty())
	{
		
	try{
	 BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(pickUpLocationLocator)).sendKeys(pickUpLocation); 
      try{
        WebElement pickupLocationSelectElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(pickUpLocationSelectLocator));
        BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",pickupLocationSelectElement);
         }
      catch(NoSuchElementException e){
    	throw new InvalidLocatorRuntimeException("pickUpLocation- Selector");
         }
	   }
	catch(NoSuchElementException e){
		throw new InvalidLocatorRuntimeException("pickUp-Location");
	   }
	catch(Exception e){
		e.printStackTrace();
	   }
	}
	else {
		System.out.println("pickUpLocation Location is Mandatory");
		throw new MandatoryFieldValueRuntimeException("pickUp-Location");
		
	}
  }
}

