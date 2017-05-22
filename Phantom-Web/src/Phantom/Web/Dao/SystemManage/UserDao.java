package Phantom.Web.Dao.SystemManage;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//import Phantom.Web.Dao.BaseRepository;
import Phantom.Web.Model.User;

public interface UserDao extends JpaRepository<User,Integer> {
	
	@Query(value="select * from tb_user where username = :username  and password = :password",nativeQuery=true)
	public User Login(@Param("username") String username,@Param("password")String password);
	
}
