package com.automation.tasks;

import com.automation.actions.MaximizeTheWindow;
import com.automation.ui.LoginPage;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

public class OpenTheApplication implements Task,Interaction{
 LoginPage login;
	
public static OpenTheApplication toTheLoginPage() {
	return Instrumented.instanceOf(OpenTheApplication.class).withProperties();
 }

@Step(" Opens Budget Application")
public <T extends Actor> void performAs(T actor) {
	
	actor.attemptsTo(
			MaximizeTheWindow.toMaxWindow(),
			Open.browserOn().the(login)
			);
	 }
 }
