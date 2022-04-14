package com.volunteer.aspect;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.volunteer.pojo.Role;
import com.volunteer.pojo.User;
import com.volunteer.service.RoleService;
import com.volunteer.service.UserService;
import com.volunteer.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@EnableAspectJAutoProxy
public class CheckRoleAspect {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    RoleService roleService;

    /**
     * 创建切点
     */
    @Pointcut("@annotation(com.volunteer.aspect.HasRole)")
    public void CheckRole() {
    }

    /**
     * 进入切点代码前
     *
     * @return
     */
    @Before("CheckRole")
    public Object before(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            throw new RuntimeException("非法请求，token无效");
        }
        Long userId;
        try {
            userId = jwtUtil.getUserId().asLong();
        } catch (JWTDecodeException e) {
            throw new RuntimeException("401");
        }
        User user = userService.getById(userId);
        if (user == null) {
            log.warn("token 可能被破解 ！！！");
            throw new RuntimeException("该用户不存在");
        }

        try {
            if (jwtUtil.expired(token)) {
                throw new RuntimeException("无效的令牌");
            }
        } catch (JWTVerificationException e) {
            throw new RuntimeException("401");
        }

        Role role = roleService.getById(user.getRoleId());

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String[] roles;
        if (method != null) {
            HasRole hasRole = method.getAnnotation(HasRole.class);
            roles = hasRole.role();
            for (String r : roles) {
                if (StringUtils.equalsIgnoreCase(role.getDesc(), r)) {
                    return null;
                }
            }
            throw new RuntimeException("角色不匹配，权限不足");
        } else {
            throw new RuntimeException("annotation have error");
        }
    }

}
