package com.ku.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "contents")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private Long quantity;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="service_id")
    private Service service;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
                name = "order_content_links",
                joinColumns = @JoinColumn(name = "content_id"),
                inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> orders;
}