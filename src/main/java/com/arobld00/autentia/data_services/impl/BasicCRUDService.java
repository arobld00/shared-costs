package com.arobld00.autentia.data_services.impl;

import java.util.List;

public interface BasicCRUDService<T> {

    T create(T dto);

    List<T> readAll();

}