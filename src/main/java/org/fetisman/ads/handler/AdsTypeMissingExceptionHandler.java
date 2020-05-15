package org.fetisman.ads.handler;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdsTypeMissingExceptionHandler {

    @Value("${spring.mail.username}")
    private String mailUsername;

    private Logger LOGGER = Logger.getLogger("database.ad_types");


    @ExceptionHandler(NullPointerException.class)
    public String handleAdsTypeMissingException(NullPointerException ex, Model model){
        model.addAttribute("adsTypeMissingWarning", "Ad types not found. Please contact the administrator with this problem by e-mail " + mailUsername);

        LOGGER.log(Level.SEVERE, "Exception. Check for the existence of ad types in the database", ex);

        return "handlerpages/adsTypeMissingPage";
    }

}
