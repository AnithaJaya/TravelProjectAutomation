package com.automation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import com.automation.budget.commonutilities.EnterTravelLocation;
import com.automation.budget.commonutilities.GetDate;
import com.automation.budget.commonutilities.SelectTravelDate;

public class EnterTravelDetails implements Task{
 public static  String pickUpDate = GetDate.getFutureDate(7);
 public static String  dropDate   = GetDate.getFutureDate(14);
 private String pickUpLocation;
 private String pickUpField = "Select Pick-up date";
 private String returnField = "Select Return date";
 
 
public EnterTravelDetails(String pickUpLocation) {
 this.pickUpLocation = pickUpLocation;
}

public static Performable enterandselect(String pickUpLocation) {
 return instrumented(EnterTravelDetails.class,pickUpLocation);
}

@Override
public <T extends Actor> void performAs(T actor) {
	
 try {
	  actor.attemptsTo(
				EnterTravelLocation.on(pickUpLocation),
				SelectTravelDate.on(pickUpField,pickUpDate),
				SelectTravelDate.on(returnField,dropDate)
				);
	 }
 
 catch(Exception e){
		e.printStackTrace();
	 }
  }
}