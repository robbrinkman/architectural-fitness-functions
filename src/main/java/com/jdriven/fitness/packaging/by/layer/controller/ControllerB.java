package com.jdriven.fitness.packaging.by.layer.controller;

import com.jdriven.fitness.packaging.by.layer.service.ServiceB;

public class ControllerB {

    private final ServiceB serviceB;

    public ControllerB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
