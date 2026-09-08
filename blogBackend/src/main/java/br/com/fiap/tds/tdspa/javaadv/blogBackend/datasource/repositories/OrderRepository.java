package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.Order;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.OrderKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends
        JpaRepository<Order, OrderKey>,
        QuerydslPredicateExecutor<Order>,
        OrderRepositoryCustom {
}
