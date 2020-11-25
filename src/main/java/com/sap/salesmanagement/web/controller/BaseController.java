package com.sap.salesmanagement.web.controller;

import com.sap.salesmanagement.exception.RequestException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {
    private final Logger logger;

    public BaseController(Logger logger) {
        this.logger = logger;
    }

    protected ModelAndView view(final String view, final ModelAndView modelAndView) {
        modelAndView.setViewName(view);

        return modelAndView;
    }

    protected ModelAndView view(final String view) {
        return this.view(view, new ModelAndView());
    }

    protected ModelAndView view(final String view, final String objectName, final Object outputObject) {
        return new ModelAndView(view, objectName, outputObject);
    }

    protected ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(final HttpServletRequest req, final Exception e) {

        //if the error is of type BaseRequestException - return it's model and view
        if (e instanceof RequestException) {
            if (e.getMessage() != null && !e.getMessage().isEmpty())
                this.logger.info(e.getMessage());
            return ((RequestException) e).getModelAndView();
        }

        //else log the error
        this.logger.error("An unhandled error occurred", e);
        //and return 404 page
        //todo change it to another page?
        return this.view("404");
    }
}
