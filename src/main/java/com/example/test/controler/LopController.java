package com.example.test.controler;

import com.example.test.service.LopService;
import com.example.test.service.dto.LopDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lop")
public class LopController {
    private final LopService lopService;

    public LopController(LopService lopService) {
        this.lopService = lopService;
    }

    @GetMapping("/get-all-lop")
    public List<LopDTO> getAccountsPage(){
        return lopService.getAllLop();
    }

    @GetMapping("/{id}")
    public LopDTO getById(@PathVariable(name = "id") Integer id){
        return lopService.getById(id);
    }
}
