package com.sparta.finalproject.recruitment.controller;

import com.sparta.finalproject.common.core.PageWrapper;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.SearchRecruitment;
import com.sparta.finalproject.recruitment.service.RecruitmentService;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruitments")
@EnableScheduling
@Slf4j
public class RecruitmentController {

    private final RecruitmentService recruitmentService;

    @Scheduled(cron = "0 30 8 * * ?")
    public void createRecruitment() throws IOException {
        ChromeOptions options = new ChromeOptions();

        System.setProperty("webdriver.chrome.driver", "/home/ubuntu/chromedriver");
//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        options.addArguments("start-maximized");
        options.addArguments("--headless");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);

        for (int i = 1; i < 10; i++) {
            String url = "https://www.rocketpunch.com/jobs?page=" + i;
            driver.get(url);
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.elementToBeClickable(By.className("job-title")));

            List<WebElement> test = driver.findElements(By.tagName("a"));

            for (WebElement t : test) {
                String hrefText = t.getAttribute("href");

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

                    if (recruitmentService.checkRecruitment(hrefText)) {
                        driver.close();
                        driver.quit();
                        log.info("크롤링 최신화 완료");
                        return;
                    }
                    recruitmentService.createRecruitment(title, info, contentData.toString(),
                        hrefText);
                }
            }
        }
        log.info("크롤링 완료");
    }

    @GetMapping("/{recruitmentId}")
    @ResponseStatus(HttpStatus.OK)
    public RecruitmentDto.ResponseRecruitment selectRecruitmentById(@PathVariable Long
        recruitmentId) {

        return recruitmentService.selectRecruitmentById(recruitmentId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageWrapper selectRecruitments(SearchRecruitment searchRecruitment,
        @PageableDefault() Pageable pageable) {
        return PageWrapper.of(recruitmentService.selectRecruitments(pageable, searchRecruitment));
    }

    @GetMapping("/main")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> mainRecruitments() {
        return recruitmentService.mainRecruitments();
    }
}

