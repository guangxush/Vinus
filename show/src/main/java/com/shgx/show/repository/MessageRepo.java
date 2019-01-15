package com.shgx.show.repository;

import com.shgx.show.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @auther guangxush
 * @create 2019/1/15
 */
@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
}
