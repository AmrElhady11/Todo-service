package com.TodaApp.entity;

import com.TodaApp.enums.ItemPriority;
import com.TodaApp.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "item_details")
public class ItemDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt ;
    @Enumerated(EnumType.STRING)
    private ItemPriority priority;
    @Enumerated(EnumType.STRING)
    private ItemStatus status;
    @OneToOne(mappedBy = "itemDetails")
    private Item item;
}
