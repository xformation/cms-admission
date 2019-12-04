package com.synectiks.admission.cucumber.stepdefs;

import com.synectiks.admission.AdmissionApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = AdmissionApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
