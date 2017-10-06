package com.jdriven.demo.jdepend.pbl;

import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.config.GradleAnalyzerConfig;
import guru.nidi.codeassert.dependency.*;
import org.junit.Before;
import org.junit.Test;

import static guru.nidi.codeassert.junit.CodeAssertMatchers.hasNoCycles;
import static guru.nidi.codeassert.junit.CodeAssertMatchers.matchesRulesExactly;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerifyPackageByLayerTest {

    private AnalyzerConfig analyzerConfig;

    @Before
    public void configureAnalyzer() {
        analyzerConfig = GradleAnalyzerConfig.gradle().main();
    }


    @Test
    public void verifyPackageByLayer() {

        // Dependency rules for Packaging by Layer
        class ComJdrivenDemoJdependPbl extends DependencyRuler {


            // Rules for layer child packages
            DependencyRule controller, service, repository;

            @Override
            public void defineRules() {
                base().mayUse(base().allSub());
                controller.mayUse(service);
                service.mayUse(repository);
                repository.mustNotUse(base());
//                base().mayUse(util, dep.allSub()); //org.proj may use org.proj.util and all subpackages of org.proj.dep
//                dep.allSub().mustUse(model); //all subpackages of org.proj.dep must use org.proj.model
//                model.mayUse(util).mustNotUse(base()); //org.proj.model may use org.proj.util but not org.proj
            }
        }


        // Dependency Rules for Packaging By Feature
        class ComJdrivenDemoJdependPbf extends DependencyRuler {

            // Rules for feature child packages
            DependencyRule a, b;

            @Override
            public void defineRules() {
                base().mayUse(base().allSub());
//                  controller.mayUse(service);
//                  service.mayUse(repository);
//                  repository.mustNotUse(base());
//                base().mayUse(util, dep.allSub()); //org.proj may use org.proj.util and all subpackages of org.proj.dep
//                dep.allSub().mustUse(model); //all subpackages of org.proj.dep must use org.proj.model
//                model.mayUse(util).mustNotUse(base()); //org.proj.model may use org.proj.util but not org.proj
            }
        }


        // All dependencies are forbidden, except the ones defined in OrgProj
        // java, org, net packages may be used freely
        DependencyRules rules = DependencyRules.denyAll()
                .withRelativeRules(new ComJdrivenDemoJdependPbl())
                .withRelativeRules(new ComJdrivenDemoJdependPbf())
                .withExternals("java.*", "org.*", "net.*");


        DependencyResult result = new DependencyAnalyzer(analyzerConfig).rules(rules).analyze();
        assertThat(result, matchesRulesExactly());
    }

    @Test
    public void verifyThatThereAreNoCyclicDependencies() {
        assertThat(new DependencyAnalyzer(analyzerConfig).analyze(), hasNoCycles());
    }


}
