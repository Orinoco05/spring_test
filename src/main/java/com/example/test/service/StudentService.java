package com.example.test.service;

import com.example.test.domain.Lop;
import com.example.test.domain.SinhVien;
import com.example.test.repository.SinhVienRepository;
import com.example.test.service.dto.LopDTO;
import com.example.test.service.dto.SinhVienDTO;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // viết method lấy về thông tin của sv tên, tên lớp, username, password
public class StudentService {
    private final SinhVienRepository sinhVienRepository;

    public StudentService(SinhVienRepository sinhVienRepository) {
        this.sinhVienRepository = sinhVienRepository;
    }

    public List<SinhVienDTO> getAllLop() {
        List<Tuple> sinhViens = sinhVienRepository.getAllCustom();
        return sinhViens.stream().map(SinhVienDTO::convertToLopDTOV3).collect(Collectors.toList());
    }
}
