package com.lang.myshop.module.sys.interceptor;

import com.lang.myshop.common.utils.UserAgentUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MobileInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            // 是移动端
            if (UserAgentUtils.isMobileOrTablet(httpServletRequest) && !org.apache.commons.lang3.StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")) {
                modelAndView.setViewName("mobile" + modelAndView.getViewName().replace("modules", ""));
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
