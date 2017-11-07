package com.hello.dao;

import com.hello.entity.LoginLog;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class LoginLogDao extends BaseDao<LoginLog> {



    public List<LoginLog> getLoginLogByUserId(int id) {

        // 这是动态查询
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<LoginLog> criteriaQuery = builder.createQuery(LoginLog.class);

        Root<LoginLog> i = criteriaQuery.from(LoginLog.class);

        criteriaQuery.select(i)
//                .where(
//                builder.equal(i.get("userId"), id)
//        )
        ;

        TypedQuery<LoginLog> query = getEntityManager().createQuery(criteriaQuery);

        return query.getResultList();


    }

}
