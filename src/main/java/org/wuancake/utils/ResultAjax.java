package org.wuancake.utils;

import java.io.Serializable;

/**
 * 
 * @Description: http请求返回的最外层对象
 * @author Lord
 * @date 2017年5月22日 下午5:39:30
 * @param <T>
 */
public class ResultAjax<T> implements Serializable{
	
	private static final long serialVersionUID = -8797234859999337279L;

	/** 错误码. */
	private Integer code;
	
	/** 提示信息. */
	private String msg;
	
	/** 具体的内容. */
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
