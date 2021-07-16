package com.nikita.springwebexample8.component;

import com.nikita.springwebexample8.document.Employee;
import com.nikita.springwebexample8.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {
    @Autowired
    EmployeeRepository employeeRepository;

    public Mono<ServerResponse> getAllEmployees(ServerRequest request) {
        Flux<Employee> employees = employeeRepository.findAll();
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employees, Employee.class);
    }
    public Mono<ServerResponse> getEmployee(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return employeeRepository.findById(id)
                .flatMap(employee -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Mono.just(employee), Employee.class)).switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> addNewEmployee(ServerRequest request) {
        Mono<Employee> employeeMono = request.bodyToMono(Employee.class);
        Mono<Employee> newEmployee = employeeMono.flatMap(employeeRepository::save);
        return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(newEmployee, Employee.class);
    }
    public Mono<ServerResponse> updateEmployee(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Mono<Employee> employeeMono = request.bodyToMono(Employee.class);
        return employeeRepository.findById(id)
                .flatMap(employee -> employeeMono.flatMap(employee1 -> {
                    employee.setFirstname(employee1.getFirstname());
                    employee.setLastname(employee1.getLastname());
                    Mono<Employee> updatedEmployee = employeeRepository.save(employee);
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(updatedEmployee, Employee.class);
                }));
    }
    public Mono<ServerResponse> deleteAnEmployee(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        return employeeRepository.findById(id)
                .flatMap(employee -> employeeRepository
                        .delete(employee)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
