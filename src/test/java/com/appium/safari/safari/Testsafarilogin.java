package com.appium.safari.safari;

//package <set your test package>;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.junit.*;
import java.net.URL;
import java.net.MalformedURLException;

public class Testsafarilogin {
private String reportDirectory = "reports";
private String reportFormat = "xml";
private String testName = "Untitled";
protected AndroidDriver<AndroidElement> driver = null;

DesiredCapabilities dc = new DesiredCapabilities();

@Before
public void setUp() throws MalformedURLException {
dc.setCapability("reportDirectory", reportDirectory);
dc.setCapability("reportFormat", reportFormat);
dc.setCapability("testName", testName);
///
dc.setCapability(MobileCapabilityType.PLATFORM_NAME, System.getProperty(""));
dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
////
dc.setCapability(MobileCapabilityType.UDID, "4d0098134a4a217d");
dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "za.co.fourimobile.safarioutdoor.debug");
dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "za.co.fourimobile.safarioutdoor.SplashActivity");
driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
}
@Test

public void LoginTest() {

//Clicking on skip to got o last page (could swipe but not all phones have 4 tutorial screens, and this will insure alld devices gets to last one)

 

driver.findElement(By.xpath("//*[@text='Skip' and @width>0]")).click();

driver.findElement(By.xpath("//*[@text='Okay']")).click();

driver.findElement(By.xpath("//*[@text='Allow']")).click(); //If the permission doesnt ned to be allowed it will skip after a couple of seconds

driver.findElement(By.xpath("//*[@id='icon' and ./parent::*[@id='menu_post']]")).click(); //clicking on Post, to show the sign in screen

new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Sign In']"))); //Clicking on the sign in button

driver.findElement(By.xpath("//*[@text='Sign In']")).click(); 



///////////

driver.findElement(By.xpath("//*[@text and @id='edit_text']")).click();

driver.findElement(By.xpath("//*[@text and @id='edit_text']")).sendKeys("saf1@saf1.com"); //enter email

driver.findElement(By.xpath("//*[@id='edit_text' and ./following-sibling::*[@id='reveal_holder']]")).click();

//enter password

driver.findElement(By.xpath("//*[@id='edit_text' and ./following-sibling::*[@id='reveal_holder']]")).sendKeys("qwerty");



/////////



driver.findElement(By.xpath("//*[@id='login_button']")).click();

new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='background' and ./*[./*[@text='Brag']]]")));

driver.findElement(By.xpath("//*[@id='background' and ./*[./*[@text='General']]]")).click();



driver.findElement(By.xpath("//*[@id='post_description_holder']")).click();

driver.findElement(By.xpath("//*[@id='post_description_holder']")).sendKeys("This is a automated test");





driver.hideKeyboard();

//NEED TO FIX SCROLLING

driver.findElement(By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@id='image_1']]")).click();;

//allow camera permissions

driver.findElement(By.xpath("//*[@text='Allow']")).click();

driver.findElement(By.xpath("//*[@text='Allow']")).click();

//Wait for camera

new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Camera']")));

driver.findElement(By.xpath("//*[@text='Camera']")).click();

//pic button Need to Long press





driver.findElement(By.xpath("//*[@id='GLSurfaceLayout']")).click();

new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='OK']")));

driver.findElement(By.xpath("//*[@text='OK']")).click();

driver.findElement(By.xpath("//*[@contentDescription='Save']")).click();







}



@After

public void tearDown() {

   driver.quit();

   

   

    

   

}

}

