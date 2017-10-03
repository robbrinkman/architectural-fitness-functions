package com.jdriven.demo.jdepend.pbl.controller;

import com.jdriven.demo.jdepend.pbl.service.ServiceA;

public class ControllerA {

    private final ServiceA serviceA;

    public ControllerA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
