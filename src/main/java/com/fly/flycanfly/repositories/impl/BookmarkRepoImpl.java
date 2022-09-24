package com.fly.flycanfly.repositories.impl;

import com.fly.flycanfly.entities.Bookmark;
import com.fly.flycanfly.repositories.BookmarkRepoCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookmarkRepoImpl implements BookmarkRepoCustom {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Bookmark> getBookmarkList(String userName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bookmark> criteriaQuery = criteriaBuilder.createQuery(Bookmark.class);
        Root<Bookmark> bookmarkRoot = criteriaQuery.from(Bookmark.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(bookmarkRoot.get("userAccountEntity").get("userName"), userName));
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteriaQuery).getResultList();

    }
}
