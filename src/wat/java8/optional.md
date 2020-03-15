flatMap 可用于解决：将Optional<Optional<T>> ot --> Optional<T>
通过将Optional<T> oa--> 查询返回一个 <Optional<T>> ob
oa.flatmap(a-> func(a))
