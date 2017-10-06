package com.jdriven.demo.jdepend.pbf;

import com.jdriven.demo.jdepend.pbf.a.*;
import com.jdriven.demo.jdepend.pbf.b.*;

public class App {

    public static void main(String... args) {
        RepositoryA repositoryA = new RepositoryA();
        AService AService = new AService(repositoryA);
        AController AController = new AController(AService);

        RepositoryB repositoryB = new RepositoryB();
        ServiceB serviceB = new ServiceB(repositoryB);
        ControllerB controllerB = new ControllerB(serviceB);
    }
}
