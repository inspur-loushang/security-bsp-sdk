package sdk.security.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.loushang.bsp.security.context.BspInfo;
import org.loushang.bsp.security.context.GetBspInfo;

import sdk.security.service.IAuthenticationProvider;

/**
 * 认证 BSP实现类
 *
 */
public class AuthenticationProviderImpl implements IAuthenticationProvider {

	/**
	 * 获取当前登录用户标识
	 * 
	 * @return String userId[用户ID]
	 */
	public String getLoginUserId() {
	    BspInfo bspInfo = GetBspInfo.getBspInfo();
        return bspInfo.getUserId();

	}

	/**
	 * 获取当前登录用户Token
	 * 
	 * @return String，token信息
	 */
	public String getToken() {
	    // 暂不支持
		return null;
	}

	/**
	 * 获取当前登录用户userId-realmname
	 * 
	 * @return
	 */
	public String getKrbPrincipalName() {
	    // 不支持
	    return null;
	}
	
	/**
	 * 获取当前登录用户的详细信息
	 * 
	 * @return Map，key分别为：
	 *         userId[用户标识]，userName[用户名]，email[邮箱地址]，...
	 */
	public Map<String, String> getLoginUserInfo() {
	    BspInfo bspInfo = GetBspInfo.getBspInfo();
        Map<String, String> userInfo = new HashMap<String, String>();
        // 获取会话处理
        String userId = bspInfo.getUserId();
        String userName = bspInfo.getUserName();
        userInfo.put("userId", userId);
        userInfo.put("userName", userName);
        return userInfo;
	}

}