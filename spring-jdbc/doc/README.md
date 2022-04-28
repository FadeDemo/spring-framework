# spring-jdbc

### `org.springframework.jdbc.core.ArgumentTypePreparedStatementSetter.setValues` 中的遍历方式当遇到传递的参数和需要的参数不一致时怎么处理？

1. 首先在创建 `ArgumentTypePreparedStatementSetter` 时，它会校验参数和参数类型两个数组的长度是否一样，如果不一样则抛出异常

![jdbc#1](resources/2022-04-28_21-32.png)

2. 上面确保了两个数组的长度相等后，在 `org.springframework.jdbc.core.ArgumentTypePreparedStatementSetter.setValues` 方法中以 `args.length` （参数数组长度）控制循环是不会在访问参数数组和参数类型数组时出现数组越界现象的

![jdbc#2](resources/2022-04-28_21-58.png)

3. 但是spring-jdbc无法保证在给 `PreparedStatement` 设置参数时是否会出现数组越界，例如下面这段测试代码：

```
@Override
void saveThrowException(User user) {
    jdbcTemplate.update("insert into user(name, age, sex) values(?, ?, ?)", 
	new Object[] {List.of(user.getName(), "test"), user.getAge(), user.getSex()}, 
	new int[] {Types.VARCHAR, Types.INTEGER, Types.VARCHAR})
}
```

除非你的参数占位符数量多于参数数组长度（=参数类型数组长度），但是这样用要小心参数类型数组内容的设置，例如：

```
@Override
void saveWithoutArrayIndexOutOfBound(User user) {
    jdbcTemplate.update("insert into user(name, sex, age) values(?, ?, ?)", 
	new Object[] {List.of(user.getName(), user.getSex()), user.getAge()}, 
	new int[] {Types.VARCHAR, Types.INTEGER})
}
```

### jdbc简易流程

todo