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

public class FilterVehiclesUsingVehicleName implements Task {
  private String vehicleName;
  

public FilterVehiclesUsingVehicleName(String vehicleName) {
  this.vehicleName = vehicleName;
}

public static Performable on(String vehicleName) {
	return instrumented(FilterVehiclesUsingVehicleName.class,vehicleName);
}

@Step("Filter Vehicles Using #vehicleName")
public <T extends Actor> void performAs(T actor){
	
	String vehicleFilterLocator = "//div[@class='vehiclePushDownBox']//form[@name='resForm']//div[@class='pull-left veh-LtR-gtPad-Null']";
	
    String vehicleLocator = "//ul[@class='list-group vehUlStyle vehDeskClose']//li/div/span[contains(text(),'"+vehicleName+"')]/preceding-sibling::img";
    
	if(!vehicleName.trim().isEmpty()) {
		
	try {
	
		WebElement vehicleFilterElement =BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(vehicleFilterLocator));
		BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",vehicleFilterElement);
        WebElement isVehiclePresent =  BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(vehicleLocator));
        BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",isVehiclePresent);
        }
	
	catch(NoSuchElementException e) {
		throw new InvalidLocatorRuntimeException("Vehicle Name " + vehicleName);
		
	    }
	
	catch(Exception e){
		e.printStackTrace();
	    }
	}
	else {
		System.out.println("vehicleName should be Mandatory to filter the vehicles");
		throw new MandatoryFieldValueRuntimeException("Vehicle Name" );
		
	}
  }
}

