package com.jdriven.fitness.packaging.by.feature;

import com.jdriven.fitness.packaging.by.feature.a.ControllerA;
import com.jdriven.fitness.packaging.by.feature.a.RepositoryA;
import com.jdriven.fitness.packaging.by.feature.a.ServiceA;
import com.jdriven.fitness.packaging.by.feature.b.ControllerB;
import com.jdriven.fitness.packaging.by.feature.b.RepositoryB;
import com.jdriven.fitness.packaging.by.feature.b.ServiceB;

public class App {

    public static void main(String... args) {
        RepositoryA repositoryA = new RepositoryA();
        ServiceA ServiceA = new ServiceA(repositoryA);


        RepositoryB repositoryB = new RepositoryB();
        ServiceB serviceB = new ServiceB(repositoryB);

        ControllerA controllerA = new ControllerA(ServiceA, serviceB);
        ControllerB controllerB = new ControllerB(serviceB);
    }
}
