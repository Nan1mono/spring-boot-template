package com.template.project.mapper.test1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.template.project.model.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper extends BaseMapper<Person> {
}
