package com.filip.examples.springbootspringdocopenapi3.config;

import com.filip.examples.springbootspringdocopenapi3.config.models.ServerProperty;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.links.Link;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.servers.ServerVariable;
import io.swagger.v3.oas.models.servers.ServerVariables;
import io.swagger.v3.oas.models.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class OpenApiConfig {

    @Autowired
    private SpringdocProperties springdocProperties;

    private static final Logger logger = LoggerFactory.getLogger(OpenApiConfig.class);

    @Bean
    public CommandLineRunner commandLineRunner(@Value("${springdocproperties.apiinfo.title}") String title) {
        return args -> {

            logger.info("apiinfo title: " + springdocProperties.getApiInfo().getTitle());
            logger.info("apiinfo description: " + springdocProperties.getApiInfo().getDescription());
            logger.info("apiinfo termsOfService: " + springdocProperties.getApiInfo().getTermsOfService());

            logger.info("apiinfo title2: " + title);

            logger.info("apiinfo title3: " + springdocProperties.getTitle());
            logger.info("apiinfo description3: " + springdocProperties.getDescription());
            logger.info("apiinfo termsOfService3: " + springdocProperties.getTermsOfService());

            logger.info(springdocProperties.getLicenseName());

            logger.info(springdocProperties.getServers().toString());


        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info());
        openAPI.setExternalDocs(externalDocumentation());
        openAPI.setServers(servers());
        openAPI.setSecurity(security());
        openAPI.setTags(tags());
        openAPI.setPaths(paths());
        openAPI.components(components());
        openAPI.setExtensions(extensions());
        return openAPI;
    }

    private Info info() {

        Info info = new Info();
        info.setTitle(springdocProperties.getApiInfo().getTitle());
        info.setDescription(springdocProperties.getApiInfo().getDescription());
        info.setTermsOfService(springdocProperties.getApiInfo().getTermsOfService());
        info.setVersion(springdocProperties.getApiInfo().getVersion());

        Contact contact = new Contact();
        contact.setName(springdocProperties.getContactName());
        contact.setUrl(springdocProperties.getContactUrl());
        contact.setEmail(springdocProperties.getContactEmail());
        info.setContact(contact);

        License license = new License();
        license.setName(springdocProperties.getLicenseName());
        license.setUrl(springdocProperties.getLicenseUrl());

        info.setLicense(license);

        return info;

    }

    private ExternalDocumentation externalDocumentation() {
        ExternalDocumentation externalDocumentation = new ExternalDocumentation();
        externalDocumentation.setDescription("Find out more about Swagger");
        externalDocumentation.setUrl("http://swagger.io");
        return externalDocumentation;
    }

    private List<Server> servers() {

        List<Server> list = new ArrayList<>();
        for (ServerProperty ss : springdocProperties.getServers()) {
            Server server = new Server().url(ss.getUrl()).description(ss.getDescription());

            ServerVariables serverVariables = new ServerVariables();

            ServerVariable serverVariable = new ServerVariable();
            serverVariable.setDescription("");
            serverVariable.setDefault("");
            serverVariable.setEnum(Collections.emptyList());
            serverVariables.addServerVariable("variables", serverVariable);

            server.setVariables(serverVariables);

            list.add(server);
        }
        return list;
    }

    private List<SecurityRequirement> security() {
        List<SecurityRequirement> securityRequirements = new ArrayList<>();

        SecurityRequirement securityRequirement = new SecurityRequirement();
        securityRequirement.addList("api_key");
        securityRequirement.addList("petstore_auth", Arrays.asList("write:pets", "read:pets"));
        return securityRequirements;
    }

    private List<Tag> tags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag()
                .name("pet")
                .description("Everything about your Pets")
                .externalDocs(new ExternalDocumentation()
                        .url("http://swagger.io")
                        .description("Find out more about Swagger")
                )
        );
        tags.add(new Tag()
                .name("user")
                .description("Operations about user")
                .externalDocs(new ExternalDocumentation()
                        .url("http://swagger.io")
                        .description("Find out more about Swagger")
                )
        );

        return tags;
    }

    private Paths paths() {
        Paths paths = new Paths();

        return paths;
    }

    private Map<String, Object> extensions() {
        Map<String, Object> extensions = new LinkedHashMap<>();

        return extensions;
    }

    // Components part

    private Components components() {
        Components components = new Components();
        components.setSchemas(getReferentieSchemas());
        components.setResponses(getReferentieResponses());
        components.setParameters(getReferentieParameters());
        components.setExamples(getReferentieExamples());
        components.setRequestBodies(getReferentieRequestBodies());
        components.setHeaders(getReferentieHeaders());
        components.setSecuritySchemes(getReferentieSecuritySchemes());
        components.setLinks(getReferentieLinks());

        return components;
    }

    private Map<String, Schema> getReferentieSchemas() {
        Map<String, Schema> schemaMap = new LinkedHashMap<>();

        Schema stringTypeSchema = new Schema().type("string");

        schemaMap.put("stringTypeSchema", stringTypeSchema);

        return schemaMap;
    }

    private Map<String, ApiResponse> getReferentieResponses() {
        Map<String, ApiResponse> apiResponseMap = new LinkedHashMap<>();

        ApiResponse apiError = new ApiResponse()
                .description("response description")
                .content(new Content().addMediaType("application/json",
                        new MediaType().schema(new Schema().$ref("ApiError"))));

        apiResponseMap.put("apierror", apiError);

        return apiResponseMap;
    }

    private Map<String, Parameter> getReferentieParameters() {
        Map<String, Parameter> parameterMap = new LinkedHashMap<>();

        Parameter cidParam = new HeaderParameter()
                .name("X-CORRELATION-ID")
                .description("To follow the flow in the logfiles")
                .required(false)
                .example("originalcidlogflow")
                .schema(new Schema().type("string"));

        parameterMap.put("X-CORRELATION-ID", cidParam);

//        Parameter cidParam2 = new HeaderParameter()
//                .name("x-correlation-id-2")
//                .description("To follow the flow in the logfiles")
//                .required(false)
//                .example("originalcidlogflow")
//                .schema(new Parameter().("allOf", new Schema().$ref(CORRELATION_ID_HEADER_NAME)))
//                ;

        //parameterMap.put("x-correlation-id-2", cidParam2);



        Parameter lineidParam = new Parameter()
                .name("lineid")
                .description("LineId Path parameter")
                .required(true)
                .in(ParameterIn.PATH.toString())
                .examples(new HashMap<String, Example>() {
                    {
                        put("150", new Example().value(150));
                        put("250", new Example().value(250));
                    }
                })
                .schema(new Schema().type("integer").format("int32"));

        parameterMap.put("lineid", lineidParam);

        Parameter fromdatesplittedParam = new Parameter()
                .name("fromdate")
                .description("LineId Path parameter")
                .required(true)
                .in(ParameterIn.PATH.toString())
                //.example("150")
                .examples(new HashMap<String, Example>() {
                    {
                        put("150", new Example().value(150));
                        put("250", new Example().value(250));
                    }
                })
                .schema(new Schema().type("integer").format("int32"));


        parameterMap.put("fromdatesplitted", fromdatesplittedParam);

        return parameterMap;
    }

    private Map<String, Example> getReferentieExamples() {
        Map<String, Example> exampleMap = new LinkedHashMap<>();

        return exampleMap;
    }

    private Map<String, RequestBody> getReferentieRequestBodies() {
        Map<String, RequestBody> requestBodyMap = new LinkedHashMap<>();

        return requestBodyMap;
    }

    private Map<String, Header> getReferentieHeaders() {
        Map<String, Header> headerMap = new LinkedHashMap<>();

        Header cidHeader = new Header()
                .description("To follow the flow in the logfiles")
                .required(false)
                .schema(new Schema().type("string"));

        headerMap.put("X-CORRELATION-ID", cidHeader);

        return headerMap;
    }

    private Map<String, SecurityScheme> getReferentieSecuritySchemes() {
        Map<String, SecurityScheme> securitySchemeMap = new LinkedHashMap<>();

        SecurityScheme basicScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic");

        securitySchemeMap.put("basicScheme", basicScheme);

        return securitySchemeMap;
    }

    private Map<String, Link> getReferentieLinks() {
        Map<String, Link> linkMap = new LinkedHashMap<>();

        return linkMap;
    }




}
