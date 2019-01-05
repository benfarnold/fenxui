package org.fenxui.application.view.factory.handler;

import com.jfoenix.validation.base.ValidatorBase;
import java.lang.annotation.Annotation;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.fenxui.annotation.Validator;
import org.fenxui.application.el.EvaluableCondition;
import org.fenxui.application.el.VariableContext;
import org.fenxui.application.el.VariableExtractor;
import org.fenxui.application.exception.FenxuiInitializationException;
import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.components.validator.ConditionalValidator;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.application.view.factory.ootb.AppConstruction;
import org.fenxui.application.view.factory.ootb.FrameContext;

public class ValidatorAnnotationHandler implements AnnotationHandler {

	@Override
	public void handle(NodeContext fieldContext, Annotation annotation) throws FenxuiInitializationException {
		Validator validator = (Validator) annotation;

		FieldOption fieldOption = fieldContext.getActiveFieldOption();
		PageContext pageContext = fieldContext.getPageContext();
		FrameContext appConstruction = pageContext.getFrameContext();

		ValidatorBase validatorImpl = validator.type().create(validator.message());

		if (!StringUtils.isEmpty(validator.evalExpression()) && validatorImpl instanceof ConditionalValidator) {
			VariableExtractor extractor = new VariableExtractor(validator.evalExpression());
			EvaluableCondition condition = new EvaluableCondition(extractor.getEvalExpression());
			VariableContext variableContext = new VariableContext(condition);
			((ConditionalValidator) validatorImpl).conditionalProperty().bind(condition.stateProperty());

			Set<String> variableNames = extractor.getVariables();
			variableContext.sizeProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue.intValue() == variableNames.size()) {
					variableContext.eval();//evaluate when all variables are resolved
				}
			});

			for (String variableName : variableNames) {
				if (variableName.contains(".")) {
					String[] parts = variableName.split("\\.");
					String className = parts[0];
					String fieldName = parts[1];
					appConstruction.addPageScopedFieldPostProcessor(className, new VariableContextFieldPostProcessor(fieldName, variableName, variableContext));
				} else {
					pageContext.addPostProcessor(new VariableContextFieldPostProcessor(variableName, variableName, variableContext));
				}
			}
		}
		fieldOption.addValidator(validatorImpl);
	}

}
