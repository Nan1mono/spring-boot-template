package com.template.project.common.util;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

/**
 * @description:
 * @author: wzw
 * @time: 2019/11/22 17:30
 */
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * @param parameter 获取请求头中某个参数
     * @return
     */
    public static String getRequestHeaderInfo(String parameter) {
	  return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
														   .getHeader(
															 parameter);
    }

    /**
     * @param url    api地址
     * @param method 请求类型  例如  get post等等
     * @param params 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
     * @return : java.lang.String
     * @Author: wzw
     * @Date: 2019/12/13 15:09
     */
    public static String HttpRestClient(String url, HttpMethod method,
						    MultiValueMap<String, String> params) throws IOException {
	  SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	  requestFactory.setConnectTimeout(10 * 1000);
	  requestFactory.setReadTimeout(10 * 1000);
	  RestTemplate client = new RestTemplate(requestFactory);
	  HttpHeaders headers = new HttpHeaders();
	  //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
	  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	  HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
		params, headers);
	  //  执行HTTP请求
	  ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
	  return response.getBody();
    }

}
