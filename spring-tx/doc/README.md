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

验证方法一：

```
void noTransactionExceptionRequiresNewRequiresNew() {
    def user1 = new PropagationUser(name: "张八")
    propagationUserService1.addRequiredNew(user1)
    def user2 = new PropagationUser(name: "李九")
    propagationUserService2.addRequiredNew(user2)
    throw new RuntimeException()
}
```

验证结果：

张八、李九均插入成功

验证方法二：

```
void noTransactionRequiresNewRequiresNewException() {
    def user1 = new PropagationUser(name: "张九")
    propagationUserService1.addRequiredNew(user1)
    def user2 = new PropagationUser(name: "李十")
    propagationUserService2.addRequiredNewException(user2)
}
```

验证结果：

张九插入成功，李十插入失败

2. 场景二——外围方法开启事务

验证方法一：

```
@Transactional(rollbackFor = Throwable)
void transactionExceptionRequiresNewRequiresNew() {
    def user1 = new PropagationUser(name: "张十")
    propagationUserService1.addRequiredNew(user1)
    def user2 = new PropagationUser(name: "李十一")
    propagationUserService2.addRequiredNew(user2)
    def user3 = new PropagationUser(name: "王五")
    propagationUserService1.addRequired(user3)
    throw new RuntimeException()
}
```

验证结果：

张十、李十一均插入成功，王五插入失败

验证方法二：

```
@Transactional(rollbackFor = Throwable)
void transactionRequiresNewRequiresNewException() {
    def user1 = new PropagationUser(name: "张十一")
    propagationUserService1.addRequiredNew(user1)
    def user2 = new PropagationUser(name: "李十二")
    propagationUserService2.addRequiredNewException(user2)
    def user3 = new PropagationUser(name: "王六")
    propagationUserService1.addRequired(user3)
}
```

验证结果：

张十一插入成功，李十二、王六插入失败

验证方法三：

```
@Transactional(rollbackFor = Throwable)
void transactionTryRequiresNewRequiresNewException() {
    def user1 = new PropagationUser(name: "张十二")
    propagationUserService1.addRequiredNew(user1)
    def user2 = new PropagationUser(name: "李十三")
    try {
        propagationUserService2.addRequiredNewException(user2)
    } catch(Exception e) {
        e.printStackTrace()
    }
    def user3 = new PropagationUser(name: "王七")
    propagationUserService1.addRequired(user3)
}
```

验证结果：

张十二、王七插入成功，李十三插入失败

3. 总结

* 外围方法未开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
* 外围方法开启事务的情况下Propagation.REQUIRES_NEW修饰的内部方法依然会单独开启独立事务，**且与外部方法事务也独立**，内部方法之间、内部方法和外部方法事务均相互独立，互不干扰。

##### `Propagation.NESTED`

示例代码：

```groovy
@Service
class PropagationUserService1 {

	@Resource
	private PropagationUserMapper propagationUserMapper

	@Transactional(propagation = Propagation.NESTED)
	void addNested(PropagationUser user) {
		propagationUserMapper.insert(user)
	}

}
```

```groovy
@Service
class PropagationUserService2 {

	@Resource
	private PropagationUserMapper propagationUserMapper

	@Transactional(propagation = Propagation.NESTED)
	void addNested(PropagationUser user) {
		propagationUserMapper.insert(user)
	}

	@Transactional(propagation = Propagation.NESTED)
	void addNestedException(PropagationUser user) {
		propagationUserMapper.insert(user)
		throw new RuntimeException()
	}

}
```

1. 场景一——外围方法未开启事务

验证方法一：

```
void noTransactionExceptionNestedNested() {
    def user1 = new PropagationUser(name: "张十三")
    propagationUserService1.addNested(user1)
    def user2 = new PropagationUser(name: "李十四")
    propagationUserService2.addNested(user2)
    throw new RuntimeException()
}
```

验证结果：

张十三、李十四均插入成功

验证方法二：

```
void noTransactionNestedNestedException() {
    def user1 = new PropagationUser(name: "张十四")
    propagationUserService1.addNested(user1)
    def user2 = new PropagationUser(name: "李十五")
    propagationUserService2.addNestedException(user2)
}
```

验证结果：

张十四插入成功，李十五插入失败

2. 场景二——外围方法开启事务

验证方法一：

```
@Transactional(rollbackFor = Throwable)
void transactionExceptionNestedNested() {
    def user1 = new PropagationUser(name: "张十五")
    propagationUserService1.addNested(user1)
    def user2 = new PropagationUser(name: "李十六")
    propagationUserService2.addNested(user2)
    throw new RuntimeException()
}
```

验证结果：

张十五、李十六均未插入

验证方法二：

```
@Transactional(rollbackFor = Throwable)
void transactionNestedNestedException() {
    def user1 = new PropagationUser(name: "张十六")
    propagationUserService1.addNested(user1)
    def user2 = new PropagationUser(name: "李十七")
    propagationUserService2.addNestedException(user2)
}
```

验证结果：

张十六、李十七均未插入

验证方法三：

```
@Transactional(rollbackFor = Throwable)
void transactionTryNestedNestedException() {
    def user1 = new PropagationUser(name: "张十七")
    propagationUserService1.addNested(user1)
    def user2 = new PropagationUser(name: "李十八")
    try {
        propagationUserService2.addNestedException(user2)
    } catch(Exception e) {
        e.printStackTrace()
    }
}
```

验证结果：

张十七插入成功，李十八未插入成功

3. 总结

* 外围方法未开启事务的情况下Propagation.NESTED和Propagation.REQUIRED作用相同，修饰的内部方法都会新开启自己的事务，且开启的事务相互独立，互不干扰。
* 外围方法开启事务的情况下Propagation.NESTED修饰的内部方法属于外部事务的子事务，外围主事务回滚，子事务一定回滚，而内部子事务可以单独回滚而不影响外围主事务和其他子事务

##### 其余事务传播行为略