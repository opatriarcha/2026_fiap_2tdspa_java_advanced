package br.com.fiap.tds.tdspa.javaadv.blogBackend.domainmodel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderKey {

    @Column(name = "USER_ID")
    private @Getter @Setter UUID userId;
    @Column(name = "ORDER_ID")
    private @Getter @Setter UUID orderId;
}
