package com.roommake.resolver;

import com.roommake.user.security.LoginUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginUserIdAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean hasPreAuthorize = parameter.hasMethodAnnotation(PreAuthorize.class);
        boolean hasSecured = parameter.hasMethodAnnotation(Secured.class);
        return hasLoginUserIdAnnotation && (hasPreAuthorize || hasSecured);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        try {
            return (LoginUser) authentication.getPrincipal();
        } catch (Exception ex) {
            return null;
        }
    }
}