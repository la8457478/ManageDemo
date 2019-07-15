package io.dreamtale.mybatisplus.extend.param.converter;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.util.Date;

public class DateConverter extends AbstractConverter {
    public static final Log LOG = LogFactory.getLog(DateConverter.class);
    private static final String[] FORMATERS = new String[]{
            "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-ddTHH:mm", "yyyy-MM-ddTHH:mm:ss",
            "yyyy/MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/ddTHH:mm", "yyyy/MM/ddTHH:mm:ss"};

    public synchronized Object[] transform(Object src) {
        Object[] values = createArray(src);
        Date[] results = new Date[values.length];
        for (int i = 0; i < values.length; i++) {
            Date date = convert(values[i]);
            results[i] = date;
        }
        return results;
    }

    private Date convert(Object value) {
        Object val = value;
        if (val.getClass().isAssignableFrom(String.class)) {
            try {
                return parseDate(value.toString());
            } catch (Exception e) {
                throw new IllegalArgumentException("目标为日期类型参数，但当前参数不是有效的日期格式！");
            }
        } else if (val.getClass().isAssignableFrom(Date.class)) {
            return (Date) value;
        } else {
            throw new IllegalArgumentException("目标为日期类型参数，但当前参数不是有效的“日期字符串”或“日期类型”！");
        }
    }

    private Date parseDate(String val) throws ParseException {
        val = val.replaceAll("T", " ");
        return DateUtils.parseDate(val, FORMATERS);
    }
}
