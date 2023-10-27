package com.example.be_api.service;

import com.example.be_api.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmployeeService {

    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployeeById(Long employeeId);

    public List<EmployeeDto> getAllEmployees();

    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);

    public void deleteEmployee(Long employeeId);

    /**
     * 1. Phương thức deleteEmployee trả về 'void'. Lý do cho việc này là phương thức này không cần trả về một giá trị kết quả cụ thể sau khi xóa một nhân viên.
     * Mục tiêu chính của phương thức này là xóa thông tin của một nhân viên từ cơ sở dữ liệu, và việc này không cần trả về một đối tượng EmployeeDto hay bất kỳ giá trị kết quả nào khác.
     * <p>
     * 2. Các phương thức khác đều trả về đối tượng EmployeeDto, thể hiện kết quả của các hoạt động thêm, truy vấn, hoặc cập nhật thông tin của nhân viên.
     * <p>
     * 3. Phương thức void thường được sử dụng cho các phương thức không trả về giá trị hoặc kết quả cụ thể, mà chỉ thực hiện các thay đổi trong trạng thái của hệ thống (ví dụ: xóa, cập nhật dữ liệu) mà không cần trả về thông tin chi tiết sau khi hoàn thành.
     */

    public List<EmployeeDto> findByFirstNameContainingOrEmailContaining(String keyword);

}


