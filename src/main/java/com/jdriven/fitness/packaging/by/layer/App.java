package com.jdriven.fitness.packaging.by.layer;

import com.jdriven.fitness.packaging.by.layer.controller.ControllerA;
import com.jdriven.fitness.packaging.by.layer.controller.ControllerB;
import com.jdriven.fitness.packaging.by.layer.repository.RepositoryA;
import com.jdriven.fitness.packaging.by.layer.repository.RepositoryB;
import com.jdriven.fitness.packaging.by.layer.service.ServiceA;
import com.jdriven.fitness.packaging.by.layer.service.ServiceB;

public class App {

    public static void main(String... args) {
        RepositoryA repositoryA = new RepositoryA();
        ServiceA serviceA = new ServiceA(repositoryA);


        RepositoryB repositoryB = new RepositoryB();
        ServiceB serviceB = new ServiceB(repositoryB);
        ControllerB controllerB = new ControllerB(serviceB);
        ControllerA controllerA = new ControllerA(serviceA);
    }
}
