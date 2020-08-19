package com.automation.budget.commonutilities;

import net.serenitybdd.screenplay.Performable;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import org.junit.Assert;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

public class ValidateLocationAddress implements Task {
  private String FieldName;
  private String expAddress;

public ValidateLocationAddress(String FieldName,String expAddress) {
  this.FieldName = FieldName;
  this.expAddress = expAddress;
}

public static Performable on(String FieldName,String expAddress) {
  return instrumented(ValidateLocationAddress.class,FieldName,expAddress);
}

@Step("Validate the Location Address For   #FieldName")
public <T extends Actor> void performAs(T actor){
    
 String textLocator = "//div[@class='location step2']//div[@class='title']/a[1]";
	
 try {
	 
	int titleSize = BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(textLocator)).size();
    for(int i= 1; i <titleSize;i++) 
      { 
    	String titleLocator = "//div[@class='location step2']//div["+i+"][@class='title']/a[1]";
        String titleName    = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(titleLocator)).getText();
    
    	if(titleName.contains(FieldName))
    	{
    		String actualTextLocator =  "//div[@class='location step2']//div["+i+"][@class='title']/following-sibling::div[@class='location-info']";
    		String[] actualAddress   = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(actualTextLocator)).getText().split(",");
    		String actualAddressText = actualAddress[0];
   
    		if(expAddress.contains(actualAddressText)){
    			System.out.println("Expected Location  :"+  expAddress);
    			System.out.println("Actual Location    :" + actualAddressText);
    			System.out.println("Actual Location contains expected Location");
    		}
    		else{
    			System.out.println("Expected Location  : "+expAddress );
    			System.out.println("Actual Location    :" + actualAddress );
    			Assert.assertTrue(expAddress.equals(actualAddressText));
    			}
    	}
     }
 
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	
}


