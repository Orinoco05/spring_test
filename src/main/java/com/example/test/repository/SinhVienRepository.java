package com.example.test.repository;

import com.example.test.domain.SinhVien;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien,Long> {
    List<SinhVien> findAllByLopId(Integer lopId);

    @Query(
            value = "select sv.*, a.name as username, a.password from sinh_vien as sv " +
                    "inner join account as a on a.id = sv.account_id " +
                    "where sv.lop_id = :lop_id",
            nativeQuery = true
    )
    List<Tuple> getAllByLopIdCustom(@Param("lop_id") Integer lopId );
    
    @Query(
            value = "select sv.*, c.name as username, c.password, l.name as className from sinh_vien as sv " +
            "inner join account as c on c.id = sv.account_id " +
            "inner join lop as l on l.id = sv.lop_id " +
            "where sv.id = :id",
            nativeQuery = true
    )
    Tuple getByIdCustom(@Param("id") Integer id );

    @Query(
            value = "select sv.*, c.name as username, c.password, l.name as className from sinh_vien as sv " +
            "inner join account as c on c.id = sv.account_id " +
            "inner join lop as l on l.id = sv.lop_id ",
            nativeQuery = true
    )
    List<Tuple> getAllCustom();
    
}
