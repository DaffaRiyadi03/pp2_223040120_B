package model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.util.List;

public interface KaryawanMapper {

    @Select("SELECT * FROM karyawan")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "nama", column = "nama"),
        @Result(property = "jabatan", column = "jabatan"),
        @Result(property = "status", column = "status"),
        @Result(property = "tahunMasuk", column = "tahun_masuk"),
        @Result(property = "gaji", column = "gaji")
    })
    List<Karyawan> getAllKaryawans();

    @Insert("INSERT INTO karyawan (nama, jabatan, status, tahun_masuk, gaji) "
            + "VALUES (#{nama}, #{jabatan}, #{status}, #{tahunMasuk}, #{gaji})")
    void addKaryawan(Karyawan karyawan);

    @Update("UPDATE karyawan SET nama = #{nama}, jabatan = #{jabatan}, status = #{status}, "
            + "tahun_masuk = #{tahunMasuk}, gaji = #{gaji} WHERE id = #{id}")
    void updateKaryawan(Karyawan karyawan);

    @Delete("DELETE FROM karyawan WHERE id = #{id}")
    void deleteKaryawan(int id);
}
