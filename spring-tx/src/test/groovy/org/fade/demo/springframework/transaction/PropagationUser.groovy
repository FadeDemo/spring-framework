package org.fade.demo.springframework.transaction

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "propagation_user")
class PropagationUser {

	@Id
	Integer id

	String name

}
