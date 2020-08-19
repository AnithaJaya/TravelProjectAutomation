package com.automation.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import com.automation.budget.commonutilities.FilterVehiclesUsingVehicleName;
import com.automation.budget.commonutilities.SortVehiclesUsingPrice;

public class SelectVehiclewithNameAndNoOfSeats implements Task {
private String vehicleName;

public SelectVehiclewithNameAndNoOfSeats(String vehicleName){
	this.vehicleName = vehicleName;
	}

public static Performable on(String vehicleName) {
	return instrumented(SelectVehiclewithNameAndNoOfSeats.class,vehicleName);
}

@Override
public <T extends Actor> void performAs(T actor) {
	try {
		
		actor.attemptsTo(
				FilterVehiclesUsingVehicleName.on(vehicleName),
				SortVehiclesUsingPrice.on()
			);
	}
	catch(Exception e) {
		e.printStackTrace();
		
	}
}
}