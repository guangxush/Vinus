package com.shgx.db.repository;

import com.shgx.db.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @auther guangxush
 * @create 2019-01-13
 */
@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
