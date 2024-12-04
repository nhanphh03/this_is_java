package com.demo.mq.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParamVo implements Serializable {

	private static final long serialVersionUID = 5982892641450425814L;
	private Long userId;
	private String userName;

}
