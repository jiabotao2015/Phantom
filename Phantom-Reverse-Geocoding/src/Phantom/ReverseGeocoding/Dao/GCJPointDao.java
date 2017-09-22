package Phantom.ReverseGeocoding.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Phantom.ReverseGeocoding.Entity.GCJPoint;

public interface GCJPointDao extends BaseRepository<GCJPoint, Long> {
	
	@Query(value="SELECT * FROM tb_gps_info where vehicle_id = ?1  ORDER BY point_id ASC",nativeQuery=true)
	public List<GCJPoint> GetAllPointOrderByIDASC(long vehicleId);

}
