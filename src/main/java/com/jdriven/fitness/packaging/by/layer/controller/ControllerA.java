package com.jdriven.fitness.packaging.by.layer.controller;

import com.jdriven.fitness.packaging.by.layer.service.ServiceA;

public class ControllerA {

    private final ServiceA serviceA;


    public ControllerA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
