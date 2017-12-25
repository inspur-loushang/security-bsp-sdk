package sdk.security.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.loushang.bsp.api.MenuServiceFactory;
import org.loushang.bsp.api.menu.IMenuService;
import org.loushang.bsp.security.context.GetBspInfo;

import sdk.security.authc.AuthenticationProvider;
import sdk.security.service.IMenuProvider;

/**
 * 菜单 BSP实现类
 *
 */
public class MenuProviderImpl implements IMenuProvider {
    
    IMenuService menuService = MenuServiceFactory.getMenuProvider();
    
	/**
	 * 获取当前登录用户有权限的菜单
	 * 
	 * @param parentId[父级菜单id,可以为空]
	 * @return
	 */
	public String getAuthzMenu(String parentId) {
	    String content="";
	    String userId = AuthenticationProvider.getLoginUserId();
	    String menuTypeId = GetBspInfo.getBspInfo().getMenuTypeId();
	    if(parentId==null){ //获取顶级菜单
	        content = menuService.getTopMenuWithJson(userId, menuTypeId);
	    } else{ //获取下级菜单
	        content = menuService.getSubMenuWithJson(userId, menuTypeId, parentId);
	    }
	    JSONObject jsonObject = JSONObject.fromObject(content);
	    JSONArray jsonArray = (JSONArray)((JSONObject)jsonObject.get("menu")).get("rows");
		return jsonArray.toString();
	}

   /**
     * 获取菜单
     * 
     * @param parentId[父级菜单id,可以为空]
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getMenu(String parentId) {
        // 暂不实现
        return null;
    }

}
