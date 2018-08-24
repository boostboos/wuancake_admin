package org.wuancake.utils;

/**
 * 
 * @Description: 请求结果
 * @author Lord
 * @date 2017年5月22日 下午5:40:29
 */
public class ResultUtil {
	
	public static ResultAjax success(Object object){
		ResultAjax<Object> result = new ResultAjax<Object>();
		result.setCode(0);
		result.setMsg("成功");
		result.setData(object);
		return result;
	}
	
	public static ResultAjax success(){
		return success(null);
	}
	
	public static ResultAjax error(Integer code, String msg){
		ResultAjax<Object> result = new ResultAjax<Object>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

}
