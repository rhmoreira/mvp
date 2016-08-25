package br.com.mvp.model;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;
import br.com.mvp.instrument.reflection.ClassHandler;
import br.com.mvp.instrument.reflection.ClassMemberFilter;

class ModelDependencyMapper {

	private ClassHandler classHandler;

	public ModelDependencyMapper(ClassHandler classHandler) {
		super();
		this.classHandler = classHandler;
	}
	
	public Map<Field, Class<?>> mapDependencies() {
		Map<Field, Class<?>> dependencyMap = filterModelClasses();
		
		dependencyMap.forEach( (f, c) -> {
			try {
				Instrumentator<?> instrumentator = InstrumentatorFactory.createModel(c);
				instrumentator.setupProxy();
			}
			catch (Exception e) {}
		});
		
		return dependencyMap;		
	}
	
	private Map<Field, Class<?>> filterModelClasses(){
		ClassMemberFilter filter = classHandler.getFilter();
		Collection<Field> scannedFields = classHandler.getScannedFields();
		
		Map<Field, Class<?>> dependentClasses = 
				scannedFields.stream()
							 .filter(f -> filter.accept(f.getType()))
							 .collect(
								Collectors.toMap(
									(Function<Field,Field>) f -> f,
									(Function<Field,Class<?>>) f -> f.getType()
								)
							 );
		return dependentClasses;
	}
}
