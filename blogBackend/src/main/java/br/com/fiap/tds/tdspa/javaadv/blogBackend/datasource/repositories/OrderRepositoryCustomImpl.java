package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.Order;

import java.util.List;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{
    @Override
    public List<Order> findByTotalQuantityGreaterThanQueryDsl(Integer quantity) {
        return List.of();
    }

    @Override
    public List<Order> findByTotalQuantityGreaterThanCriteria(Integer quantity) {
        return List.of();
    }
}
