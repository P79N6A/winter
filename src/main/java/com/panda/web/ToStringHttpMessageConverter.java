package com.panda.web;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.panda.bean.User;

public class ToStringHttpMessageConverter extends AbstractHttpMessageConverter<Object>{

	public ToStringHttpMessageConverter() {
		super(new MediaType("application", "tostring", Charset.forName("UTF-8")));
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return User.class == clazz;
	}

	//从请求体封装数据 对应RequestBody 用String接收
	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("UTF-8"));
	}

	//从响应体封装数据 对应ResponseBody
	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String result = t.toString();
		outputMessage.getBody().write(result.getBytes());
	}

}
