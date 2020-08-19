package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.automation.exception.InvalidLocatorRuntimeException;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class SortVehiclesUsingPrice implements Task {
 private static String vehicleSort = "Price";
  
public SortVehiclesUsingPrice() {}

public static Performable on() {
 return instrumented(SortVehiclesUsingPrice.class);
}

@Step("Sort Vehicles with Price")
public <T extends Actor> void performAs(T actor){
	
	String vehicleFilterLocator = "//div[@class='vehiclePushDownBox']//div[@class='pull-left veh-Select-Style veh-LtR-gtPad-Null dropdown']/a";
    String vehiclePriceLocator = "//ul[@class='dropdown-menu']//li[1]//a";
    
	try {
	
		WebElement vehicleFilterElement =BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(vehicleFilterLocator));
		BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",vehicleFilterElement);
        String vehicleSortElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(vehiclePriceLocator)).getText();
        
        if(vehicleSortElement.contains(vehicleSort)) {
    	   WebElement vehicleSeatsElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(vehiclePriceLocator));
    	   BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",vehicleSeatsElement);
                }
        }
  
	catch(NoSuchElementException e) {
		throw new InvalidLocatorRuntimeException("Vehicle Name ");
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
  }
}


