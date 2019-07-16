package io.dreamtale.mybatisplus.extend.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 常量
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	/**
	 * 菜单类型
	 * 
	 * @author chenshun
	 * @email sunlightcs@gmail.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public static final String CIVIL_BUSINESS_CODE = "100";

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final Long INITIAL_STATE = 0L;
    public static final Long INDATE_FINISH = 1L;
    public static final Long OUTDATE_FINISH = 2L;
    public static final Long INDATE_PROCEED = 3L;
    public static final Long OUTDATE_PROCEED = 4L;
    public static final Long INDATE_WAITING = 5L;
    public static final Long OUTDATE_WAITING = 6L;
    public static final Long NONEED = 7L;
    public static final Integer WAITING = 0;
    public static final Integer PROCEED = 1;
    public static final Integer FINISH = 2;


    public static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }

    public static String getLocalDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public static String getLocalTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(TIME_PATTERN));
    }

    public static String getLocalYM() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(MONTH_PATTERN));
    }

    public static String getDateTimeByPattern(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }
}
