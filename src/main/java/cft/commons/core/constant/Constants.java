package cft.commons.core.constant;

/**
 * @author daniel
 *
 */
public class Constants {

	public static final String CACHE_LOG = "[CACHE_LOG]: ";
	public static final String DAO_LOG = "[DAO_LOG]: ";
	public static final String MGR_LOG = "[MANAGER_LOG]: ";
	public static final String SVC_LOG = "[SERVICE_LOG]: ";
	public static final String CJB_LOG = "[CRONJOB_LOG]: ";
	public static final String RPT_LOG = "[REPORT_LOG]: ";
	public static final String SRL_LOG = "[SERVLET_LOG]: ";
	public static final String LSN_LOG = "[LISTENER_LOG]: ";
	public static final String UTL_LOG = "[UTIL_LOG]: ";
	public static final String CTL_LOG = "[CONTROLLER_LOG]: ";

	public static final String FLAG_Y = "Y";
	public static final String FLAG_N = "N";

	public static final String RESULT_CODE_FAIL = "fail";
	public static final String RESULT_CODE_SUCCESS = "success";

	public static final String STATUS_CODE_OPEN = "open";
	public static final String STATUS_CODE_CLOSED = "closed";
	public static final String STATUS_CODE_READY = "ready";

	public static final String STATUS_CODE_NEW = "new";
	public static final String STATUS_CODE_INREVIEW = "inReview";
	public static final String STATUS_CODE_REJECTED = "rejected";
	public static final String STATUS_CODE_APPROVED = "approved";
	public static final String STATUS_CODE_PUBLISHED = "published";

	public static final String STATUS_CODE_ENABLE = "enable";
	public static final String STATUS_CODE_DISABLE = "disable";

	public static final String STATUS_CODE_ACTIVE = "active";
	public static final String STATUS_CODE_INACTIVE = "inactive";

	public static final String C_DATETIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
	public static final String C_DATETIME_PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String C_DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
	public static final String C_DATA_PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String C_TIME_PATTERN_DEFAULT = "HH:mm:ss";

	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = C_ONE_SECOND * 60;
	public static final int C_ONE_HOUR = C_ONE_MINUTE * 60;
	public static final long C_ONE_DAY = C_ONE_HOUR * 24;
	public static final long C_ONE_YEAR = C_ONE_DAY * 365;

	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_GBK = "GBK";
	public static final String ENCODING_BIG5 = "BIG5";
	
}
