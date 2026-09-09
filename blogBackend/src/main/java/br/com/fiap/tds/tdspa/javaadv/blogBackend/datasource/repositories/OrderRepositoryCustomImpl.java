package br.com.fiap.tds.tdspa.javaadv.blogBackend.datasource.repositories;

import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.Order;
import br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities.QOrder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findByTotalQuantityGreaterThanQueryDsl(Integer quantity) {
        QOrder order = QOrder.order;
        return new JPAQueryFactory(entityManager)
                .selectFrom(order)
                .where(
                        order.totalQuantity.gt(quantity)
                ).fetch();
    }

    @Override
    public List<Order> findByTotalQuantityGreaterThanCriteria(Integer quantity) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class);
        Root<Order> orderRoot = criteria.from(Order.class);

        List<Predicate> predicates = new LinkedList<>();
        predicates.add(builder.greaterThan(orderRoot.get("totalQuantity"),
                quantity));

        criteria.select(orderRoot).where(predicates);

        return entityManager.createQuery(criteria).getResultList();
    }
}

