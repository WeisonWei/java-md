# DDD Domain Drive Design

## Key
领域： [{根对象(聚合对象): {子对象[实体 值对象 策略对象]}},{"领域服务":"领域逻辑"}]
业务逻辑分布：服务层(MVC的Service)和领域层的领域服务
沟通和编码统一语言
资源库 
工厂模式
分层架构

## Core
核心：优先关注业务领域而不是持久层的表，业务逻辑由Service层转到Domain层；
资源库 --操作--> {领域} --操作--> {领域根对象} --操作--> 领域子对象[实体 值对象 策略对象]； 
创建领域对象建议使用工厂模式；

## Summary
根据业务划分领域，需求讨论和编码统一特有术语命名(变量&方法)，将领域根对象进行细粒度拆分[实体 值对象 策略对象]，逻辑放置领域根对象内；
好处是业务越来越复杂时，只是在领域内部拓展，领域间调用依然简洁；

