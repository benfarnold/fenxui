package org.fenxui.application.view.factory.handler;

import com.jfoenix.validation.base.ValidatorBase;
import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;
import org.fenxui.annotation.Validator;
import org.fenxui.application.el.EvaluableCondition;
import org.fenxui.application.el.ExpressionInitUtil;
import org.fenxui.application.el.VariableExtractor;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.validator.ConditionalValidator;
import org.fenxui.application.view.factory.handler.page.PageContext;

public class ValidatorAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		Validator validator = (Validator) annotation;

		FieldOption fieldOption = fieldContext.getActiveFieldOption();
		PageContext pageContext = fieldContext.getPageContext();

		ValidatorBase validatorImpl = validator.type().create(validator.message());

		if (!StringUtils.isEmpty(validator.evalExpression()) && validatorImpl instanceof ConditionalValidator) {
			VariableExtractor extractor = new VariableExtractor(validator.evalExpression());
			EvaluableCondition condition = new EvaluableCondition(extractor.getEvalExpression());
			((ConditionalValidator) validatorImpl).conditionalProperty().bind(condition.stateProperty());

			ExpressionInitUtil.INSTANCE.init(extractor, condition, pageContext, fieldOption);
		}
		fieldOption.addValidator(validatorImpl);
	}

}
