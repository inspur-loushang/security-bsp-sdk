package sdk.security.service.impl;

import java.util.Map;

import org.loushang.bsp.api.user.IUserService;
import org.loushang.bsp.api.user.UserServiceFactory;

import sdk.security.service.IUserProvider;

/**
 * 
 * 用户 BSP实现类
 */
public class UserProviderImpl implements IUserProvider {

    public IUserService userService = UserServiceFactory.getUserService();
    
    /**
     * 根据用户ID获取详细信息
     * @param userId[用户标识]
     * @return Map,key分别为：
     *          userId[用户ID]
     *          userName[用户名]
     *          email[邮箱]
     */
    public Map<String, String> getUserInfo(String userId) {
        return userService.getUserDetail(userId);
    }

}
