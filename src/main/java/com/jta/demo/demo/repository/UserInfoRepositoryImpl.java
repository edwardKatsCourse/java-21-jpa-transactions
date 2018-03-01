package com.jta.demo.demo.repository;

import com.jta.demo.demo.model.User;
import com.jta.demo.demo.model.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserInfo save(UserInfo userInfo) {
        entityManager.persist(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        entityManager.merge(userInfo);

        return userInfo;
    }

    @Override
    public UserInfo findById(Integer userInfoId) {
        return entityManager.find(UserInfo.class, userInfoId);
    }

    @Override
    public UserInfo findByUser(User user) {
        String jpql = "from UserInfo ui where ui.user.id = :userId";
        List users = entityManager
                .createQuery(jpql)
                .setParameter("userId", user.getId())
                .getResultList();

        if (users.isEmpty()) {
            return null;
        }


        return (UserInfo) users.get(0);
    }

    @Override
    public List<UserInfo> findAll() {
        return entityManager
                .createQuery("from UserInfo")
                .getResultList();
    }
}
