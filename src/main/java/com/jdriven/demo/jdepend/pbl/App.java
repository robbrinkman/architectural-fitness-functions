package com.jdriven.demo.jdepend.pbl;

import com.jdriven.demo.jdepend.pbl.controller.ControllerA;
import com.jdriven.demo.jdepend.pbl.controller.ControllerB;
import com.jdriven.demo.jdepend.pbl.repository.RepositoryA;
import com.jdriven.demo.jdepend.pbl.repository.RepositoryB;
import com.jdriven.demo.jdepend.pbl.service.ServiceA;
import com.jdriven.demo.jdepend.pbl.service.ServiceB;

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
