package com.bestbookservice.repository;

import com.bestbookservice.domain.model.BestBook;
import com.bestbookservice.domain.vo.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BestBookRepository extends MongoRepository<BestBook,String> {
    Optional<BestBook> findBestBookByItem(Item item);
}
