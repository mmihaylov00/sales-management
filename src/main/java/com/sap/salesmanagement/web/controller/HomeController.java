package com.sap.salesmanagement.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    /**
     * Returns the home page of the website
     * @return The view of the home page
     */
    @GetMapping(value = {"/"})
    public ModelAndView getIndex() {
        return this.view("index");
    }
}
