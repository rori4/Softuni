package com.marketingsuite.annotations;

import com.marketingsuite.entities.User;
import com.marketingsuite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

   @Autowired
   private static UserService userService;

   public void initialize(UniqueEmail constraint) {
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      User user = userService.findByEmail(email);
      return user == null;
   }
}
