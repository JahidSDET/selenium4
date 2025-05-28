package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBrowsers {
	public static String browser = "chrome"; // chrome/firefox/edge
	public static WebDriver driver;

	public static void main(String[] args) {
		try {
			setupDriver();
			if (driver != null) {
				testWebsite();
			}
		} catch (Exception e) {
			System.err.println("Test failed: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	private static void setupDriver() {
		try {
			switch (browser.toLowerCase()) {
				case "chrome":
					WebDriverManager.chromedriver().clearResolutionCache().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--remote-allow-origins=*");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("--disable-blink-features=AutomationControlled");
					options.setExperimentalOption("excludeSwitches",
							new String[]{"enable-automation", "enable-logging"});
					driver = new ChromeDriver(options);
					break;
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					break;
				case "edge":
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					break;
				default:
					throw new IllegalArgumentException("Unsupported browser: " + browser);
			}
		} catch (Exception e) {
			System.err.println("Driver setup failed: " + e.getMessage());
			e.printStackTrace();
			driver = null;
		}
	}

	private static void testWebsite() {
		driver.get("https://way2automation.com");
		System.out.println("Page Title: " + driver.getTitle());
	}
}