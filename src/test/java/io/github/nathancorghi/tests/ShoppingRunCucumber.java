package io.github.nathancorghi.tests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:/features",
        glue = {"io.github.nathancorghi.tests"})
public class ShoppingRunCucumber {

}
