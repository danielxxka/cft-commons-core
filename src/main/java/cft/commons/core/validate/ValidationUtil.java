package cft.commons.core.validate;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Path.Node;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import cft.commons.core.constant.Constants;
import cft.commons.core.model.display.ResultJSON;
import cft.commons.core.util.PropertiesUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import com.google.common.collect.Iterables;

public class ValidationUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

	public ResultJSON callbackFailJson(MessageSource messageSource, Locale locale, String resultCode) {
		ResultJSON json = new ResultJSON();
		json.setSuccess(false);
		json.setMessage(messageSource.getMessage(resultCode, null, locale));
		json.setResultCode(resultCode);
		return json;
	}

	public String getInValidMsg(Errors result, Object o, Class<?>... classes) {
		if (classes == null || classes.length == 0 || classes[0] == null) {
			classes = new Class<?>[] { Default.class };
		}
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(o, classes);
		for (ConstraintViolation<Object> v : violations) {
			Path path = v.getPropertyPath();
			String propertyName = "";
			if (path != null) {
				for (Node n : path) {
					propertyName += n.getName() + ".";
				}
				propertyName = propertyName.substring(0, propertyName.length() - 1);
			}
			String constraintName = v.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
			if (propertyName == null || "".equals(propertyName)) {
				result.reject(constraintName, v.getMessage());
			} else {
				result.rejectValue(propertyName, constraintName, v.getMessage());
			}

		}
		if (violations.size() == 0) {
			return null;
		} else {
			logger.error(Constants.UTL_LOG + "getInValidMsg Object:" + o);
			logger.error(Constants.UTL_LOG + "invalid data code :"
					+ Iterables.get(violations, 0).getMessage().toString());

			return Iterables.get(violations, 0).getMessage().toString();
		}
	}

}
