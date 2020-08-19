package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.openqa.selenium.NoSuchElementException;
import com.automation.exception.InvalidLocatorRuntimeException;
import com.automation.exception.MandatoryFieldValueRuntimeException;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class ValidateVehicleDoorInformation implements Task {
  private String noOfDoors;
  

public ValidateVehicleDoorInformation(String noOfDoors) {
	this.noOfDoors = noOfDoors;
}

public static Performable on(String noOfDoors) {
	return instrumented(ValidateVehicleDoorInformation.class,noOfDoors);
}

@Step("Validate Vehicles Information #vehicleName")
public <T extends Actor> void performAs(T actor){
	
	String vehicleFilterLocator = "//section[@class='container-fluid cf-pad-0 full-bleed-width']//div/div/h3[contains(text(),'SUV')]//ancestor::div[@class='row avilablecar available-car-box']//div[@class='available-car-fac hidden']/span[1]";
	
    
	if(!noOfDoors.trim().isEmpty()) {
	try {
	
	
  
    int doorlist =  BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(vehicleFilterLocator)).size();
    
    for(int i = 1;i<doorlist;i++) {
    	
    	String e = BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(vehicleFilterLocator)).get(i).getText();
    
    if(e.contains("4")) {
      System.out.println("Contains");
    	
    }
    System.out.println("Seat informaiotndd "+e);
    }
    
   
    }
    
	catch(NoSuchElementException e) {
		throw new InvalidLocatorRuntimeException("Vehicle Name " + noOfDoors);
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	else {
		System.out.println("vehicleName should be Mandatory to filter the vehicles");
		throw new MandatoryFieldValueRuntimeException("Vehicle Name" );
		
	}
}
}

