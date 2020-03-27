package org.example.advs.controllers;

import com.sun.mail.util.MailConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import org.springframework.mail.MailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    private Logger LOGGER;


    @ExceptionHandler(MailException.class)
    public String handleMailException(MailException e, Model model){
        model.addAttribute("mailsendError", "Server Error related to  registration email sending.");
        LOGGER = Logger.getLogger("application.properties");
        if (e.getCause() instanceof MailConnectException) { // parent is MailSendException
                LOGGER.log(Level.WARNING, "MailConnectException. Check out spring.mail.host data in .properties");
            }
            else if (e.getCause() instanceof AuthenticationFailedException){ // parent is MailAuthenticationException
                LOGGER.log(Level.WARNING, "AuthenticationFailedException. Check out spring.mail.password / .username data in .properties");
            }
            else {// works with spring.mail.port wrong data as example
                LOGGER.log(Level.WARNING, "MailException. We have some troubles with a mail sending in .properties");
            }
        return "errorPage";
    }

}
