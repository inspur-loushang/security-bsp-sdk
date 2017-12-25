package sdk.security.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.loushang.bsp.api.role.RoleServiceFactory;
import org.loushang.bsp.api.role.service.IRoleService;

import sdk.security.authc.AuthenticationProvider;
import sdk.security.service.IAuthorizationProvider;

/**
 * 授权 BSP实现类
 *
 */
public class AuthorizationProviderImpl implements IAuthorizationProvider {

    IRoleService roleService = RoleServiceFactory.getRoleService();
    
	/**
	 * 判断当前登录用户对指定资源是否有权限
	 * 
	 * @param resourceId
	 *            [资源标识]
	 *
	 * @return true[有权限]，false[无权限]
	 */
	public boolean hasPermission(String resourceId) {
	    // 获取当前登录用户
        String userId = AuthenticationProvider.getLoginUserId();
	    Set<String> resIds = roleService.getPermitOperationsList(userId);
        for (String id : resIds) {
            if(resourceId.equals(id)){
                return true;
            }               
        }  
        return false;
	}

	/**
	 * 获取当前登录用户有权限的资源信息
	 * 
	 * @param type
	 *            [资源类型，可为空]
	 *
	 * @return List，内容为Map，key分别为：rid[资源ID]，url[资源url]，type[资源类型]，. ..
	 */
	public List<Map<String, String>> getResources(String type) {
	    // 获取当前登录用户
	    String userId = AuthenticationProvider.getLoginUserId();
	    Set<String> resIds = roleService.getPermitOperationsList(userId);
        List<Map<String, String>> list =new ArrayList<Map<String, String>>();
        for (String id : resIds) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("rid", id);
            list.add(map);
        } 
        return list;
	}
}
