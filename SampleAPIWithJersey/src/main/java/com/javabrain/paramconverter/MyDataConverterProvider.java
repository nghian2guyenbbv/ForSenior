package com.javabrain.paramconverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.javabrain.component.MyDate;
@Provider
public class MyDataConverterProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		// TODO Auto-generated method stub
		if(rawType.getName().equals(MyDate.class.getName())) {
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					Calendar requestDate = Calendar.getInstance();
					// TODO Auto-generated method stub
					if("tomorrow".equals(value)) {
					requestDate.add(Calendar.DATE, 1);;
					}else if("yesterday".equals(value)) {
						requestDate.add(Calendar.DATE,-1);
					}
					MyDate mydate = new MyDate();
					mydate.setDate(requestDate.get(Calendar.DATE));
					mydate.setMonth(requestDate.get(Calendar.MONTH));
					mydate.setYear(requestDate.get(Calendar.YEAR));
					return rawType.cast(mydate);
					
				}

				@Override
				public String toString(T value) {
					// TODO Auto-generated method stub
					if(value == null) {
						return null;
					}
					return value.toString();
				}
				

};
			}
		return null;
			}
	}

