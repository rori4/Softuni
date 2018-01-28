package com.securitydemo.annotations;

import com.securitydemo.model.RegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
   public void initialize(IsPasswordsMatching constraint) {
   }

   public boolean isValid(Object userClass, ConstraintValidatorContext context) {
      if (userClass instanceof RegistrationModel){
         return ((RegistrationModel) userClass).getPassword().equals(((RegistrationModel) userClass).getConfirmPassword());
      }
      return false;
   }
}
