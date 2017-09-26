package com.appium.safari.safari;

//package <set your test package>;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.media.jfxmediaimpl.platform.PlatformManager;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.By;
import org.junit.*;
import java.net.URL;
import java.net.MalformedURLException;


public class Testsafarilogin {
//	
//
private String reportDirectory = "reports";
private String reportFormat = "xml";
private String testName = "Untitled";
private String PlatformVersion = "";
protected AndroidDriver<AndroidElement> driver = null;

DesiredCapabilities dc = new DesiredCapabilities();

@Before
public void setUp() throws MalformedURLException {
dc.setCapability("reportDirectory", reportDirectory);
dc.setCapability("reportFormat", reportFormat);
dc.setCapability("testName", testName);
///
PlatformVersion = System.getProperty("PlatformVersion");
//PlatformVersion = "5.0.1";
dc.setCapability(MobileCapabilityType.PLATFORM_NAME, System.getProperty("PlatformName"));

dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, PlatformVersion);
dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");

////
dc.setCapability(MobileCapabilityType.UDID, System.getProperty("DeviceUDID"));
dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "za.co.fourimobile.safarioutdoor.debug");
dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "za.co.fourimobile.safarioutdoor.SplashActivity");
driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
}
@Test

public void LoginTest() {

//Clicking on skip to got o last page (could swipe but not all phones have 4 tutorial screens, and this will insure alld devices gets to last one)
 
driver.findElement(By.xpath("//*[@text='Skip']")).click();

//If the platform version is bellow 6 kick of the code (permissions not asked on <6)
if (versionCompare(PlatformVersion, "6") < 0)
   { 
	driver.findElement(By.xpath("//*[@text='Skip' and ./parent::*[./following-sibling::*[./*[@text='Share your know how or expertise\n" + 
			" by answering quiz questions and \n" + 
			"giving helpful tips to those who are \n" + 
			"in need of valuable advice.']]]]")).click();
	new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='icon' and ./parent::*[@id='menu_post']]")));
	driver.findElement(By.xpath("//*[@id='icon' and ./parent::*[@id='menu_post']]")).click(); //clicking on Post, to show the sign in screen
   } 
else
   {
	driver.findElement(By.xpath("//*[@text='Okay']")).click();
	driver.findElement(By.xpath("//*[@text='Allow']")).click(); //If the permission doesnt ned to be allowed it will skip after a couple of seconds
	driver.findElement(By.xpath("//*[@id='icon' and ./parent::*[@id='menu_post']]")).click(); //clicking on Post, to show the sign in screen
   }



new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Sign In']"))); //waiting  on the sign in button

driver.findElement(By.xpath("//*[@text='Sign In']")).click(); 


driver.findElement(By.xpath("//*[@text and @id='edit_text']")).click();

driver.findElement(By.xpath("//*[@text and @id='edit_text']")).sendKeys("saf1@saf1.com"); //enter email

driver.findElement(By.xpath("//*[@id='edit_text' and ./following-sibling::*[@id='reveal_holder']]")).click();

//enter password
driver.findElement(By.xpath("//*[@id='edit_text' and ./following-sibling::*[@id='reveal_holder']]")).sendKeys("qwerty");


driver.findElement(By.xpath("//*[@id='login_button']")).click();

new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='background' and ./*[./*[@text='Brag']]]")));

}
/**
 * Compares two version strings. 
 * 
 * Use this instead of String.compareTo() for a non-lexicographical 
 * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
 * 
 * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
 * 
 * @param str1 a string of ordinal numbers separated by decimal points. 
 * @param str2 a string of ordinal numbers separated by decimal points.
 * @return The result is a negative integer if str1 is _numerically_ less than str2. 
 *         The result is a positive integer if str1 is _numerically_ greater than str2. 
 *         The result is zero if the strings are _numerically_ equal.
 */
public int versionCompare(String str1, String str2) {
    String[] vals1 = str1.split("\\.");
    String[] vals2 = str2.split("\\.");
    int i = 0;
    // set index to first non-equal ordinal or length of shortest version string
    while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
      i++;
    }
    // compare first non-equal ordinal number
    if (i < vals1.length && i < vals2.length) {
        int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
        return Integer.signum(diff);
    }
    // the strings are equal or one string is a substring of the other
    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
    return Integer.signum(vals1.length - vals2.length);
}

@After

public void tearDown() {

   driver.quit();

   

}

}

