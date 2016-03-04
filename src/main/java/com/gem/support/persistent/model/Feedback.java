package com.gem.support.persistent.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vanhop on 3/4/16.
 */
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_id")
    @NotNull
    private String userId;

    @Column(name = "content")
    @Length(min = 1)
    private String content;

    @Column(name = "time")
    @NotNull
    private Date time;

    @Column(name = "app_version")
    private String appVersion;

    @Column(name = "os_type")
    private String osType;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "type")
    private int type;

    @Column(name = "status")
    private int status;

    public Feedback() {
    }

    public Feedback(String userId, String content, Date time, String appVersion, String osType, String deviceId, String model, String brand, int type, int status) {
        this.userId = userId;
        this.content = content;
        this.time = time;
        this.appVersion = appVersion;
        this.osType = osType;
        this.deviceId = deviceId;
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.status = status;
    }

    public Feedback(String id, String userId, String content, Date time, String appVersion, String osType, String deviceId, String model, String brand, int type, int status) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.time = time;
        this.appVersion = appVersion;
        this.osType = osType;
        this.deviceId = deviceId;
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
