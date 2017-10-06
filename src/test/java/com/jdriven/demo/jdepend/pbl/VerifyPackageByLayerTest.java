package com.jdriven.demo.jdepend.pbl;

import com.structurizr.Workspace;
import com.structurizr.analysis.ComponentFinder;
import com.structurizr.analysis.NameSuffixTypeMatcher;
import com.structurizr.analysis.TypeMatcherComponentFinderStrategy;
import com.structurizr.model.*;
import guru.nidi.codeassert.config.AnalyzerConfig;
import guru.nidi.codeassert.config.GradleAnalyzerConfig;
import guru.nidi.codeassert.dependency.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.StreamSupport;

import static guru.nidi.codeassert.junit.CodeAssertMatchers.hasNoCycles;
import static guru.nidi.codeassert.junit.CodeAssertMatchers.matchesRulesExactly;
import static guru.nidi.codeassert.junit.CodeAssertMatchers.matchesRulesIgnoringNonExisting;
import static org.hamcrest.MatcherAssert.assertThat;

public class VerifyPackageByLayerTest {


    @Test
    public void verifyThatThereArenoCyclicDependencies() {
        AnalyzerConfig config = GradleAnalyzerConfig.gradle().main();

//        config.getSourcePaths().forEach(System.out::println);
//        config.getClasses().forEach(System.out::println);
//        config.getSources().forEach(System.out::println);
        assertThat(new DependencyAnalyzer(config).analyze(), hasNoCycles());
    }


    @Test
    public void voidStructurizer() throws Exception {
        Workspace workspace = new Workspace("Big Bank plc", "This is an example workspace to illustrate the key features of Structurizr, based around a fictional online banking system.");
        Model model = workspace.getModel();
        SoftwareSystem mySoftwareSystem = model.addSoftwareSystem(Location.Internal, "Internet Banking System", "Allows customers to view information about their bank accounts and make payments.");

        Container webApplication = mySoftwareSystem.addContainer("Web Application", "Description", "Apache Tomcat 7.x");
        ComponentFinder componentFinder = new ComponentFinder(webApplication, "com.jdriven.demo.jdepend.pbf", new TypeMatcherComponentFinderStrategy(
                new NameSuffixTypeMatcher("Controller", "", ""),
                new NameSuffixTypeMatcher("Service", "", "")));
//           ComponentFinder componentFinder = new ComponentFinder(
//                   webApplication, "com.mycompany.mysoftwaresystem",
        Set<Component> components = componentFinder.findComponents();
        components.forEach(component -> {
            component.getCode().forEach(codeElement -> {
                CodeElement e;
                System.out.println(codeElement.getCategory());
                System.out.println(codeElement.getType());
                System.out.println(codeElement.getLanguage());
                System.out.println(codeElement.getName());
                System.out.println(codeElement.getVisibility());
            });
            component.getRelationships().forEach(System.out::println);
        });
    }

    //       @Ignore
    @Test
    public void verifyPackageByLayer() {
        AnalyzerConfig config = GradleAnalyzerConfig.gradle().main();

        config.getClasses().forEach(System.out::println);

        class ComJdrivenDemoJdependPbl extends DependencyRuler {
            // Rules for org.proj.dep, org.proj.model, org.proj.util
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

        class ComJdrivenDemoJdependPbf extends DependencyRuler {
            // Rules for org.proj.dep, org.proj.model, org.proj.util
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

        config.getSourcePaths().forEach(System.out::println);
        config.getSources().forEach(System.out::println);

        DependencyResult result = new DependencyAnalyzer(config).rules(rules).analyze();
        assertThat(result, matchesRulesExactly());
    }


}
