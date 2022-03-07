package org.fade.demo.springframework.beans

import org.springframework.beans.propertyeditors.PropertiesEditor
import org.springframework.lang.Nullable

import java.text.SimpleDateFormat

class DatePropertyEditor extends PropertiesEditor {

	String format

	DatePropertyEditor() {
		this.format = "yyyy-MM-dd"
	}

	@Override
	void setAsText(@Nullable String text) throws IllegalArgumentException {
		println "arg:" + text
		def sdf = new SimpleDateFormat(format)
		def date = sdf.parse(text)
		this.setValue(date)
	}

}
