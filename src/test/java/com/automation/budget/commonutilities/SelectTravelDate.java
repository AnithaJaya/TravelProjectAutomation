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

public class SelectTravelDate implements Task {
  private String date;
  private String dateFieldName;
  Actor actor = Actor.named("user");
  
public SelectTravelDate(String dateFieldName,String date) {
  this.dateFieldName = dateFieldName;
  this.date = date;
}

public static Performable on(String dateFieldName,String date) {
  return instrumented(SelectTravelDate.class,dateFieldName,date);
}

@Step("Select the Travel date #dateFieldName")
public <T extends Actor> void performAs(T actor){
    this.actor = actor;
    
	String dateLocator = "//div[@class='col-xs-3 res-inputFld dateImg']//input[@data-dpheaderlabel='"+dateFieldName+"']";
    String monthLocator = "//select[@data-handler='jumpMonth']";
    
    if(!dateFieldName.trim().isEmpty()){
	try {
	WebElement dateIconLocator = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(dateLocator));
    BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",dateIconLocator);
      try {
    	String expectedMonthValue = date.substring(0, 2);
    	String dateValue          = date.substring(3, 5);
    	if(dateValue.startsWith("0")){
    		dateValue = dateValue.substring(1);
    		 }
    	if(expectedMonthValue.startsWith("0")){
    		expectedMonthValue = expectedMonthValue.substring(1);
    	     }
    	
    	int monthValue =Integer.parseInt(expectedMonthValue)-1;
        BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(monthLocator)).click();
    	String selectMonth = Integer.toString(monthValue).concat("|").concat("2020");
        String selectMonthLocator = "//select[@data-handler='jumpMonth']//option[@value='"+selectMonth+"']";
    	BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(selectMonthLocator)).click();
   	    dateLocator = "//table[@class='ui-datepicker-calendar uitable ui-datepicker-table-first ']/tbody//a[contains(text(),'"+dateValue+"')]";
    	WebElement dateFieldElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(dateLocator));
    	BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",dateFieldElement);
        System.out.println("Selected the Travel Date :" +date );
        closeCalendar(); 
          }
      
      catch(NoSuchElementException e){
    	throw new InvalidLocatorRuntimeException(dateFieldName);
       }
	  }
	catch(NoSuchElementException e) {
		throw new InvalidLocatorRuntimeException(dateFieldName);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
	else{
		System.out.println("Date Field Name is Mandatory");
		throw new MandatoryFieldValueRuntimeException(dateFieldName);
	}
  }



private void closeCalendar() {
	String closeCalendarLocator = "//button[@id='calendarclose']";
	WebElement closeCalendarElement = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(closeCalendarLocator));
	BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click()",closeCalendarElement);
	}

}
