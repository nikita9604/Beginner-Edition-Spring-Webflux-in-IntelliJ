package com.nikita.springwebexample8.repository;

import com.nikita.springwebexample8.document.Employee;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {
}
