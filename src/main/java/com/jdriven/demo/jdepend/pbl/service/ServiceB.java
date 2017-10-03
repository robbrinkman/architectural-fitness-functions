package com.jdriven.demo.jdepend.pbl.service;

import com.jdriven.demo.jdepend.pbl.repository.RepositoryA;
import com.jdriven.demo.jdepend.pbl.repository.RepositoryB;

public class ServiceB {

    private final RepositoryB repositoryB;

    public ServiceB(RepositoryB repositoryB) {
        this.repositoryB = repositoryB;
    }
}
