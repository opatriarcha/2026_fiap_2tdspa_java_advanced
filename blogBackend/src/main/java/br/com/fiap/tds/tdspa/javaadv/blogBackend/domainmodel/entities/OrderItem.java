package br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDER_ITENS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @EmbeddedId
    private @Getter @Setter OrderItemKey key;

    @Column(name = "PRODUCT_NAME", length = 60)
    private @Getter @Setter String productName;

    @Column(name = "PRICE", precision = 10)
    private @Getter @Setter double price;

    @Column(name = "QUANTITY")
    private @Getter @Setter int quantity;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID", insertable = false, updatable = false),
            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    })
    private Order order;
}
