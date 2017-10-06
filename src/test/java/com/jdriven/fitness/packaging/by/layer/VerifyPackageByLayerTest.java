package com.jdriven.fitness.packaging.by.layer;

import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.config.GradleAnalyzerConfig;
import guru.nidi.codeassert.dependency.*;
import org.junit.Test;

import static guru.nidi.codeassert.junit.CodeAssertMatchers.matchesRulesExactly;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerifyPackageByLayerTest {


    @Test
    public void verifyPackageByLayer() {

        /// Create an analyzer config for the package we'd like to verify
        AnalyzerConfig analyzerConfig = GradleAnalyzerConfig.gradle().main("com.jdriven.fitness.packaging.by.layer");

        // Dependency rules for Packaging by Layer
        // NOTE: the classname should match the packagename
        class ComJdrivenFitnessPackagingByLayer extends DependencyRuler {

            // Rules for layer child packages
            // NOTE: they should match the name of the sub packages
            DependencyRule controller, service, repository;

            @Override
            public void defineRules() {
                // Our App classes depends on all subpackages because it constructs all of them
                base().mayUse(base().allSub());
                // Controllers may use Services
                controller.mayUse(service);
                // Services may use Repositories
                service.mayUse(repository);
            }
        }

        // All dependencies are forbidden, except the ones defined in ComJdrivenFitnessPackagingByLayer
        // java, org, net packages may be used freely
        DependencyRules rules = DependencyRules.denyAll()
                .withRelativeRules(new ComJdrivenFitnessPackagingByLayer())
                .withExternals("java.*", "org.*", "net.*");

        DependencyResult result = new DependencyAnalyzer(analyzerConfig).rules(rules).analyze();

        assertThat(result, matchesRulesExactly());
    }

}
