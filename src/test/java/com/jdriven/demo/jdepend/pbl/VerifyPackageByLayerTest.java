package com.jdriven.demo.jdepend.pbl;

import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.dependency.DependencyAnalyzer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.StreamSupport;

import static guru.nidi.codeassert.junit.CodeAssertMatchers.hasNoCycles;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerifyPackageByLayerTest {

    private final AnalyzerConfig config = AnalyzerConfig.maven().main("com.jdriven.demo.jdepend.pbl");

    @Before
    public void setUp() throws IOException {
    }

    @Test
    public void noCycles() {
        assertThat(new DependencyAnalyzer(config).analyze(), hasNoCycles());
    }


    @Test
    public void testForDepedencies() {
       config.getSources().forEach(System.out::println);

    }
}
