package com.company.model.dao.mapper;

import java.util.List;

public interface CrudDao <T>{
        List<T> getAll();
        T getItem(int id);
        boolean addItem(T newEntity);
        boolean updateItem(T Entity);
        boolean deleteItem(int id);
}
