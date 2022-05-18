package org.fade.demo.springframework.transaction

import org.springframework.data.jpa.repository.JpaRepository

interface PropagationUserRepository extends JpaRepository<PropagationUser, Integer> {

}