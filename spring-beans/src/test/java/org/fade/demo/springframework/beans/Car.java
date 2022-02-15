package org.fade.demo.springframework.beans;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Car {

	private int maxSpeed;

	private String brand;

	private double price;

}
