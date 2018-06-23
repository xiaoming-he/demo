package com.ming.web.dao.impl;


import com.ming.web.domain.Spittle;
import com.ming.web.dao.SpittleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpittleRespositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return null;
    }

    @Override
    public Spittle findOne(long id) {
        return null;
    }

    @Override
    public Spittle save(Spittle spittle) {
        return null;
    }
}
