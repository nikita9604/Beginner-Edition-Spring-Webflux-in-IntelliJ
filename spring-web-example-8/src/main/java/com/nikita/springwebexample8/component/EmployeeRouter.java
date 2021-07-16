package com.nikita.springwebexample8.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EmployeeRouter {

    @Bean
    public RouterFunction<ServerResponse> root(EmployeeHandler employeeHandler) {
        return RouterFunctions.route(
                GET("/employee")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), employeeHandler::getAllEmployees)
                .andRoute(GET("/employee/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), employeeHandler::getEmployee)
                .andRoute(POST("/employee")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), employeeHandler::addNewEmployee)
                .andRoute(PUT("/employee/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), employeeHandler::updateEmployee)
                .andRoute(DELETE("/employee/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), employeeHandler::deleteAnEmployee);

    }
}