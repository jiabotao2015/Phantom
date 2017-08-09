package Phantom.API.Constant;

public class WebSocketConstant {

	// 添加车辆监控标识
	public static final String ADD_VEHICLE_MONITOR = "Add_Vehicle_Monitor";
	
	// 添加所有车辆监控标识
	public static final String ADD_ALL = "Add_All";
	
	// 删除监控车辆标识
	public static final String DELETE = "delete";
	
	// 浏览器数据请求方法
	public static final String REQUEST_METHOD = "sendtome";
	
	// 缓存数据新增事件标识
	public static final String INSERT_VEHICLE = "insert_vehicle";
	
	// 缓存数据更新时间标识
	public static final String UPDATE_VEHICLE = "update_vehicle";
	
	// 缓存数据删除事件标识
	public static final String DELETE_VEHICLE = "delete_vehicle";
	
	// 查询所有车辆信息
	public static final String QUERY_EVENT = "query_vehicle";
	
	//插入alarm
	public static final String INSERT_ALARM = "insert_alarm";

	//更新alarm
	public static final String UPDATE_ALARM = "update_alarm";
	
	// 数据操作标识
	public static final String METHOD_HEAD = "method";
	
	// 数据内容标识
	public static final String PARAMS_HEAD = "params";
	
	// 方法与参数之间的分隔符
	public static final String SPLIT_COLUMN = ";";
	
	// 参数值之间的分隔符
	public static final String SPLIT_VALUE = ",";
	
	// websocket 用户名标识
	public static final String WEBSOCKET_AUTH_NAME = "name";
	
	// websocket 密码标识
	public static final String WEBSOCKET_AUTH_PASS = "password";
	
	// 返回数据为报警数据标记
	public static final String ALARM_RETURN_FLAG = "alarm";
	
	// 返回数据为定位数据数组标记
	public static final String GPS_ALL_RETURN_FLAG = "gpsall";
	
	/**
	 * 指令结果反馈标识
	 */
	public static final String CMD_RETURN_FLAG = "command";
	public static final String ON_OFF_RETURN_FLAG = "onoff";
	
	// 返回数据为定位数据标记
	public static final String GPS_RETURN_FLAG = "gps";
	
	// 返回数据前缀
	public static final String DATA_TYPE = "datatype";
	
	// 指令缓存数据更新标识
	public static final String INT_UPDATE = "int_update";
	
	// 指令缓存数据插入标识
	public static final String INT_INSERT = "int_insert";

}
