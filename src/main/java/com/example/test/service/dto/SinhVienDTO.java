package com.example.test.service.dto;

import com.example.test.domain.Account;
import com.example.test.domain.Lop;
import com.example.test.domain.SinhVien;
import jakarta.persistence.Tuple;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SinhVienDTO {
    private Long id;
    private String name;
    private String userName;
    private String password;

    private String className;
    public SinhVienDTO(SinhVien sinhVien, Account account, Lop lop) {
      this.id = sinhVien.getId();
      this.name = sinhVien.getName();
      this.userName = account.getName();
      this.password = account.getPassword();
      this.className = lop.getName();
    }
    public static SinhVienDTO convertToLopDTOV2(Tuple tuple) {
        SinhVienDTO dto = new SinhVienDTO();
        dto.setId(tuple.get("id", Long.class));
        dto.setName(tuple.get("name", String.class));
        dto.setUserName(tuple.get("username", String.class));
        dto.setPassword(tuple.get("password", String.class));
        return dto;
    }
    public static SinhVienDTO convertToLopDTOV3(Tuple tuple) {
        SinhVienDTO dto = new SinhVienDTO();
        dto.setId(tuple.get("id", Long.class));
        dto.setName(tuple.get("name", String.class));
        dto.setUserName(tuple.get("username", String.class));
        dto.setPassword(tuple.get("password", String.class));
        dto.setClassName(tuple.get("className", String.class));
        return dto;
    }
}
