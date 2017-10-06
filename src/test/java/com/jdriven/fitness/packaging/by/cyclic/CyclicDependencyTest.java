package com.jdriven.fitness.packaging.by.cyclic;

import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.config.GradleAnalyzerConfig;
import guru.nidi.codeassert.dependency.DependencyAnalyzer;
import org.junit.Test;

import static guru.nidi.codeassert.junit.CodeAssertMatchers.hasNoCycles;
import static org.hamcrest.MatcherAssert.assertThat;

public class CyclicDependencyTest {

    @Test
    public void verifyThatThereAreNoCyclicDependencies() {
        /// Create an analyzer for the whole project
        AnalyzerConfig analyzerConfig = GradleAnalyzerConfig.gradle().main();

        // Check that we have no CyclicDependencies
        assertThat(new DependencyAnalyzer(analyzerConfig).analyze(), hasNoCycles());
    }
}
