package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import com.automation.exception.MandatoryFieldValueRuntimeException;
import org.junit.Assert;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class ValidateVehicleNameInPayScreen implements Task {
  private String vehicleName;
  
public ValidateVehicleNameInPayScreen(String vehicleName) {
	this.vehicleName = vehicleName;
}

public static Performable on(String vehicleName) {
	return instrumented(ValidateVehicleNameInPayScreen.class,vehicleName);
}

@Step("Validate Vehicles Information #vehicleName")
public <T extends Actor> void performAs(T actor){
	
	String vehicleNameLocator = "//div[@class='vehicle-name' and text() = '"+vehicleName+"']";
	System.out.println("Vehicle name "+vehicleName );
    
	if(vehicleName!=null && !vehicleName.trim().isEmpty()) {
	try {
    int vehicleNamecount =  BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(vehicleNameLocator)).size();
    if(vehicleNamecount>0) {
    	System.out.println("Expected Vehicle Name is Present : "+vehicleName);
    }
    else {
    	Assert.assertTrue(vehicleNamecount>0);
    }
    }
   
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	else {
		System.out.println("vehicleName should be Mandatory");
		throw new MandatoryFieldValueRuntimeException("Vehicle Name" );
		
	}
}
}


