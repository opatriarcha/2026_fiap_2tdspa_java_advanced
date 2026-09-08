package br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode( of={"key"})
@ToString
public class Order {

    @EmbeddedId
    private @Getter @Setter OrderKey key;

    @Column(name = "TOTAL_PRICE", precision = 10)
    private @Getter @Setter double totalPrice;

    @Column( name="TOTAL_QUANTITY", precision = 10 )
    private @Getter @Setter int totalQuantity;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter Set<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    public @Getter @Setter User user;


}
