package demo;

import file.PropertyFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * Created by vibhu on 4/26/2017.
 */
public class SeleniumCode {


    public static WebDriver driver = null;
    String locatorFirstFile = System.getProperty("user.dir") + "\\src\\main\\resources\\locatorsfile\\locators.properties";
    String chromeDriverFileLocation = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver\\chromedriver.exe";
    Properties prop;


    public void loginGHApp(String username, String password) {

        prop = PropertyFileReader.locatorFileReader(locatorFirstFile);

        System.setProperty("webdriver.chrome.driver", chromeDriverFileLocation);
        driver = new ChromeDriver();
        driver.get("http://application-721436538.ap-northeast-2.elb.amazonaws.com/guesthouse/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.findElement(By.xpath(prop.getProperty("usernameXpath"))).sendKeys(username);
        driver.findElement(By.xpath(prop.getProperty("passwordXpath"))).sendKeys(password);
        driver.findElement(By.xpath(prop.getProperty("loginButtonXpath"))).click();
    }

    public void sleep(int sleepValue) {
        try {
            Thread.sleep(sleepValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void homeGHApp(String chargecodeValue) {

        prop = PropertyFileReader.locatorFileReader(locatorFirstFile);
        sleep(5000);
        driver.findElement(By.xpath(prop.getProperty("addChargeCodeIconXpath"))).click();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("enterChargeCodeValueXpath"))).sendKeys(chargecodeValue);
        driver.findElement(By.xpath(prop.getProperty("saveChargeCodeXpath"))).click();
        sleep(5000);
        driver.findElement(By.xpath(prop.getProperty("selectChargeCodeDropDownXpath"))).click();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("lastaddedChargeCodeSelectionXpath"))).click();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("continueLoginBookChargeCodeXpath"))).click();
    }

    public void newBooking(String officeLocationCity, String travelLocationCity, String nearestLocation,
                           String checkInDateValue, String checkOutDateValue,
                           String checkInTimeValue) {

        String officeLocationxpath="//div[contains(text(),'".concat(officeLocationCity).concat("')]");
        String travelLocationxpath="//div[contains(text(),'".concat(travelLocationCity).concat("')]");
        nearestLocation="Rohini, New Delhi,";
        String nearestLocationxpath="//div[contains(text(),'".concat(nearestLocation).concat("')]");
        String checkInDateValueXpath="//span[text() = '".concat(checkInDateValue).concat("']");
        String checkOutDateValueXpath="//span[text() = '".concat(checkOutDateValue).concat("']");
        String checkInTimeValueXpath="//span[text() = '".concat(checkInTimeValue).concat("']");

        System.out.println(officeLocationxpath);
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("newbookingIconOnHomePageXpath"))).click();
        sleep(5000);
        driver.findElement(By.xpath(prop.getProperty("officeLocationXpath"))).click();
        sleep(1000);

        driver.findElement(By.xpath(officeLocationxpath)).click();
        sleep(3000);
        driver.findElement(By.xpath(prop.getProperty("travelLocationXpath"))).click();
        sleep(1000);

        driver.findElement(By.xpath(travelLocationxpath)).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("nearestLocationXpath"))).clear();

        driver.findElement(By.xpath(prop.getProperty("nearestLocationXpath"))).sendKeys(nearestLocation);

        driver.findElement(By.xpath(nearestLocationxpath)).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("checkInDateXpath"))).click();
        sleep(1000);

        driver.findElement(By.xpath(checkInDateValueXpath)).click();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("dateOrTimeSelectionpopOKxpath"))).click();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("checkOutDateXpath"))).click();
        sleep(1000);

        driver.findElement(By.xpath(checkOutDateValueXpath)).click();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("dateOrTimeSelectionpopOKxpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("checkinTimeXpath"))).click();
        sleep(5000);

        WebElement element = driver.findElement(By.xpath(checkInTimeValueXpath));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("dateOrTimeSelectionpopOKxpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("excludeSaturdayXpath"))).click();
        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("excludeSundayXpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("continueNewBookingXpath"))).click();
        sleep(2000);
        try {
            driver.findElement(By.xpath(prop.getProperty("firstGuestHouseSelectionXpath"))).click();
            sleep(2000);
            driver.findElement(By.xpath(prop.getProperty("selectedGuestHouseBookingButtonXpath"))).click();
            sleep(2000);
            driver.findElement(By.xpath(prop.getProperty("OkButtonOnConfirmationXpath"))).click();
            sleep(2000);
            driver.findElement(By.xpath(prop.getProperty("doneButtonXpath"))).click();
        } catch (Exception e) {
            sleep(2000);
            Assert.assertEquals(driver.findElement(By.xpath(prop.getProperty("alreadyBookingPopUpXpath"))).getText(),
                    "You already have a booking b/w this period. Consider changing Date/ Time!");
            driver.findElement(By.xpath(prop.getProperty("OkButtonOnConfirmationXpath"))).click();
            sleep(1000);
            driver.findElement(By.xpath(prop.getProperty("backArrowIconToHomePageXpath"))).click();
        }


      //  manageBooking(statusOnManageBooking);
      //  editBooking();
     //   cancelBooking();

    }


    public void editBooking(String officeLocationCity) {

        String officeLocationxpath="//div[contains(text(),'".concat(officeLocationCity).concat("')]");

        sleep(1000);
        driver.findElement(By.xpath(prop.getProperty("editFirstBookingXpath"))).click();
        sleep(3000);
        driver.findElement(By.xpath(prop.getProperty("officeLocationXpath"))).click();
        sleep(1000);
        driver.findElement(By.xpath(officeLocationxpath)).click();
        sleep(3000);
        driver.findElement(By.xpath(prop.getProperty("excludeSaturdayXpath"))).click();

        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("continueNewBookingXpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("firstGuestHouseSelectionXpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("selectedGuestHouseBookingButtonXpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("OkButtonOnConfirmationXpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("doneButtonXpath"))).click();

    }

    public void cancelBooking() {

        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("manageBookingIconOnHomePageXpath"))).click();
        sleep(2000);
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("cancelButtonManageBookingXpath"))).click();
        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("OkButtonOnConfirmationXpath"))).click();
    }

    public void manageBooking(String statusOnManageBooking, String guestHouseNameManageBooking,
                              String bookingTypeManageBooking,String caretakerNameManageBooking,
                              String caretakerContactNumberManageBooking,String guesthouseContactNumberManageBooking,
                              String checkInDateManageBooking, String checkOutDateManageBooking,
                              String checkInTimeManageBooking, String checkOutTimeManageBooking,
                              String BookedForManageBooking, String BookedByManageBooking
                              ) {


        sleep(2000);
        driver.findElement(By.xpath(prop.getProperty("manageBookingIconOnHomePageXpath"))).click();
        sleep(2000);
        String manageBookingHeaderTextLoc =
                driver.findElement(By.xpath(prop.getProperty("manageBookingHeaderXpath"))).getText();
        Assert.assertEquals("Manage Bookings", manageBookingHeaderTextLoc);
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("bookingStatusManageBookingXpath"))).getText(),
                (statusOnManageBooking));
        Assert.assertEquals("Past",
                driver.findElement(By.xpath(prop.getProperty("pastTextTabManageBookingXpath"))).getText());
        Assert.assertEquals("Upcoming",
                driver.findElement(By.xpath(prop.getProperty("upComingTextTabManageBookingXpath"))).getText());
        Assert.assertEquals("Name",
                driver.findElement(By.xpath(prop.getProperty("ghNameTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("ghNameManageBookingXpath"))).getText(),
                guestHouseNameManageBooking);
        Assert.assertEquals("Type",
                driver.findElement(By.xpath(prop.getProperty("typeTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("typeValueManageBookingXpath"))).getText(),
                bookingTypeManageBooking);
        Assert.assertEquals("Room Number",
                driver.findElement(By.xpath(prop.getProperty("roomNumberTextManageBookingXpath"))).getText());
//        Assert.assertEquals("1",
//                driver.findElement(By.xpath(prop.getProperty("roomNumberValueManageBookingXpath"))).getText());
        Assert.assertEquals("Caretaker",
                driver.findElement(By.xpath(prop.getProperty("caretakerTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("caretakerValueManageBookingXpath"))).getText(),
                caretakerNameManageBooking);
        Assert.assertEquals("Contact Caretaker",
                driver.findElement(By.xpath(prop.getProperty("contactCaretakerTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("contactCaretakerValueManageBookingXpath"))).getText(),
                caretakerContactNumberManageBooking);
        Assert.assertEquals("Contact Guesthouse",
                driver.findElement(By.xpath(prop.getProperty("contactGuestHouseTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("contactGuestHouseValueManageBookingXpath"))).getText(),
                guesthouseContactNumberManageBooking);
        Assert.assertEquals("Check in date",
                driver.findElement(By.xpath(prop.getProperty("checkInDateTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("checkInDateValueManageBookingXpath"))).getText(),
                checkInDateManageBooking);
        Assert.assertEquals("Check out date",
                driver.findElement(By.xpath(prop.getProperty("checkOutDateTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("checkOutDateValueManageBookingXpath"))).getText(),
                checkOutDateManageBooking);
        Assert.assertEquals("Check in time",
                driver.findElement(By.xpath(prop.getProperty("checkInTimeTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("checkInTimeValueManageBookingXpath"))).getText(),
                checkInTimeManageBooking);
        Assert.assertEquals("Check out time",
                driver.findElement(By.xpath(prop.getProperty("checkOutTimeTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("checkOutTimeValueManageBookingXpath"))).getText(),
                checkOutTimeManageBooking);
        Assert.assertEquals("Booked For",
                driver.findElement(By.xpath(prop.getProperty("bookForTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("bookForValueManageBookingXpath"))).getText(),
                BookedForManageBooking);
        Assert.assertEquals("Booked By",
                driver.findElement(By.xpath(prop.getProperty("bookByTextManageBookingXpath"))).getText());
        Assert.assertEquals(
                driver.findElement(By.xpath(prop.getProperty("bookByValueManageBookingXpath"))).getText(),
                BookedByManageBooking);
    }
}

