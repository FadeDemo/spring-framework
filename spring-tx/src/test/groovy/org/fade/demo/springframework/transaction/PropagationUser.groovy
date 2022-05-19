package org.fade.demo.springframework.transaction

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

@TableName("propagation_user")
class PropagationUser {

	@TableId(value = "id", type = IdType.AUTO)
	Integer id

	@TableField("name")
	String name

}
