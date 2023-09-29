package io.learnspring.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.joran.conditional.ElseAction;

@RestController
public class UrlCheckController {
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    /**
     * @param url
     * @return
     */
    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String returnMessage = "";

        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

            System.out.println(url);
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCodeCategory = conn.getResponseCode()/100;

            if (responseCodeCategory >= 2  &&  responseCodeCategory <= 3) {
                returnMessage = SITE_IS_UP; 
              //  System.out.println("Site Down =>"+returnMessage);
            } else {
                returnMessage = SITE_IS_DOWN;
              //  System.out.println("Site Up =>"+returnMessage);
            }
            
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
          //  System.out.println("INCORRECT_URL Exception");
            returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            // TODO Auto-generated catch block
         //   System.out.println("IO Exception");
            returnMessage = SITE_IS_DOWN;
        }

        return returnMessage;
    }

}
