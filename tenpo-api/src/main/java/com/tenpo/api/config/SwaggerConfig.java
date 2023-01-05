package com.tenpo.api.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import springfox.documentation.service.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket swaggerApi(TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.securityContexts(Arrays.asList(securityContext()))
				.securitySchemes(Arrays.asList(apiKey()))
				.select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build()
                .produces(getProduces()).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getGlobalResponses())
                .globalResponseMessage(RequestMethod.POST, getGlobalResponses())
                .globalResponseMessage(RequestMethod.PUT, getGlobalResponses())
                .globalResponseMessage(RequestMethod.PATCH, getGlobalResponses())
                .globalResponseMessage(RequestMethod.DELETE, getGlobalResponses());
	}

	protected ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Tenpo Api")
				.description("Challenge Tenpo")
				.version("")
				.contact(new Contact("", "", "")).
				extensions(Collections.emptyList()).build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}
	
    private Set<String> getProduces() {
        return new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON_VALUE));
    }

    private List<ResponseMessage> getGlobalResponses() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(HttpStatus.OK.value()).message(HttpStatus.OK.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.UNAUTHORIZED.value()).message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value()).message(HttpStatus.BAD_REQUEST.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.FORBIDDEN.value()).message(HttpStatus.FORBIDDEN.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.METHOD_NOT_ALLOWED.value()).message(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.CONFLICT.value()).message(HttpStatus.CONFLICT.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.TOO_MANY_REQUESTS.value()).message(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()).message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase()).build(),
                new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build());
    }
}
