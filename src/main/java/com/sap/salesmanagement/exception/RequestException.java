package com.sap.salesmanagement.exception;

import org.springframework.web.servlet.ModelAndView;

public class RequestException extends Exception {
    private final ModelAndView modelAndView;

    public RequestException(final ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }

    public RequestException(final String view) {
        this.modelAndView = new ModelAndView();
        this.modelAndView.setViewName(view);
    }

    public RequestException(final ModelAndView modelAndView, final String message) {
        super(message);
        this.modelAndView = modelAndView;
    }

    public RequestException(final String view, final String message) {
        super(message);
        this.modelAndView = new ModelAndView();
        this.modelAndView.setViewName(view);
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }
}
