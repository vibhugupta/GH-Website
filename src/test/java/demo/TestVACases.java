package demo;


import file.PropertyFileReader;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Properties;

public class TestVACases {


    SeleniumCode scObj = new SeleniumCode();
    String userDataFile = System.getProperty("user.dir") + "\\src\\main\\resources\\usersdatafile\\usersdata.properties";
    Properties prop;

    @BeforeSuite
    public void login() {

        prop = PropertyFileReader.locatorFileReader(userDataFile);

        scObj.loginGHApp((prop.getProperty("username")), (prop.getProperty("password")));


    }

    @Test(priority = 1)
    public void home() {

       scObj.homeGHApp(prop.getProperty("chargecodeValue"));

    }

    @Test(priority = 2)
    public void NewBooking() {

        scObj.newBooking(prop.getProperty("officeLocationCity"),
                prop.getProperty("travelLocationCity"),prop.getProperty("nearestLocation"),
                prop.getProperty("checkInDateValue"),
                prop.getProperty("checkOutDateValue"),prop.getProperty("checkInTimeValue"));

    }


    @Test(priority = 3)
    public void manageBooking() {

        scObj.manageBooking(prop.getProperty("statusOnManageBooking"),
                prop.getProperty("guestHouseNameManageBooking"),
                prop.getProperty("bookingTypeManageBooking"),
                prop.getProperty("caretakerNameManageBooking"),
                prop.getProperty("caretakerContactNumberManageBooking"),
                prop.getProperty("guesthouseContactNumberManageBooking"),
                prop.getProperty("checkInDateManageBooking"),
                prop.getProperty("checkOutDateManageBooking"),prop.getProperty("checkInTimeManageBooking"),
                prop.getProperty("checkOutTimeManageBooking"),
                prop.getProperty("BookedForManageBooking"),prop.getProperty("BookedByManageBooking")
        );

    }

    @Test(priority = 4)
    public void editBooking() {

        scObj.editBooking(prop.getProperty("officeLocationCity"));

    }

    @Test(priority = 5)
    public void cancelBooking() {

        scObj.cancelBooking();

    }
}
