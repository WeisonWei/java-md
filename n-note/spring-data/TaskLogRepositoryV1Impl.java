package com.meishubao.app.service.account.repository.v1;

import com.meishubao.app.base.entity.account.TaskLog;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Iterator;


public class TaskLogRepositoryV1Impl implements TaskLogRepositoryV1 {

    private final static int BATCH_SIZE = 500;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Collection<TaskLog> batchSave(Collection<TaskLog> collection) {
        Iterator<TaskLog> iterator = collection.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            entityManager.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0) {
            entityManager.flush();
            entityManager.clear();
        }
        return collection;
    }

    @Override
    @Transactional
    public Collection<TaskLog> batchUpdate(Collection<TaskLog> collection) {
        Iterator<TaskLog> iterator = collection.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            entityManager.merge(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0) {
            entityManager.flush();
            entityManager.clear();
        }
        return collection;
    }
}
