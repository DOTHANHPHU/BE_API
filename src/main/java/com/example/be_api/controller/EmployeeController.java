package com.example.be_api.controller;

import com.example.be_api.dto.EmployeeDto;
import com.example.be_api.entity.Employee;
import com.example.be_api.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Build Add Employee REST API
    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    // Build Get All Employees REST API
    @GetMapping
    public ResponseEntity<?> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Build Update Employee REST API

    /**
     * 1. @PathVariable("id"): Đây là một annotation được sử dụng để lấy giá trị của biến động {id} từ đường dẫn và gán vào biến employeeId.
     * Trong trường hợp này, employeeId sẽ nhận giá trị từ {id} trong đường dẫn, và kiểu dữ liệu là Long.
     *
     * 2. @RequestBody: Đây là một annotation sử dụng để ánh xạ dữ liệu từ phần thân của yêu cầu HTTP (request body) vào đối tượng updatedEmployee.
     * Điều này cho phép bạn gửi thông tin của nhân viên cần cập nhật từ phía máy khách dưới dạng JSON hoặc XML và chuyển đổi nó thành một đối tượng EmployeeDto.
     *
     *
     * @param employeeId
     * @param updatedEmployee
     * @return
     */
    @PutMapping("{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long employeeId,
                                                      @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    // Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!.");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchEmployeesByKeyword(@RequestParam(value = "keyword", required = false, defaultValue = "Do") String keyword) {
        List<EmployeeDto> employees = employeeService.findByFirstNameContainingOrEmailContaining(keyword);

        if (employees.isEmpty()) {
            // Nếu không tìm thấy nhân viên nào, có thể trả về mã HTTP 404 (Not Found)
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }

        return ResponseEntity.ok(employees);
    }
}
