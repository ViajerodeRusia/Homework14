package com.EmployeeBook.Homework14.repos;
import com.EmployeeBook.Homework14.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface EmployeeBookRepo extends CrudRepository<Employee, Long> {
    List<Employee> findByTag(String tag);
}
