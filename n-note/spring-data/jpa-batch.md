在JPA规范中, EntityManager是完成持久化操作的核心对象。
实体作为普通Java对象，只有在调用EntityManager将其持久化后才会变成持久化对象。
EntityManager对象在一组实体类与底层数据源之间进行 O/R 映射的管理。
它可以用来管理和更新Entity Bean, 根椐主键查找Entity Bean, 还可以通过JPQL语句查询实体。

实体的状态:

       ①、新建状态:   新创建的对象，尚未拥有持久性主键。
       ②、持久化状态：已经拥有持久性主键并和持久化建立了上下文环境
       ③、游离状态：拥有持久化主键，但是没有与持久化建立上下文环境
       ④、删除状态:  拥有持久化主键，已经和持久化建立上下文环境，但是从数据库中删除。


> https://docs.oracle.com/javaee/7/api/javax/persistence/EntityManager.html
> https://www.cnblogs.com/lone5wolf/p/10940827.html


```yaml

在配置文件里加入：

spring.jpa.properties.hibernate.jdbc.batch_size=500
spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates =true

```

```java
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
```


```xml
        //20000条数据
        //13:31:06.112 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --saveAll--end--cost--6633
        //13:31:10.992 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --batchSave--end--cost--4636
        //13:31:17.073 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --batchUpdate--end--cost--6103

        //50000条数据
        //13:31:06.112 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --saveAll--end--cost--13064
        //13:31:10.992 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --batchSave--end--cost--10477
        //13:31:17.073 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --batchUpdate--end--cost--12034

        //100000条数据
        //13:39:11.584 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --saveAll--end--cost--23443
        //13:39:32.227 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --batchSave--end--cost--20643
        //13:39:56.138 [main, ,] INFO  c.m.a.s.a.r.TaskLogRepositoryTest - --batchUpdate--end--cost--23897

        
```        