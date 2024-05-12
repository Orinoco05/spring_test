package com.example.test.service.dto;

import com.example.test.domain.Lop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LopDTO {
    private Long id;
    private String name;

    private List<SinhVienDTO> svs;

    public static LopDTO convertToLopDTO(Lop lop) {
        LopDTO lopDTO = new LopDTO();
        // Set properties from lop to lopDTO
        lopDTO.setId(lop.getId());
        lopDTO.setName(lop.getName());
        // add more fields as needed
        return lopDTO;
    }
}
