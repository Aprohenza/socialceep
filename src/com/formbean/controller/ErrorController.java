package com.formbean.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
 
    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest) {
         
        ModelAndView errorPage = new ModelAndView("error/404.jsp");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        
        System.out.println("HTTP REQUEST CODE: " + httpErrorCode);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                return "errors/404";
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return "errors/404";
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
    	
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}