package testcases.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestSauceLab {
	// USERNAME = "chaya-Thilakumara";
	// ACCESS_KEY = "97763576-4590-45ba-8a36-a41fdde52359";
	// public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY +
	// "@ondemand.saucelabs.com:443/wd/hub";
	public static final String URL = "https://chaya:97863576-4590-45ba-8a36-a41fdde52359@ondemand.saucelabs.com:443/wd/hub";

	public static void main(String[] args) throws Exception {

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		/*
		 * caps.setCapability("platform", "Windows 10"); caps.setCapability("platform",
		 * "Linux");
		 */caps.setCapability("platform", "macOS 10.13");
		caps.setCapability("version", "latest");
		caps.setCapability("name", "Test1");
		caps.setCapability("extendedDebugging", "true");
		caps.setCapability("buildNumber", "3.0");
		WebDriver driver = new RemoteWebDriver(new java.net.URL(URL), caps);

		// Go to the url
		driver.get("https://www.findmyfare.com/");

		// Get the title and print it
		System.out.println(driver.getTitle());

		// Validate landed url
		System.out.println(driver.getCurrentUrl());

		// Print page source
		System.out.println(driver.getPageSource());

		// Go to the url
		driver.get("https://www.airbnb.com/");

		// Navigate back
		driver.navigate().back();

		// Navigate forward
		driver.navigate().forward();

		// Close current browser
		driver.close();

		// Close all the browsers
		driver.quit();

	}

}
