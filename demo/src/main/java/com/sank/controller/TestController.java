package com.sank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sank.utils.redis.RedisClientTemplate;

@RequestMapping("/testRedis")
@RestController
public class TestController {

	@Autowired
    RedisClientTemplate redisClientTemplate;

    @GetMapping(value = "/testSet/{key}/{value}")
    public Map<String,Object> testSet(@PathVariable(value="key") String key,@PathVariable(value="value") String value){
        Map<String,Object> resp = new HashMap<String,Object>();
        if(key==null) {
        	resp.put("errCode","F");
        	resp.put("errMsg","key值为空");
        }else {
        	redisClientTemplate.setToRedis(key,value);
        	resp.put("errCode","S");
        	resp.put("errMsg",key+"=>"+redisClientTemplate.getRedis(key));
        }
        return resp;
    }
    
    @GetMapping(value="/testGet/{key}")
    public Map<String,Object> testGet(@PathVariable(value="key") String key){
    	Map<String,Object> resp = new HashMap<String,Object>();
    	if(key==null) {
        	resp.put("errCode","F");
        	resp.put("errMsg","key值为空");
        }else {
        	resp.put("errCode","S");
        	resp.put("errMsg","获取成功");
        	resp.put("value",redisClientTemplate.getRedis(key));
        }
    	return resp;
    }
	
}
