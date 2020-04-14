package org.fetisman.ads.handler;

import com.sun.mail.util.MailConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import org.springframework.mail.MailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalMailExceptionHandler {

    private Logger LOGGER = Logger.getLogger("application.properties");


    @ExceptionHandler(MailException.class)
    public String handleMailException(MailException ex, Model model){
        model.addAttribute("mailSendWarning", "Server Error related to  registration email sending.");
        if (ex.getCause() instanceof MailConnectException) {
                LOGGER.log(Level.SEVERE, "MailConnectException. Check out spring.mail.host data in .properties", ex);
            }
            else if (ex.getCause() instanceof AuthenticationFailedException){
                LOGGER.log(Level.SEVERE, "AuthenticationFailedException. Check out spring.mail.password / .username data in .properties", ex);
            }
            else {// works with spring.mail.port wrong data as example
                LOGGER.log(Level.SEVERE, "MailException. We have some troubles with a mail sending in .properties", ex);
            }
        return "mailWarnPage";
    }

}
