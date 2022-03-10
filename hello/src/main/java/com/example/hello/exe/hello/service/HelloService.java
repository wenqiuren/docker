package com.example.hello.exe.hello.service;

import com.example.hello.exe.common.model.api.CommonReq;
import com.example.hello.exe.common.model.api.CommonResponse;
import com.example.hello.exe.common.utils.MqProducerUtils;
import com.example.hello.exe.hello.domain.dto.PersonDTO;
import com.example.hello.exe.hello.mapper.HelloMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            redisTemplate.boundListOps(personDTO.getName()).leftPush(persons);
            redisTemplate.expire(personDTO.getName(),1, TimeUnit.HOURS);
            return CommonResponse.success(persons);
        }else {
            List persons = (List) redisTemplate.boundListOps(personDTO.getName()).leftPop();
            return CommonResponse.success(persons);
        }
    }

    public CommonResponse<Boolean> testMq(CommonReq commonReq){
        mqProducerUtils.sendMessage(commonReq.getSearchKey(),"queue");
        return CommonResponse.success(Boolean.TRUE);
    }
}
