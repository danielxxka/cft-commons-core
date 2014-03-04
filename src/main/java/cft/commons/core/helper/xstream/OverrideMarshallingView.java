package cft.commons.core.helper.xstream;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.oxm.Marshaller;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.xml.MarshallingView;

/**
 * @author daniel
 *
 */
public class OverrideMarshallingView extends MarshallingView {

	private Marshaller marshaller;

	private String modelKey;

	public OverrideMarshallingView() {
		super();
	}

	public OverrideMarshallingView(Marshaller marshaller) {

		super(marshaller);

		this.marshaller = marshaller;

	}

	public void setMarshaller(Marshaller marshaller) {

		super.setMarshaller(marshaller);
		this.marshaller = marshaller;
	}

	public void setModelKey(String modelKey) {
		super.setModelKey(modelKey);
		this.modelKey = modelKey;
	}

	@Override
	protected void initApplicationContext() throws BeansException {

		super.initApplicationContext();

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.renderMergedOutputModel(model, request, response);
	}

	@Override
	protected Object locateToBeMarshalled(Map model) throws IllegalStateException {

		if (modelKey != null) {

			Object o = model.get(modelKey);

			if (!this.marshaller.supports(o.getClass())) {
				throw new IllegalStateException("Model object [" + o + "] retrieved via key [" + modelKey
						+ "] is not supported by the Marshaller");
			}

			return o;
		}

		for (Object o : model.values()) {

			//解决对象添加到ModelAndView中，转换后的xml是BindingResult信息的bug
			if (o instanceof BindingResult) {
				continue;
			}

			if (this.marshaller.supports(o.getClass())) {
				return o;
			}

		}

		return "NULL";
	}
}