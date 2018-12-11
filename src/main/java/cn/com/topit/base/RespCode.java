package cn.com.topit.base;

public enum RespCode {
	SUCCESS(0, "请求成功"), WARN(-1, "网络异常，请稍后重试"),ERROR(2,"严重错误");

	private int code;
	private String msg;

	RespCode(int code, String msg) {
		this.code=code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
