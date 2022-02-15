package org.fade.demo.springframework.beans;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

import java.util.List;

@Data
public class CarFactoryBean implements FactoryBean<Car> {

	private String carInfo;

	@Override
	public Car getObject() throws Exception {
		assert StrUtil.isNotBlank(carInfo);
		List<String> split = StrUtil.split(carInfo, ",");
		return new Car().setMaxSpeed(Integer.valueOf(split.get(0)))
				.setBrand(split.get(1)).setPrice(Double.valueOf(split.get(2)));
	}

	@Override
	public Class<?> getObjectType() {
		return Car.class;
	}

}
