package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.EmployeeDTO;
import Oracle.Partner.Tracker.entities.Employee;
import Oracle.Partner.Tracker.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> findbyId(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAllUsers());
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<Employee> registerNewUser(@RequestBody EmployeeDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.registerNewUser(user));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
//        EmployeeDTO newEmployeeDTO = employeeService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateUser(id, employeeDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id){
        employeeService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
