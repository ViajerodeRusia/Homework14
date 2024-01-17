package com.EmployeeBook.Homework14.controller;
import com.EmployeeBook.Homework14.domain.Employee;
import com.EmployeeBook.Homework14.repos.EmployeeBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class EmployeeBookController {
    @Autowired
    private EmployeeBookRepo employeeBookRepo;
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }
    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Employee> employees = employeeBookRepo.findAll();
        model.put("employees", employees);
        return "main";
    }
    @PostMapping
    public String add(@RequestParam String fullName, @RequestParam String tag, Map<String, Object> model) {
        Employee employee = new Employee(fullName,tag);
        employeeBookRepo.save(employee);//сохранили
        Iterable<Employee> employees = employeeBookRepo.findAll();//взяли из репозитория
        model.put("employees", employees);//положили в модель
        return "main"; //отдали пользователю
    }
    @PostMapping("delete")
    public String delete(@RequestParam String delete, Map<String, Object> model) {
        Iterable<Employee> employees;
        if(delete != null && !delete.isEmpty()) {
            employees = employeeBookRepo.findByTag(delete);
        }else {
            employees = employeeBookRepo.findAll();
        }
        model.remove("employees", employees);//убрали из модели
        return "main"; //отдали пользователю
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Employee> employees;
        if(filter != null && !filter.isEmpty()) {
            employees = employeeBookRepo.findByTag(filter);
        }else {
            employees = employeeBookRepo.findAll(); //если тэг был пустым, то ищем весь список
        }
        model.put("employees", employees);
        return "main";
    }

}
