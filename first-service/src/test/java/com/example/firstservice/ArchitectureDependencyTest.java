package com.example.firstservice;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArchitectureDependencyTest {

  JavaClasses javaClasses;

  @BeforeEach
  public void init() {
    javaClasses =
        new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("com.example.firstservice");
  }

  @Test
  @DisplayName("Controller는 Service와 Request/Response를 사용할 수 있다")
  public void controller_dependency_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..request..", "..response..", "..service..", "..http..");
    rule.check(javaClasses);
  }

  @Test
  @DisplayName("Controller는 자신 외 의존하지 않는다.")
  public void controller_not_dependent_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .onlyHaveDependentClassesThat()
            .resideInAnyPackage("..controller");
    rule.check(javaClasses);
  }

  @Test
  @DisplayName("Controller는 domain 을 사용할 수 없다.")
  public void controller_no_dependency_test() {
    ArchRule rule =
        noClasses()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..domain..");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("Service는 Controller를 의존하지 않는다.")
  public void service_no_dependency_test() {
    ArchRule rule =
        noClasses()
            .that()
            .resideInAnyPackage("..service")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..controller..");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("domain은 오직 Service와 dto, Repository에 의해 의존된다.")
  public void domain_dependent_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..domain")
            .should()
            .onlyHaveDependentClassesThat()
            .resideInAnyPackage("..service..", "..repository..", "..dto", "..domain");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("domain은 자신 외 아무것도 의존하지 않는다")
  public void domain_no_dependency_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..domain")
            .should()
            .onlyDependOnClassesThat()
            .resideInAnyPackage("..domain", "java..", "jakarta..");
    rule.check(javaClasses);
  }
}
