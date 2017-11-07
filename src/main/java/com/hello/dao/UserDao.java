package com.hello.dao;

import com.hello.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class UserDao extends BaseDao<User> {

    /**
     * 根据用户名查询User对象
     * @param userName 用户名
     * @return 对应userName的User对象，如果不存在，返回null。
     */
    public User getUserByUserName(String userName){

        // 这是动态查询
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);

        Root<User> i = criteriaQuery.from(User.class);


        criteriaQuery.select(i).where(
                builder.equal(i.get("userName"), userName)
        );


        TypedQuery<User> query = getEntityManager().createQuery(criteriaQuery);
        List<User> list = query.getResultList();
        return list.size() == 0 ? null : list.get(0);

//        List<User> users = (List<User>)getHibernateTemplate().find(GET_USER_BY_USERNAME,userName);
//        if (users.size() == 0) {
//            return null;
//        }else{
//            return users.get(0);
//        }


    }

    /**
     * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
     * @param userName 用户名查询条件
     * @return 用户名前缀匹配的所有User对象
     */
    public List<User> queryUserByUserName(String userName){




        // 这是动态查询
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);

        Root<User> i = criteriaQuery.from(User.class);


        criteriaQuery.select(i).where(
                builder.like(i.get("userName"), "%" + userName + "%")
        );
        TypedQuery<User> query = getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();

//        return (List<User>)getHibernateTemplate().find(QUERY_USER_BY_USERNAME,userName+"%");
    }
}
