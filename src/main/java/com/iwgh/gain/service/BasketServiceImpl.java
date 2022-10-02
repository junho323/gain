package com.iwgh.gain.service;

import com.iwgh.gain.dao.BasketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService{

    @Autowired
    private BasketDAO bkdao;
}
