package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.junit.Assert;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class ValidateLocationAddressInPayPage implements Task {
  private String FieldName;
  private String expAddress;

public ValidateLocationAddressInPayPage(String FieldName,String expAddress) {
  this.FieldName = FieldName;
  this.expAddress = expAddress;
}

public static Performable on(String FieldName,String expAddress) {
	return instrumented(ValidateLocationAddressInPayPage.class,FieldName,expAddress);
}

@Step("Verify the Text #text")
public <T extends Actor> void performAs(T actor){
    String addressLocator = null;
    
    if(FieldName.equalsIgnoreCase("Pick-Up")) {
	 addressLocator = "//div[@class='col-sm-6 source']//div[@class='location-info']";
    }
    else if (FieldName.equalsIgnoreCase("Return")){
     addressLocator = "//div[@class='col-sm-6 destination']//div[@class='location-info']";
     
    }
	
	try {
		
		String[] actualAddress = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(addressLocator)).getText().split(",");
		String actualAddressText = actualAddress[0];

    	if(expAddress.contains(actualAddressText)) {
    			System.out.println("Expected Address  :"+  expAddress);
    			System.out.println("Actual Address    :" + actualAddressText);
    			System.out.println("Actual Address contains expected Location");
    		}
    		else {
    			System.out.println("Expected Location "+expAddress );
    			System.out.println("Actual Location " + actualAddressText );
    			Assert.assertTrue(expAddress.equals(actualAddressText));
    			
    		}
        }
 
	
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	
}


