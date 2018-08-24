package com.tyust.util.commons;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter {

	@Override
	public Object convert(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class type, Object value) {
		if(value==null) return null;
		if(!(value instanceof String))
			return value;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse((String) value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object convert(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
