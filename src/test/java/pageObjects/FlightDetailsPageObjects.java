package pageObjects;

import org.openqa.selenium.By;

public interface FlightDetailsPageObjects {

	By roundTrip = By.xpath("//input[@value='roundtrip']");
	By oneWay = By.xpath("//input[@value='oneway']");

	By noOfPassengersDropDown = By.name("passCount");

	By departingFrom = By.name("passCount");
	By departingFromMonth = By.name("fromMonth");
	By departingFromDay = By.name("fromDay");

	By arrivingIn = By.name("toPort");
	By arrivingMonth = By.name("toMonth");
	By arrivingDay = By.name("toDay");

	By economyClass = By.xpath("//input[@value='Coach']");
	By businessClass = By.xpath("//input[@value='Business']");
	By firstClass = By.xpath("//input[@value='First']");

	By airlinePreference = By.name("airline");
	
	By continueButton = By.name("findFlights");

}