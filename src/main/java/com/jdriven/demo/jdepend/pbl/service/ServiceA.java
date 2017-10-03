package com.jdriven.demo.jdepend.pbl.service;

import com.jdriven.demo.jdepend.pbl.repository.RepositoryA;

public class ServiceA {

    private final RepositoryA repositoryA;

    public ServiceA(RepositoryA repositoryA) {
        this.repositoryA = repositoryA;
    }
}
