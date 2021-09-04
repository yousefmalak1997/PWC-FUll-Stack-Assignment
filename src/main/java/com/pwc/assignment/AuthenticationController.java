package com.pwc.assignment;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

    @RequestMapping(value = {"/index"},method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = {"/register"},method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = {"/home"},method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = {"/editUser"},method = RequestMethod.GET)
    public ModelAndView editUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @RequestMapping(value = {"/viewUsers"},method = RequestMethod.GET)
    public ModelAndView viewUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewUsers");
        return modelAndView;
    }

    @RequestMapping(value = {"/createDep"},method = RequestMethod.GET)
    public ModelAndView createDep() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createDep");
        return modelAndView;
    }

    @RequestMapping(value = {"/viewDep"},method = RequestMethod.GET)
    public ModelAndView viewDep() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewDep");
        return modelAndView;
    }

    @RequestMapping(value = {"/editDep/{id}"},method = RequestMethod.GET)
    public ModelAndView editDep() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editDep");
        return modelAndView;
    }

    @RequestMapping(value = {"/createProjects"},method = RequestMethod.GET)
    public ModelAndView createProjects() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createProjects");
        return modelAndView;
    }

    @RequestMapping(value = {"/viewProjs"},method = RequestMethod.GET)
    public ModelAndView viewProjs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewProjs");
        return modelAndView;
    }

    @RequestMapping(value = {"/editProjects/{id}"},method = RequestMethod.GET)
    public ModelAndView editProjects() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editProjects");
        return modelAndView;
    }

    @RequestMapping(value = {"/editUserDep/**"},method = RequestMethod.GET)
    public ModelAndView editUserDep() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUserDep");
        return modelAndView;
    }

    @RequestMapping(value = {"/assignProject"},method = RequestMethod.GET)
    public ModelAndView assignProject() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("assignProject");
        return modelAndView;
    }

    @RequestMapping(value = {"/viewAssignments"},method = RequestMethod.GET)
    public ModelAndView viewAssignments() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("viewAssignments");
        return modelAndView;
    }
}
