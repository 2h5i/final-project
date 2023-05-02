package com.sparta.finalproject.common.core;

import com.sparta.finalproject.recruitment.service.RecruitmentService;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Crawling {

    private final RecruitmentService recruitmentService;

    @Scheduled(cron = "0 30 13 * * ?")
    public void createRecruitment() throws IOException {
        ChromeOptions options = new ChromeOptions();

//        System.setProperty("webdriver.chrome.driver", "/home/ubuntu/chromedriver");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//
//        options.addArguments("start-maximized");
//        options.addArguments("--headless");
//        options.addArguments("disable-infobars");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);

        for (int i = 1; i < 3; i++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            String url = "https://www.rocketpunch.com/jobs?page=" + i;
            driver.get(url);
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(By.className("job-title")));

            List<WebElement> test = driver.findElements(By.tagName("a"));

            for (WebElement t : test) {
                String hrefText = t.getAttribute("href");

                if (recruitmentService.checkRecruitment(hrefText)) {
                    continue;
                }

                if (hrefText != null && hrefText.contains("www.rocketpunch.com/jobs/")
                    && !hrefText.contains(
                    "/collections") && !hrefText.contains("/tags")) {
                    Document doc = Jsoup.connect(hrefText).get();

                    Elements titleData = doc.getElementsByClass("break job-title");
                    Elements infoData = doc.getElementsByClass("job-stat-info");
                    Elements contentData = doc.getElementsByClass("full-text");

                    if (contentData.size() == 0) {
                        contentData = doc.getElementsByClass("content break");
                    }

                    String title = titleData.text();
                    String info = infoData.text();

                    recruitmentService.createRecruitment(title, info, contentData.toString(),
                        hrefText);
                }
            }
        }
        log.info("크롤링 완료");
        driver.quit();
    }

}
