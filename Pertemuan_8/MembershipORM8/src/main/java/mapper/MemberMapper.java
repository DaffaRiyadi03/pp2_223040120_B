package mapper;

import java.util.List;
import model.JenisMember;
import model.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {

    @Insert("INSERT INTO member (id, nama, jenis_member_id) VALUES (#{id}, #{nama}, #{jenisMemberId})")
    Integer insert(Member member);

    Integer update(Member member); // Hanya deklarasi metode tanpa anotasi

    @Delete("DELETE FROM member WHERE id = #{id}")
    Integer delete(Member member);

    @Select("SELECT * FROM member")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "nama", column = "nama"),
        @Result(
            property = "jenisMember",
            column = "jenis_member_id",
            javaType = JenisMember.class,
            one = @One(select = "getJenisMember")
        )
    })
    List<Member> findAll();

//    @Select("SELECT * FROM jenis_member WHERE id = #{id}")
//    @Results(value = {
//        @Result(property = "id", column = "id"),
//        @Result(property = "nama", column = "nama")
//    })
    JenisMember getJenisMember(String jenisMemberId);
}
