package br.com.raphaelneves.rest.webservices.infra.configs.swagger;

import org.springframework.http.MediaType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SwaggerModel {

    public static final Contact DEFAULT_CONTACT = new Contact("Raphael Neves", "http://github.com/raphaeloneves", "raphaeloneves@outlook.com");
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("User API", "This is the API for user resources", "1.0.0", null,
            DEFAULT_CONTACT, null, null);
    public static final Set<String> DEFAULT_CONSUMES_PRODUCES = new HashSet(Arrays.asList(MediaType.APPLICATION_JSON_VALUE.toString() , MediaType.APPLICATION_XML.toString()));
}
