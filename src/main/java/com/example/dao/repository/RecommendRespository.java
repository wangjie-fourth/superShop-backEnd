package com.example.dao.repository;

import com.example.domain.dataobject.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangjie_fourth on 2019/5/16.
 */
public interface RecommendRespository extends JpaRepository<Recommend, String> {

    // 添加新的数据
    Recommend save(Recommend recommend);

    // 根据id查询对象
    Recommend findByRecommendId(String recommendId);

}
