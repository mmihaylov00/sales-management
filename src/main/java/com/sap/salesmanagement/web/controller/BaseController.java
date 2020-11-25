package com.sap.salesmanagement.web.controller;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    protected ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.setViewName(view);

        return modelAndView;
    }

    protected ModelAndView view(String view) {
        return this.view(view, new ModelAndView());
    }

    protected ModelAndView view(String view, String objectName, Object outputObject) {
        return new ModelAndView(view, objectName, outputObject);
    }

    protected ModelAndView redirect(String url) {
        return this.view("redirect:" + url);
    }
}
