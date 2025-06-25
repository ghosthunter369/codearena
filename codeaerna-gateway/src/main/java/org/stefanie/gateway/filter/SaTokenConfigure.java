package org.stefanie.gateway.filter;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.*;

/**
 * [Sa-Token 权限认证] 配置类 
 * @author click33
 */
@Component
public class SaTokenConfigure implements WebMvcConfigurer {
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                .addInclude("/**")
                .addExclude("/doc.html", "/favicon.ico", "/webjars/**", "/swagger-resources/**",
                        "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/swagger-config",
                        "/api/user/v3/api-doc", "/api/user/v3/api-docs", "/api/user/v3/api-docs/swagger-config",
                        "/api/user/swagger-resources/**")

                .setAuth(obj -> {
                    String path = SaHolder.getRequest().getRequestPath();

                    // 登录接口、注册接口直接放行
                    if (path.equals("/api/user/login") || path.equals("/api/user/register")) {
                        return;
                    }
                    // Step1：必须登录
                    StpUtil.checkLogin();
                    System.out.println(StpUtil.getRoleList());
                    System.out.println(StpUtil.getLoginId());
                    System.out.println(StpUtil.hasPermission("admin"));
                    // Step2：是否需要角色校验
                    SaRouter.match("/api/user/add", r -> StpUtil.checkRole("admin"));
                    SaRouter.match("/api/user/edit", r -> StpUtil.checkRoleOr("user", "admin"));

                    // 其他路径不限制角色，只需登录即可访问
                })

                .setError(e -> SaResult.error("鉴权失败：" + e.getMessage()));
    }
}
