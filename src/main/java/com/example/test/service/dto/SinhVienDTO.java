package com.example.test.service.dto;

import com.example.test.domain.SinhVien;
import jakarta.persistence.Tuple;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SinhVienDTO {
//    private Long id;
    private String name;
    private String userName;
    private String password;

    private String className;
    public static SinhVienDTO convertToLopDTO(SinhVien sinhVien) {
        SinhVienDTO dto = new SinhVienDTO();
//        dto.setId(sinhVien.getId());
        dto.setName(sinhVien.getName());
        return dto;
    }
    public static SinhVienDTO convertToLopDTOV2(Tuple tuple) {
        SinhVienDTO dto = new SinhVienDTO();
//        dto.setId(tuple.get("id", Long.class));
        dto.setName(tuple.get("name", String.class));
        dto.setUserName(tuple.get("username", String.class));
        dto.setPassword(tuple.get("password", String.class));
        return dto;
    }
    public static SinhVienDTO convertToLopDTOV3(Tuple tuple) {
        SinhVienDTO dto = new SinhVienDTO();
//        dto.setId(tuple.get("id", Long.class));
        dto.setName(tuple.get("name", String.class));
        dto.setUserName(tuple.get("username", String.class));
        dto.setPassword(tuple.get("password", String.class));
        dto.setClassName(tuple.get("className", String.class));
        return dto;
    }
}
