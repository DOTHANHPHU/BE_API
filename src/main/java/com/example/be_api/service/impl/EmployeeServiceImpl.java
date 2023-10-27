package com.example.be_api.service.impl;

import com.example.be_api.dto.EmployeeDto;
import com.example.be_api.entity.Employee;
import com.example.be_api.controller.exception.ResourceNotFoundException;
import com.example.be_api.mapper.EmployeeMapper;
import com.example.be_api.repository.EmployeeRepository;
import com.example.be_api.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//@Service
@Component
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    // ResourceNotFoundException: Hàm này để ném ngoại lệ
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));

        // Sử dụng một lớp hoặc phương thức EmployeeMapper để ánh xạ đối tượng employee này thành một đối tượng EmployeeDto. Sau đó, phương thức trả về EmployeeDto.
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    /**
     * 1. Phương thức stream() được gọi trên danh sách employees, biến đổi danh sách này thành một luồng (stream).
     * Luồng cho phép bạn thực hiện các phép biến đổi hoặc tạo ra các phần tử mới dễ dàng.
     *
     * 2. Phương thức map được gọi trên luồng, và nó ánh xạ (map) mỗi đối tượng Employee thành một đối tượng EmployeeDto
     * Điều này tạo ra một luồng mới chứa các đối tượng EmployeeDto.
     *
     * 3. Phương thức collect được sử dụng để thu thập các phần tử trong luồng thành một danh sách (List).
     * Trong trường hợp này, nó chuyển đổi luồng các đối tượng EmployeeDto thành danh sách List<EmployeeDto> và trả về kết quả.
     * @return
     */
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    /**
     * 1. Phương thức updateEmployee() có 2 tham số:
     * - ID của nhân viên cần update
     * - Một đối tượng EmployeeDto chứa thông tin đã cập nhật của nhân viên
     *
     * 2. updatedEmployeeObj để lưu (cập nhật) thông tin của nhân viên đã cập nhật vào cơ sở dữ liệu.
     * Kết quả trả về là một đối tượng Employee đã được cập nhật.
     *
     * @param employeeId
     * @param updatedEmployee
     * @return
     */
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {

        employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with given id: " + employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }

    @Override
    public List<EmployeeDto> findByFirstNameContainingOrEmailContaining(String keyword) {
        List<Employee> employees = employeeRepository.findByFirstNameContainingOrEmailContaining(keyword, keyword);
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }


}
