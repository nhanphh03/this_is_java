package com.demo.mq.consumer;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.demo.mq.util.QueueList;
import com.demo.mq.vo.ParamVo;

@Component
public class MessageHandler {

	@JmsListener(destination = QueueList.BUSINESS2)
	public void receiveQueueObj(String txtMsg) {
		ParamVo paramVo = JSON.parseObject(txtMsg, ParamVo.class);// 推荐该转换方案
		System.out.println("##activemq.queue#" + JSON.toJSONString(paramVo));
	}

	@JmsListener(destination = QueueList.BUSINESS1)
	public void receiveQueue(String txtMsg) {
		Map<String, Object> map = JSON.parseObject(txtMsg);
		System.out.println("##activemq.queue#" + map);
	}

}
