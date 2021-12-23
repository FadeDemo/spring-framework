package org.fade.demo.springframework.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 *
 * @author fade
 * @date 2021/12/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private int id;

	private String name;

	private int age;

	private String sex;

}
