package ds.dao;


import ds.pojo.Account;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author Qinghan Huang
 * @Desc
 * @Version 1.0
 */
@Mapper
@Repository
public interface AccountMapper {
    @Select("select * from account where username=#{text} or email=#{text}")
    Account findAccountByUsernameOrEmail(@Param("text") String  text);

    @Insert("insert into account(username,password,email) values(#{username},#{password},#{email}) ")
    int creatAccount(@Param("username") String username,@Param("password") String password, @Param("email") String email);

}
