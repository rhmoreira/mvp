package br.com.mvp.binding;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import br.com.mvp.instrument.reflection.ClassMemberFilter;
import br.com.mvp.instrument.reflection.MemberHandler;

public class BindMemberHandler extends MemberHandler {

	private Class<?> bindClass;
	
	public BindMemberHandler(ClassMemberFilter filter) {
		super(filter);
	}
	
	@Override
	protected Field[] mapFields(Class<?> clazz) throws Exception {
		this.bindClass = clazz;
		return new Field[]{};
	}
	
	@Override
	protected void mapGettersAndSetters(Field[] acceptedFields) throws Exception {
		Map<String, Method> mappedMethods = getMappedMethods();
		for (Method method: bindClass.getDeclaredMethods())
			if (filter.accept(method))
				mappedMethods.put(method.getName(), method);
	}

}
