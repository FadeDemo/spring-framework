# spring-tx

### 核心的几个类

* `TxNamespaceHandler`
* `AbstractAutoProxyCreator`
* `TransactionInterceptor`

### 事务传播行为案例

##### 参考文章：

1. [Spring事务传播行为详解](https://segmentfault.com/a/1190000013341344)

spring中的事务传播行为：

![tx#1](resources/2022-05-18_21-19.png)

这也可以通过查看 `org.springframework.transaction.annotation.Propagation` 得知

##### `Propagation.REQUIRED`

示例代码：

```groovy
@Service
class PropagationUserService1 {

	@Resource
	private PropagationUserRepository propagationUserRepository

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequired(PropagationUser user) {
		propagationUserRepository.save(user)
	}

}
```

```groovy
@Service
class PropagationUserService2 {

	@Resource
	private PropagationUserRepository propagationUserRepository

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequired(PropagationUser user){
		propagationUserRepository.save(user)
	}

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequiredException(PropagationUser user){
		propagationUserRepository.save(user)
		throw new RuntimeException()
	}

}
```

1. 场景一——外围方法未开启事务

验证方法一：

```
void noTransactionExceptionRequiredRequired(){
    def user1 = new PropagationUser(name: "张三")
    propagationUserService1.addRequired(user1)
    def user2 = new PropagationUser(name: "李四")
    propagationUserService2.addRequired(user2)
    throw new RuntimeException()
}
```

验证结果：

张三、李四均插入成功

验证方法二：

```
void noTransactionRequiredRequiredException() {
    def user1 = new PropagationUser(name: "张四")
    propagationUserService1.addRequired(user1)
    def user2 = new PropagationUser(name: "李五")
    propagationUserService2.addRequiredException(user2)
}
```

验证结果：

张四插入成功，李五未插入成功

2. 场景二——外围方法开启事务

验证方法一：

```
@Transactional(rollbackFor = Throwable)
void transactionExceptionRequiredRequired() {
    def user1 = new PropagationUser(name: "张五")
    propagationUserService1.addRequired(user1)
    def user2 = new PropagationUser(name: "李六")
    propagationUserService2.addRequired(user2)
    throw new RuntimeException()
}
```

验证结果：

张五、李六均未插入

验证方法二：

```
@Transactional(rollbackFor = Throwable)
void transactionRequiredRequiredException() {
    def user1 = new PropagationUser(name: "张六")
    propagationUserService1.addRequired(user1)
    def user2 = new PropagationUser(name: "李七")
    propagationUserService2.addRequiredException(user2)
}
```

验证结果：

张六、李七均未插入

验证方法三：

```
@Transactional(rollbackFor = Throwable)
void transactionTryRequiredRequiredException() {
    def user1 = new PropagationUser(name: "张七")
    propagationUserService1.addRequired(user1)
    def user2 = new PropagationUser(name: "李八")
    try {
        propagationUserService2.addRequiredException(user2)
    } catch (Exception e) {
        e.printStackTrace()
    }
}
```

验证结果：

张七、李八均未插入

3. 总结

* 外围方法未开启事务的情况下Propagation.REQUIRED修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
* 外围方法开启事务的情况下Propagation.REQUIRED修饰的内部方法会加入到外围方法的事务中，所有Propagation.REQUIRED修饰的内部方法和外围方法均属于同一事务，只要一个方法回滚，整个事务均回滚。此时如果内部事务发生了异常，即使是在外围方法捕获了异常，整个事务仍然回滚，这是因为该事务被设置为rollbackOnly了

##### `Propagation.REQUIRES_NEW`

示例代码：

```groovy
@Service
class PropagationUserService1 {

	@Resource
	private PropagationUserMapper propagationUserMapper

	@Transactional(propagation = Propagation.REQUIRED)
	void addRequired(PropagationUser user) {
		propagationUserMapper.insert(user)
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void addRequiredNew(PropagationUser user) {
		propagationUserMapper.insert(user)
	}

}
```

```groovy
@Service
class PropagationUserService2 {

	@Resource
	private PropagationUserMapper propagationUserMapper

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void addRequiredNew(PropagationUser user) {
		propagationUserMapper.insert(user)
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void addRequiredNewException(PropagationUser user) {
		propagationUserMapper.insert(user)
		throw new RuntimeException()
	}

}
```

1. 场景一——外围方法未开启事务

