package com.jdriven.demo.jdepend.pbf;

import com.jdriven.demo.jdepend.pbf.a.*;
import com.jdriven.demo.jdepend.pbf.b.*;

public class App {

    public static void main(String... args) {
        RepositoryA repositoryA = new RepositoryA();
        ServiceA serviceA = new ServiceA(repositoryA);
        ControllerA controllerA = new ControllerA(serviceA);

        RepositoryB repositoryB = new RepositoryB();
        ServiceB serviceB = new ServiceB(repositoryB);
        ControllerB controllerB = new ControllerB(serviceB);
    }
}
