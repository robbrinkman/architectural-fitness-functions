package com.jdriven.fitness.packaging.by.layer.service;

import com.jdriven.fitness.packaging.by.layer.repository.RepositoryA;

public class ServiceA {

    private final RepositoryA repositoryA;

    public ServiceA(RepositoryA repositoryA) {
        this.repositoryA = repositoryA;
    }
}
