package com.miquido.Validator;

import com.miquido.Config.ConfigProperties;
import com.miquido.Service.SpringApplicationContextService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CharacterValidator implements ConstraintValidator<ValidCharacter, Long> {

    @Autowired
    private ConfigProperties configProperties;

    @Override
    public void initialize(ValidCharacter constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        configProperties = SpringApplicationContextService.getApplicationContext().getBean(ConfigProperties.class);
        if (value == null) {
            return true;
        }
        formatMessage(context);
        return value >= configProperties.getMinimum();
    }

    private void formatMessage(ConstraintValidatorContext context) {
        String msg = context.getDefaultConstraintMessageTemplate();
        String formattedMsg = String.format(msg, configProperties.getMinimum());
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(formattedMsg)
                .addConstraintViolation();
    }
}