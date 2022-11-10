package swiggytest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class SwiggyTest {

	static AndroidDriver driver;
	
	@Test
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			openSwiggyApp();
			clickFoodCategory();
			searchFood();
			addFoodToCart();
			ViewCart();
			checkOut();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
		}

	}
	
	@BeforeTest
	@SuppressWarnings("deprecation")
	public static void openSwiggyApp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("deviceName", "realme 7");
		capabilities.setCapability("udid", "GQ8TQ8DAW8WGVO9X");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "11");
		capabilities.setCapability("ignoreHiddenApiPolicyError", true);
		capabilities.setCapability("appPackage", "in.swiggy.android");
		capabilities.setCapability("appActivity", "in.swiggy.android.activities.HomeActivity");
		capabilities.setCapability("noReset", true);
		
		capabilities.setCapability("unicodeKeyboard", "true");                                     
		capabilities.setCapability("resetKeyboard", "true");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, capabilities);	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public static void shutDown() {
		driver.quit();
	}
	
	public static void clickFoodCategory() {
		By food = By.id("in.swiggy.android:id/m_n_image_spec_id");
		
		driver.findElement(food).click();
	}
	
	public static void searchFood() throws InterruptedException {
		By searchInput = By.id("in.swiggy.android:id/et_search_query_v2");
		By searchIcon = By.id("in.swiggy.android:id/searchIcon");
		
		
		driver.findElement(searchIcon).isDisplayed();
		driver.findElement(searchIcon).click();
		System.out.println("-----Search bar clicked----");
		driver.findElement(searchInput).sendKeys("Chichken Dum Biryani");
		Thread.sleep(2000);
		System.out.println("-----Searched for Biryani----");
//		Enter Key event to Navigate selection to suggestion list
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
//		Enter Key event to select suggested food item
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		
	}
	
	public static void addFoodToCart() {
		By addButton = By.id("in.swiggy.android:id/quantity_text_1");
		
		driver.findElement(addButton).click();
	}

	public static void ViewCart() {
		By cartView = By.id("in.swiggy.android:id/landing_cart_view");
		
		driver.findElement(cartView).click();		
	}
	
	public static void checkOut() {
		By checkOut = By.id("in.swiggy.android:id/tv_checkout");
		
		driver.findElement(checkOut).click();
	}
	
}
