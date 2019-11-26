package com.filip.examples.springbootspringdocopenapi3.constraints;

import com.filip.examples.springbootspringdocopenapi3.models.Movie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, List<Movie>> {
    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param values   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(List<Movie> values, ConstraintValidatorContext context) {
        boolean isValid = true;
        if (values.size() > 4) {
            isValid = false;
        }
        return isValid;
    }


}
