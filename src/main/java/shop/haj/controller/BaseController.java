package shop.haj.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class BaseController {
	

	private ImmutableMap<String, String> errorCodeMap;

	/**
	 * 接口数据返回
	 * @param errorCode
	 * @param data
	 * @return
	 */
	protected Map<String,Object> rtnParam(Integer errorCode,Object data) {
		//正常的业务逻辑
		if(errorCode == 0){
			return ImmutableMap.of("code", errorCode,"data", (data == null)? new Object() : data);
		}else{
			return ImmutableMap.of("errorCode", errorCode, "errmsg", String.valueOf(data));
		}
	}


}
