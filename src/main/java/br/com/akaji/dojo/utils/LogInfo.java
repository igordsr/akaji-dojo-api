package br.com.akaji.dojo.utils;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Getter
@ToString
public class LogInfo {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private Class<?> className;
    private String methodName;
    private Map<String, Object> parameters;
    private Calendar startTime;
    private Calendar endTime;
    private Long duration;
    private Object result;

    public LogInfo(Class<?> className, String methodName) {
        this.className = className;
        this.methodName = methodName;
        this.parameters = new HashMap<>();
        this.startTime = Calendar.getInstance();
    }

    public LogInfo append(String key, Object value) {
        if (key != null && value != null) {
            this.parameters.put(key, value);
        }
        return this;
    }

    public LogInfo startLog() {
        return doStartLog(this);
    }

    public LogInfo finishLog() {
        this.endTime = Calendar.getInstance();
        this.duration = this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis();
        this.result = "void";
        return doFinishLog(this);
    }

    public LogInfo finishLog(Object result) {
        this.endTime = Calendar.getInstance();
        this.duration = this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis();
        this.result = result;
        return doFinishLog(this);
    }

    private static LogInfo doStartLog(LogInfo logInfo) {
        final StringBuilder args = new StringBuilder();
        args.append("[{}::{} method] [STARTED]");
        logInfo.getParameters().forEach((key, value) -> args.append(LINE_SEPARATOR).append("Param ").append(key).append("=").append("{}"));
        log.debug(args.toString(), logInfo.getClassName(), logInfo.getMethodName(), logInfo.getParameters().values());
        return logInfo;
    }

    private static LogInfo doFinishLog(LogInfo logInfo) {
        final Format format = new SimpleDateFormat("HH:mm:ss.SSS");
        final String args = "[{}::{} method] [FINISHED]" + LINE_SEPARATOR +
                "Return Value: {}" + LINE_SEPARATOR +
                "Time Execution: Start Time: {} End Time: {}" + LINE_SEPARATOR +
                "Duration: {} millis" + LINE_SEPARATOR;
        log.debug(args, logInfo.getClassName(), logInfo.getMethodName(), logInfo.getResult(), format.format(logInfo.getStartTime().getTime()), format.format(logInfo.getEndTime().getTime()), logInfo.getDuration());
        return logInfo;
    }
}
