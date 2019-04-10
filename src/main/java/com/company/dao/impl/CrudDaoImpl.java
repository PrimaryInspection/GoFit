package com.company.dao.impl;

import com.company.dao.interfaces.CrudDao;
import com.company.model.Lifestyle;

import java.util.List;

public  class CrudDaoImpl<T> implements CrudDao<T> {


    @Override
    public List<T> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T getItem(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addItem(T newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updateItem(T newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean deleteItem(int id) {
        throw new UnsupportedOperationException();
    }
}
