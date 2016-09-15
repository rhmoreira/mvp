package br.com.mvp.view.dnd.list;

import br.com.mvp.view.dnd.configuration.DnDConfiguration;
import br.com.mvp.view.dnd.configuration.ElementFilter;

public class JListConfiguration<T> extends DnDConfiguration<T> {

	@Override
	public JListConfiguration<T> sourceFilter(ElementFilter<T> filter) {
		super.sourceFilter(filter);
		return this;
	}

	@Override
	public JListConfiguration<T> destFilter(ElementFilter<T> filter) {
		super.destFilter(filter);
		return this;
	}
}
