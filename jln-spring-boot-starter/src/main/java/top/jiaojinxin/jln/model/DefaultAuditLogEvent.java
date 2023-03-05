package top.jiaojinxin.jln.model;

import top.jiaojinxin.jln.log.event.AuditLogEvent;
import top.jiaojinxin.jln.util.JlnUtil;

import java.io.Serial;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * 默认的审计日志事件
 *
 * @author JiaoJinxin
 */
public class DefaultAuditLogEvent extends HashMap<String, Object> implements AuditLogEvent {

    @Serial
    private static final long serialVersionUID = 2626399612618082319L;

    private static final long ZERO = 0L;
    private static final String EMPTY_STRING = "";
    private static final String DEFAULT_VALUE = "-";
    private static final String SEPARATOR_STRING = "|";
    private static final String COMMA = ",";
    private static final String JSON_PREFIX = "{";
    private static final String JSON_SUFFIX = "}";
    private static final String APP_NAME = "log.audit.appName";
    private static final String PROFILE = "log.audit.profile";
    private static final String URL = "log.audit.url";
    private static final String METHOD = "log.audit.method";
    private static final String START = "log.audit.start";
    private static final String END = "log.audit.end";
    private static final String USE_TIME = "log.audit.useTime";
    private static final String CLIENT_IP = "log.audit.clientIp";
    private static final String DEVICE_TYPE = "log.audit.deviceType";
    private static final String OPERATING_SYSTEM = "log.audit.operatingSystem";
    private static final String BROWSER = "log.audit.browser";
    private static final String BROWSER_VERSION = "log.audit.browserVersion";
    private static final String DESCRIPTION = "log.audit.description";

    private Long start;

    private Long end;

    public void putAppName(String appName) {
        putKV(APP_NAME, appName);
    }

    public static String getAppNameKey() {
        return getMsg(APP_NAME);
    }

    public String getAppName() {
        return (String) getOrDefault(APP_NAME, DEFAULT_VALUE);
    }

    public void putProfile(String profile) {
        putKV(PROFILE, profile);
    }

    public static String getProfileKey() {
        return getMsg(PROFILE);
    }

    public String getProfile() {
        return (String) getOrDefault(PROFILE, DEFAULT_VALUE);
    }

    public void putUrl(String url) {
        putKV(URL, url);
    }

    public static String getUrlKey() {
        return getMsg(URL);
    }

    public String getUrl() {
        return (String) getOrDefault(URL, DEFAULT_VALUE);
    }

    public void putMethod(String method) {
        putKV(METHOD, method);
    }

    public static String getMethodKey() {
        return getMsg(METHOD);
    }

    public String getMethod() {
        return (String) getOrDefault(METHOD, DEFAULT_VALUE);
    }

    public void putStart() {
        start = System.currentTimeMillis();
        putKV(START, LocalDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault()));
    }

    public static String getStartKey() {
        return getMsg(START);
    }

    public LocalDateTime getStart() {
        Object start = get(START);
        return start == null ? LocalDateTime.MIN : (LocalDateTime) start;
    }

    public void putEnd() {
        end = System.currentTimeMillis();
        putKV(END, LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault()));
    }

    public static String getEndKey() {
        return getMsg(END);
    }

    public LocalDateTime getEnd() {
        Object end = get(END);
        return end == null ? LocalDateTime.MIN : (LocalDateTime) end;
    }

    public void putUseTime() {
        if (start != null && end != null) {
            putKV(USE_TIME, end - start);
        } else {
            putKV(USE_TIME, ZERO);
        }
    }

    public static String getUseTimeKey() {
        return getMsg(USE_TIME);
    }

    public Long getUseTime() {
        return (Long) getOrDefault(USE_TIME, ZERO);
    }

    public void putClientIp(String clientIp) {
        putKV(CLIENT_IP, clientIp);
    }

    public static String getClientIpKey() {
        return getMsg(CLIENT_IP);
    }

    public String getClientIp() {
        return (String) getOrDefault(CLIENT_IP, DEFAULT_VALUE);
    }

    public void putDeviceType(String deviceType) {
        putKV(DEVICE_TYPE, deviceType);
    }

    public static String getDeviceTypeKey() {
        return getMsg(DEVICE_TYPE);
    }

    public String getDeviceType() {
        return (String) getOrDefault(DEVICE_TYPE, DEFAULT_VALUE);
    }

    public void putOperatingSystem(String operatingSystem) {
        putKV(OPERATING_SYSTEM, operatingSystem);
    }

    public static String getOperatingSystemKey() {
        return getMsg(OPERATING_SYSTEM);
    }

    public String getOperatingSystem() {
        return (String) getOrDefault(OPERATING_SYSTEM, DEFAULT_VALUE);
    }

    public void putBrowser(String browser) {
        putKV(BROWSER, browser);
    }

    public static String getBrowserKey() {
        return getMsg(BROWSER);
    }

    public String getBrowser() {
        return (String) getOrDefault(BROWSER, DEFAULT_VALUE);
    }

    public void putBrowserVersion(String browserVersion) {
        putKV(BROWSER_VERSION, browserVersion);
    }

    public static String getBrowserVersionKey() {
        return getMsg(BROWSER_VERSION);
    }

    public String getBrowserVersion() {
        return (String) getOrDefault(BROWSER_VERSION, DEFAULT_VALUE);
    }

    public void putDescription(String description) {
        putKV(DESCRIPTION, description);
    }

    public static String getDescriptionKey() {
        return getMsg(DESCRIPTION);
    }

    public String getDescription() {
        return (String) getOrDefault(DESCRIPTION, DEFAULT_VALUE);
    }

    public String getEventJson() {
        return getEventString(COMMA, JSON_PREFIX, JSON_SUFFIX);
    }

    public String getEventString() {
        return getEventString(SEPARATOR_STRING, EMPTY_STRING, EMPTY_STRING);
    }

    private void putKV(String key, Object value) {
        put(key, Optional.ofNullable(value).orElse(DEFAULT_VALUE));
    }

    private static String getMsg(String key) {
        return JlnUtil.getMsg(key);
    }

    private String getEventString(CharSequence delimiter, CharSequence prefix, CharSequence suffix) {
        return new StringJoiner(delimiter, prefix, suffix)
                .add(String.format("%s:%s", getAppNameKey(), getAppName()))
                .add(String.format("%s:%s", getProfileKey(), getProfile()))
                .add(String.format("%s:%s", getUrlKey(), getUrl()))
                .add(String.format("%s:%s", getMethodKey(), getMethod()))
                .add(String.format("%s:%s", getStartKey(), getStart()))
                .add(String.format("%s:%s", getEndKey(), getEnd()))
                .add(String.format("%s:%s", getUseTimeKey(), getUseTime()))
                .add(String.format("%s:%s", getClientIpKey(), getClientIp()))
                .add(String.format("%s:%s", getDeviceTypeKey(), getDeviceType()))
                .add(String.format("%s:%s", getOperatingSystemKey(), getOperatingSystem()))
                .add(String.format("%s:%s", getBrowserKey(), getBrowser()))
                .add(String.format("%s:%s", getBrowserVersionKey(), getBrowserVersion()))
                .add(String.format("%s:%s", getDescriptionKey(), getDescription()))
                .toString();
    }
}