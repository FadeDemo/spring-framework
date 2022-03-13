package org.fade.demo.springframework.beans

import cn.hutool.core.date.DateUtil
import org.springframework.core.convert.converter.Converter

class String2DateConverter implements Converter<String, Date> {

	@Override
	Date convert(String source) {
		try {
			DateUtil.parse(source)
		} catch (Exception e) {
			e.printStackTrace()
			null
		}
	}

}
