package org.avol.tiny;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Runner class, execute the BDD feature files.
 *
 * Created by lovababu on 10/06/18.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        format = {
                "json:build/cucumber/tinyurl_report.json",
                "html:build/cucumber/tinyurl_report.html",
                "pretty"
        },
        features = "src/test/resources/feature",
        tags = {"~@skip"}
)
public class CucumberRunner {

}
