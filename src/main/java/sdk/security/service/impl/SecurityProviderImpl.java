package sdk.security.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.loushang.framework.util.SpringContextHolder;

import sdk.security.service.ISecurityProvider;

/**
 * BSP 实现类
 * 
 */
public class SecurityProviderImpl implements ISecurityProvider {

    private static String contextUrl;

    static {
        // 获取安全中心的服务根地址
        // 首先假设独立部署模式
        // 从配置文件security中获取
        // sso.smal.idp.sso = http://ip:port/context/SMAL2/POST/SSO
        contextUrl = getIdpContext();
        if (contextUrl == null || "".equals(contextUrl)) {
            // 集中部署模式，获取本地地址
            contextUrl = SpringContextHolder.getApplicationContext().getApplicationName();
        }
    }

    /**
     * 获取安全中心的服务根地址
     * 
     * @return String 服务根URL
     */
    public String getSecurityContextUrl() {
        return contextUrl;

    }

    /**
     * 从 security.properties 中获取IDP的地址
     * 
     * @return
     */
    private static String getIdpContext() {
        String contextUrl = null;
        Properties prop = new Properties();
        try {
            ClassLoader ccl = SecurityProviderImpl.class.getClassLoader();
            InputStream in = ccl.getResourceAsStream("security.properties");
            prop.load(in);
            if(prop.getProperty("sso.saml.idp.sso")!=null){
                String idpsso = prop.getProperty("sso.saml.idp.sso");
                if (idpsso != null && !"".equals(idpsso.trim())) {
                    int index = idpsso.indexOf("/SAML2/POST/SSO");
                    if (index != -1) {
                        contextUrl = idpsso.substring(0, index);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contextUrl;
    }

    /**
     * 获取注销url
     * 
     * @param backUrl
     * @return
     */
    public String getLogoutUrl(String backUrl) {
        return getSecurityContextUrl() + "/logout";
    }
}
