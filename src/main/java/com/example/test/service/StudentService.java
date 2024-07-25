package com.example.test.service;

import com.example.test.domain.Account;
import com.example.test.domain.Lop;
import com.example.test.domain.SinhVien;
import com.example.test.repository.AccountRepository;
import com.example.test.repository.LopRepository;
import com.example.test.repository.SinhVienRepository;
import com.example.test.service.dto.SinhVienCreateDTO;
import com.example.test.service.dto.SinhVienDTO;
import com.example.test.service.dto.SinhVienUpdateDTO;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service // viết method lấy về thông tin của sv tên, tên lớp, username, password
public class StudentService {
    private final SinhVienRepository sinhVienRepository;
    private final LopRepository lopRepository;
    private final AccountRepository accountRepository;

    public StudentService(SinhVienRepository sinhVienRepository, LopRepository lopRepository, AccountRepository accountRepository) {
        this.sinhVienRepository = sinhVienRepository;
        this.lopRepository = lopRepository;
        this.accountRepository = accountRepository;
    }

    public List<SinhVienDTO> getAllLop() {
        List<Tuple> sinhViens = sinhVienRepository.getAllCustom();
        return sinhViens.stream().map(SinhVienDTO::convertToLopDTOV3).collect(Collectors.toList());
    }
    public List<SinhVienDTO> createMany(List<SinhVienCreateDTO> sinhVienCreateDTOs) {
        List<SinhVienDTO> sinhVienDTO = new ArrayList<>();
        Set<Long> lopIds = sinhVienCreateDTOs.stream().map(SinhVienCreateDTO::getLopId).collect(Collectors.toSet());
        List<Lop> lops = lopRepository.findAllById(lopIds);
        sinhVienCreateDTOs.forEach(sv -> {
            Lop lop = lops.stream()
                    .filter(i -> i.getId().equals(sv.getLopId())).findFirst().orElseThrow(() -> new RuntimeException("Khong tim thay lop"));
            String userName = Normalizer.normalize(sv.getName(), Normalizer.Form.NFD).trim();

            Account account = new Account();
            account.setName(userName);
            account.setPassword("123456");
            account = accountRepository.save(account);

            SinhVien sinhVien = new SinhVien();
            sinhVien.setName(sv.getName());
            sinhVien.setLopId(lop.getId());
            sinhVien.setAccountId(account.getId());
            sinhVien = sinhVienRepository.save(sinhVien);

            sinhVienDTO.add(new SinhVienDTO(sinhVien, account, lop));
        });
        return sinhVienDTO;
    }

    public SinhVienDTO update(Long id, SinhVienUpdateDTO request) {
        Optional<SinhVien> sinhvien = sinhVienRepository.findById(id);
        if(sinhvien.isEmpty()) throw new RuntimeException("Khong tim thay sinh vien");

        Optional<Account> account = accountRepository.findById(sinhvien.get().getAccountId());

        if(account.isEmpty()) throw new RuntimeException("Khong tim thay thong tin sinh vien");

        Optional<Lop> lop = lopRepository.findById(sinhvien.get().getLopId());
        if(lop.isEmpty()) throw new RuntimeException("Khong tim thay lop");

        if(request.getLopId() != null && !sinhvien.get().getLopId().equals(request.getLopId())){
            sinhvien.get().setLopId(lop.get().getId());
        }
        if(request.getName() != null && !sinhvien.get().getName().equals(request.getName())){
            sinhvien.get().setName(request.getName());
        }
        if(request.getUsername() != null && !account.get().getName().equals(request.getUsername())) {
            account.get().setName(request.getUsername());
        }
        if(request.getPassword() != null && !account.get().getPassword().equals(request.getPassword())) {
            account.get().setPassword(request.getPassword());
        }
        Account accountSave = accountRepository.save(account.get());
        SinhVien sinhVienSave = sinhVienRepository.save(sinhvien.get());
        return new SinhVienDTO(sinhVienSave, accountSave, lop.get());
    }
    public void delete(Long id) {
        Optional<SinhVien> sinhvien = sinhVienRepository.findById(id);
        if(sinhvien.isEmpty()) throw new RuntimeException("Khong tim thay sinh vien");
        Optional<Account> account = accountRepository.findById(sinhvien.get().getAccountId());
        if(account.isEmpty()) throw new RuntimeException("Khong tim thay thong tin sinh vien");
        sinhVienRepository.delete(sinhvien.get());
        accountRepository.delete(account.get());
    }
}
