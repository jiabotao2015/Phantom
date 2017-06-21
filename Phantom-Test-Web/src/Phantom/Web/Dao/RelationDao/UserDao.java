package Phantom.Web.Dao.RelationDao;

import org.springframework.data.jpa.repository.JpaRepository;

import Phantom.Web.Entity.RelationEntity.User;

public interface UserDao extends JpaRepository<User,Integer> {

}
