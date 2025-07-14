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
                .addExclude(
                        "/doc.html",
                        "/favicon.ico",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/swagger-config",

                        // user 模块文档
                        "/api/user/v3/api-doc",
                        "/api/user/v3/api-docs",
                        "/api/user/v3/api-docs/swagger-config",
                        "/api/user/swagger-resources/**",

                        // judge 模块文档
                        "/api/judge/v3/api-doc",
                        "/api/judge/v3/api-docs",
                        "/api/judge/v3/api-docs/swagger-config",
                        "/api/judge/swagger-resources/**",

                        // question 模块文档
                        "/api/question/v3/api-doc",
                        "/api/question/v3/api-docs",
                        "/api/question/v3/api-docs/swagger-config",
                        "/api/question/swagger-resources/**",

                        // ai 模块文档
                        "/api/ai/v3/api-doc",
                        "/api/ai/v3/api-docs",
                        "/api/ai/v3/api-docs/swagger-config",
                        "/api/ai/swagger-resources/**",

                        // search 模块文档
                        "/api/search/v3/api-doc",
                        "/api/search/v3/api-docs",
                        "/api/search/v3/api-docs/swagger-config",
                        "/api/search/swagger-resources/**"
                )

                .setAuth(obj -> {
                    String path = SaHolder.getRequest().getRequestPath();

                    // 登录接口、注册接口直接放行
                    if (path.equals("/api/user/login") || path.equals("/api/user/register")) {
                        return;
                    }
                    // Step1：必须登录
                    StpUtil.checkLogin();
                    System.out.println(StpUtil.isLogin());
                    System.out.println(StpUtil.getRoleList());
                    System.out.println(StpUtil.getLoginId());
                    System.out.println(StpUtil.hasPermission("admin"));
                    // Step2：是否需要角色校验
                    SaRouter.match("/api/user/add", r -> StpUtil.checkRole("admin"));
                    SaRouter.match("/api/user/edit", r -> StpUtil.checkRoleOr("user", "admin"));

                    //Question模块
                    SaRouter.match("/api/question/add", r -> StpUtil.checkRole("admin"));
                    // 其他路径不限制角色，只需登录即可访问
                })

                .setError(e -> SaResult.error("鉴权失败：" + e.getMessage()));
    }
}
