package org.fade.demo.springframework.beans

import org.springframework.beans.PropertyEditorRegistrar
import org.springframework.beans.PropertyEditorRegistry
import org.springframework.beans.propertyeditors.CustomDateEditor

import java.text.SimpleDateFormat

class DatePropertyEditorRegistrar implements PropertyEditorRegistrar{

	@Override
	void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Date, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true))
	}

}
