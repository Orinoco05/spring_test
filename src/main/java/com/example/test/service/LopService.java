package com.example.test.service;

import com.example.test.domain.Lop;
import com.example.test.domain.SinhVien;
import com.example.test.repository.AccountRepository;
import com.example.test.repository.LopRepository;
import com.example.test.repository.SinhVienRepository;
import com.example.test.service.dto.LopDTO;
import com.example.test.service.dto.SinhVienDTO;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LopService {
    private final LopRepository lopRepository;
    private final SinhVienRepository sinhVienRepository;


    public LopService(LopRepository lopRepository, SinhVienRepository sinhVienRepository) {
        this.lopRepository = lopRepository;
        this.sinhVienRepository = sinhVienRepository;

    }

    public List<LopDTO> getAllLop(){
        List<Lop> lops = lopRepository.findAll();
        return lops.stream().map(LopDTO::convertToLopDTO).collect(Collectors.toList());
    }
    public LopDTO getById(Integer id){
        Optional<Lop> lop = lopRepository.findById(Long.valueOf(id));
        if(lop.isEmpty()){
            throw new ServiceException("Lop khong ton tai ");
        }
        LopDTO lopDTO = LopDTO.convertToLopDTO(lop.get());
        List<SinhVienDTO> sinhViens = sinhVienRepository.getAllByLopIdCustom(id)
                .stream().map(SinhVienDTO::convertToLopDTOV2).toList();
        lopDTO.setSvs(sinhViens);
        return lopDTO;
    }
}
