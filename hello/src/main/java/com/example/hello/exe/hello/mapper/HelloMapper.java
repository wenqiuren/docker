package com.example.hello.exe.hello.mapper;

import com.example.hello.exe.hello.domain.dto.PersonDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HelloMapper {
    @Select({"<script>",
            "select name,age from person ",
            "<where>",
                "<if test = 'name != null'>",
                    "name = #{name}",
                "</if>",
            "</where>",
    "</script>"})
    public List<PersonDTO> findByCondition(PersonDTO personDTO);
}
