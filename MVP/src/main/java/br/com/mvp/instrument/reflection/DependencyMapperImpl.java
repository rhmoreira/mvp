package br.com.mvp.instrument.reflection;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import br.com.mvp.Util;
import br.com.mvp.instrument.Instrumentator;
import br.com.mvp.instrument.InstrumentatorFactory;

class DependencyMapperImpl implements DependencyMapper{

	private ClassHandler classHandler;
	private Map<Field, Class<?>> dependencyMap = new HashMap<>();
	private Collection<Class<?>> dependencyClasses;

	public DependencyMapperImpl(ClassHandler classHandler) {
		super();
		this.classHandler = classHandler;
	}
	
	public void mapModelDependencies() {
		Map<Field, Class<?>> dependencyMap = filterDependencyClasses();
		
		dependencyMap.forEach( (f, c) -> {
			try {
				Instrumentator<?> instrumentator = InstrumentatorFactory.create(c);
				instrumentator.setupProxy();
			}
			catch (Exception e) {}
		});
		
		this.dependencyMap = dependencyMap;
		dependencyClasses = dependencyMap.values();
	}
	
	@Override
	public boolean isDependency(Class<?> modelClass) {
		if (modelClass == null)
			return false;
		Class<?> proxiedClass = Util.getProxiedClass(modelClass);
		return dependencyClasses.contains(proxiedClass);
	}
	
	@Override
	public Map<Field, Class<?>> getDependencies() {
		return dependencyMap;
	}
	
	private Map<Field, Class<?>> filterDependencyClasses(){
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
