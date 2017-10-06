package com.jdriven.fitness.packaging.by.feature;

import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.config.GradleAnalyzerConfig;
import guru.nidi.codeassert.dependency.*;
import org.junit.Test;

import static guru.nidi.codeassert.junit.CodeAssertMatchers.matchesRulesExactly;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerifyPackageByFeatureTest {

    @Test
    public void verifyPackageByFeature() {

        /// Create an analyzer config for the package we'd like to verify
        AnalyzerConfig analyzerConfig = GradleAnalyzerConfig.gradle().main("com.jdriven.fitness.packaging.by.feature");

        // Dependency Rules for Packaging By Feature
        // NOTE: the classname should match the packagename
        class ComJdrivenFitnessPackagingByFeature extends DependencyRuler {

            // Rules for feature child packages
            // NOTE: they should match the name of the sub packages
            DependencyRule a, b;

            @Override
            public void defineRules() {
                // Our App classes depends on all subpackages because it constructs all of them
                base().mayUse(base().allSub());
                a.mayUse(b);
            }
        }

        // All dependencies are forbidden, except the ones defined in ComJdrivenFitnessPackagingByFeature
        // java, org, net packages may be used freely
        DependencyRules rules = DependencyRules.denyAll()
                .withRelativeRules(new ComJdrivenFitnessPackagingByFeature())
                .withExternals("java.*", "org.*", "net.*");

        DependencyResult result = new DependencyAnalyzer(analyzerConfig).rules(rules).analyze();
        assertThat(result, matchesRulesExactly());
    }


}
