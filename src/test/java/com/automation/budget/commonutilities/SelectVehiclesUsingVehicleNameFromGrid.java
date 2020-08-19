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

public class SelectVehiclesUsingVehicleNameFromGrid implements Task {
  private String vehicleName;
  private static String selectedVehicleName;
  
  public SelectVehiclesUsingVehicleNameFromGrid(String vehicleName) {
  this.vehicleName = vehicleName;
 }
  
 public static String getSelectedVehicleName() {
  return selectedVehicleName;
	 
}

 
 public static void setSelectedVehicleName(String vehicleName) {
  SelectVehiclesUsingVehicleNameFromGrid.selectedVehicleName =  vehicleName;
 }
 


public static Performable on(String vehicleName) {
	return instrumented(SelectVehiclesUsingVehicleNameFromGrid.class,vehicleName);
}

@Step("Select Vehicles From Grid #vehicleName")
public <T extends Actor> void performAs(T actor){
	String vehicleFilterLocator =  "//section[@class='container-fluid cf-pad-0 full-bleed-width']//div/div/h3[contains(text(),'SUV')]/parent::div/a[text()='View Vehicle Information']";
    
	if(!vehicleName.trim().isEmpty()) {
	try 
	{
	
		int noOfVehicles =BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(vehicleFilterLocator)).size();
		
        for(int i=2;i<=noOfVehicles+1;i++) {
        	
    	String viewInformationLocator ="//div[@class='step2dtl']/div['"+i+"']/div/section/div//a[text()='View Vehicle Information']";
        WebElement viewInformationElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(viewInformationLocator));
        BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",viewInformationElement);
        String seatInformation = "//div[@class='row avilablecar available-car-box bg-grey']//div[@class='available-car-fac bg-grey']/span[2]";
        String seatInformationElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(seatInformation)).getText();
        String doorInformation = "//div[@class='row avilablecar available-car-box bg-grey']//div[@class='available-car-fac bg-grey']/span[1]";
        String doorInformationElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(doorInformation)).getText();
	 
	      if((seatInformationElement.contains("5 seats") && doorInformationElement.contains("4 Doors"))) 
	      {
          String payLocator = "//div[@class='row avilablecar available-car-box bg-grey']//a[text()='Pay Now']";
          WebElement payElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(payLocator));
          String vehicleName = "//div[@class='row avilablecar available-car-box bg-grey']//h3[@ng-bind='car.carGroup']";
          setSelectedVehicleName(BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(vehicleName)).getText());
          BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",payElement);
    	  break;
    	
    	 
          }
    	 else {
    		
    		 String closeVehicleLocator = "//div[@class='step2dtl']/div[2]/div/section/div//a[text()='Close Vehicle Information']";
    		 WebElement closeVehicleElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(closeVehicleLocator));
    		 BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",closeVehicleElement);
    	   }
       }
     }
	 
	catch(NoSuchElementException e) {
		throw new InvalidLocatorRuntimeException("Vehicle Name " + vehicleName);
		
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

