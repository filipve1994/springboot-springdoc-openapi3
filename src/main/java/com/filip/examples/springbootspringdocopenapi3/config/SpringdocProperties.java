package com.filip.examples.springbootspringdocopenapi3.config;

import com.filip.examples.springbootspringdocopenapi3.config.models.ServerProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// https://www.javacodegeeks.com/2019/05/spring-boot-yaml-configuration.html
// https://www.mkyong.com/spring-boot/spring-boot-yaml-example/
// https://www.baeldung.com/spring-yaml
@Data
@Configuration
@ConfigurationProperties(value = "springdocproperties")
public class SpringdocProperties {

    @Value("${springdocproperties.apiinfo.title}")
    private String title;

    @Value("${springdocproperties.apiinfo.description}")
    private String description;

    @Value("${springdocproperties.apiinfo.terms-of-service}")
    private String termsOfService;

    @Value("${springdocproperties.apiinfo.version}")
    private String version;

    @Value("${springdocproperties.apiinfo.contact.name}")
    private String contactName;

    @Value("${springdocproperties.apiinfo.contact.url}")
    private String contactUrl;

    @Value("${springdocproperties.apiinfo.contact.email}")
    private String contactEmail;

    @Value("${springdocproperties.apiinfo.license.name}")
    private String licenseName;

    @Value("${springdocproperties.apiinfo.license.url}")
    private String licenseUrl;

    private final ApiInfo apiInfo = new ApiInfo();

    public ApiInfo apiInfo(){
        return apiInfo;
    }

    @Data
    @ConfigurationProperties(value = "apiinfo")
    public static class ApiInfo {

        private String title;

        private String description;

        private String termsOfService;

        private String version;
    }

    private List<ServerProperty> servers;

}
