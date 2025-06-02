package com.TodaApp.repository;

import com.TodaApp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByTitle(String title);
}
