package com.example.test.controler;

import com.example.test.service.StudentService;
import com.example.test.service.dto.LopDTO;
import com.example.test.service.dto.SinhVienDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sinh-vien")
public class SinhVienController {
    private final StudentService studentService;

    public SinhVienController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<SinhVienDTO> getAccountsPage(){
        return studentService.getAllLop();
    }

}
