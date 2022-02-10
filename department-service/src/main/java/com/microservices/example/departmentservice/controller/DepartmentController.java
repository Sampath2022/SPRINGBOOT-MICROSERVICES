package com.microservices.example.departmentservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.microservices.example.departmentservice.entity.Department;
import com.microservices.example.departmentservice.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		log.info("inside department controller save method");

		return departmentService.saveDepartment(department);
	}
	
	@GetMapping("/{id}")
	public Department findDepartmentById(@PathVariable("id") Long departmentId) {
		
		log.info("inside department controller findDepartmentById method");
		return departmentService.findDepartmentById(departmentId);
	}
	
	@PutMapping("/edit/{id}")
	public Department edit(@PathParam("id") Long departmentId, @RequestBody Department department) {
		
		Department departmentInfo = new Department();
		Long departmentID2=departmentId;
		String departmentName=department.getDepartmentName();
		String departmantAddress=department.getDepartmantAddress();
		String departmentCode=department.getDepartmentCode();
		
		departmentInfo.setDepartmentId(departmentID2);
		departmentInfo.setDepartmentName(departmentName);
		departmentInfo.setDepartmantAddress(departmantAddress);
		departmentInfo.setDepartmentCode(departmentCode);
        
		return departmentService.saveDepartment(departmentInfo);
		
	}
	
	
	@PostMapping("/import-order-excel")
    public List<Department> importExcelFile(@RequestParam("file") MultipartFile files)throws IOException {
        List<Department> listOfDepartment = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        // Read student data form excel file sheet1.
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                Department department = new Department();
                Long departmentId=Long.parseLong(getCellValue(row, 0));
                String departmentName = getCellValue(row, 1);
                String departmantAddress = getCellValue(row, 2);
                String departmentCode = getCellValue(row, 3);
                
                department.setDepartmentId(departmentId);
                department.setDepartmentName(departmentName);
                department.setDepartmantAddress(departmantAddress);
                department.setDepartmentCode(departmentCode);
                
                listOfDepartment.add(department);
            }
        }
        
        departmentService.saveAll(listOfDepartment);
        
        return listOfDepartment;
    }
    private int convertStringToInt(String str) {
        int result = 0;
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        }
        result = Integer.parseInt(str);
        return result;
    }
    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }
}
