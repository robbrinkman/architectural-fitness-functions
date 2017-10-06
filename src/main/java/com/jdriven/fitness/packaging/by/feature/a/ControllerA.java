package com.jdriven.fitness.packaging.by.feature.a;

import com.jdriven.fitness.packaging.by.feature.b.ServiceB;

public class ControllerA {

    private final ServiceA serviceA;

    private final ServiceB serviceB;

    public ControllerA(ServiceA serviceA, ServiceB serviceB) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
    }

}
