package com.example.examservice;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

public class ArchitectureConventionTest {

  JavaClasses javaClasses;

  @BeforeEach
  public void init() {
    javaClasses =
        new ClassFileImporter()
            .withImportOption(new ImportOption.DoNotIncludeTests())
            .importPackages("com.example.examservice");
  }

  @Test
  @DisplayName("controller 패키지 안에 있는 클래스들은 Controller로 끝나야 한다.")
  public void controller_package_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .haveSimpleNameEndingWith("Controller");
    ArchRule annotationRule =
        classes()
            .that()
            .resideInAnyPackage("..controller")
            .should()
            .beAnnotatedWith(RestController.class)
            .orShould()
            .beAnnotatedWith(Controller.class);

    rule.check(javaClasses);
    annotationRule.check(javaClasses);
  }

  @Test
  @DisplayName("request 패키지 안의 클래스는 Reqeust 로 시작한다.")
  public void vo_request_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..request..")
            .should()
            .haveSimpleNameStartingWith("Request");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("response 패키지 안의 클래스는 Response 로 시작한다.")
  public void vo_response_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..response..")
            .should()
            .haveSimpleNameStartingWith("Response");

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("repository 패키지 안에 있는 클래스들은 Repository로 끝나고 @Repository 어노테이션이 불어있어야 한다.")
  public void repository_package_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..repository")
            .should()
            .haveSimpleNameEndingWith("Repository")
            .andShould()
            .beInterfaces();
    ArchRule annotationRule =
        classes()
            .that()
            .resideInAnyPackage("..repository")
            .should()
            .beAnnotatedWith(Repository.class);

    rule.check(javaClasses);
    annotationRule.check(javaClasses);
  }

  @Test
  @DisplayName("service 패키지 안에 있는 클래스들은 Service로 끝나고 @Service 어노테이션이 불어있어야 한다.")
  public void service_package_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..service..")
            .should()
            .haveSimpleNameEndingWith("Service")
            .andShould()
            .beAnnotatedWith(Service.class);

    rule.check(javaClasses);
  }

  @Test
  @DisplayName("config 패키지 안에 있는 클래스들은 Config로 끝나고 @Configuration 어노테이션이 불어있어야 한다.")
  public void config_package_test() {
    ArchRule rule =
        classes()
            .that()
            .resideInAnyPackage("..config..")
            .should()
            .haveSimpleNameEndingWith("Config")
            .andShould()
            .beAnnotatedWith(Configuration.class);

    rule.check(javaClasses);
  }
}
