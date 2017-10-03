package com.jdriven.demo.jdepend.pbl.controller;

import com.jdriven.demo.jdepend.pbl.service.ServiceA;
import com.jdriven.demo.jdepend.pbl.service.ServiceB;

public class ControllerB {

    private final ServiceB serviceB;

    public ControllerB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
