package com.example.test.controler;

import com.example.test.service.StudentService;
import com.example.test.service.dto.LopDTO;
import com.example.test.service.dto.SinhVienCreateDTO;
import com.example.test.service.dto.SinhVienDTO;
import com.example.test.service.dto.SinhVienUpdateDTO;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create")
    public List<SinhVienDTO> createMany(
        @RequestBody List<SinhVienCreateDTO> sinhVienCreateDTOS
    ){
        return studentService.createMany(sinhVienCreateDTOS);
    }
    @PutMapping("/update/{id}")
    public SinhVienDTO update(
        @PathVariable(name = "id") Long id,
        @RequestBody SinhVienUpdateDTO sinhVienUpdateDTO
    ){
        return studentService.update(id, sinhVienUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void update(
        @PathVariable(name = "id") Long id
    ){
        studentService.delete(id);
    }

}
