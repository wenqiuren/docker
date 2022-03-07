package com.example.hello.exe.hello.service;

import com.example.hello.exe.common.api.CommonReq;
import com.example.hello.exe.common.api.CommonResponse;
import com.example.hello.exe.common.utils.MqProducerUtils;
import com.example.hello.exe.hello.domain.dto.PersonDTO;
import com.example.hello.exe.hello.mapper.HelloMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class HelloService {
    @Resource
    HelloMapper helloMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MqProducerUtils mqProducerUtils;

    public CommonResponse<List<PersonDTO>> findByCondition(CommonReq commonReq){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(commonReq.getSearchKey());
        if(!redisTemplate.hasKey(personDTO.getName())){
            List<PersonDTO> persons = helloMapper.findByCondition(personDTO);
            redisTemplate.boundListOps(personDTO.getName()).leftPushAll(persons);
            return CommonResponse.success(persons);
        }else {
            List<PersonDTO> persons = redisTemplate.boundListOps(personDTO.getName()).range(commonReq.getPageIndex()-1, commonReq.getPageSize()-1);
            return CommonResponse.success(persons);
        }
    }

    public CommonResponse<Boolean> testMq(CommonReq commonReq){
        mqProducerUtils.sendMessage(commonReq.getSearchKey(),"queue");
        return CommonResponse.success(Boolean.TRUE);
    }
}
