package com.manning.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:syl
 * @Date： 2020/12/13
 * @Description:
 */
public interface ReaderRepository extends JpaRepository<Reader,String> {//JPA持久化读者
}
