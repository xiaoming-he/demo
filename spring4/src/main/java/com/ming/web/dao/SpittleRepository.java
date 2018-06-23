package com.ming.web.dao;

import com.ming.web.domain.Spittle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpittleRepository {

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);

    Spittle save(Spittle spittle);
}
