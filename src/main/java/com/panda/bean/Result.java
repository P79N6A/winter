package com.panda.bean;

public class Result<T>{

	private boolean success = false;
	private T data = null;
	private String msg = "";
	private String code = "";
	
	
	public static <T> Result<T> success(T data){
		Result<T> r = new Result<>();
		r.setCode("200");
		r.setData(data);
		r.setSuccess(true);
		r.setMsg("success");
		return r;
	}
	
	
	public static <T> Result<T> failure(String code,String msg){
		Result<T> r = new Result<>();
		r.setCode(code);
		r.setSuccess(false);
		r.setMsg(msg);
		return r;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
