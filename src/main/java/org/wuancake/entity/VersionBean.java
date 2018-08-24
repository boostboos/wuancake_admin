package org.wuancake.entity;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Version实体类
 *
 * @author
 * @date
 */
public class VersionBean implements Serializable {
    private Integer vid;

    private Long v;

    @NotEmpty(message="链接地址不能为空")
    private String url;

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Long getV() {
        return v;
    }

    public void setV(Long v) {
        this.v = v;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
