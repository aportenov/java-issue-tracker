package softuni.validator;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class Validator {

   public <T> List<String> validate(T object) {
       ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
       javax.validation.Validator validator = factory.getValidator();
       Set<ConstraintViolation<T>> violations = validator.validate(object);
       List<String> violationMsgs = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

       return violationMsgs;
    }
}
