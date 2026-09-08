package br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderItemKey {
    @Column(name="ORDER_ID")
    private @Getter @Setter UUID orderId;
    @Column(name="ORDER_ITEM_ID")
    private @Getter @Setter UUID orderItemId;
}
