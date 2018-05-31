package org.wuancake.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Ericheel
 * @Description: 异常处理
 * @date 2018/5/2512:29
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
    @ExceptionHandler(RuntimeException.class)
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        System.err.println("访问" + request.getRequestURI() + "时发生错误，错误信息:" + e.toString());
        e.printStackTrace();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("errorInfo", true));
            bufferedWriter.write("访问" + request.getRequestURI() + "时发生错误，错误信息:" + e.toString());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e1) {
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
