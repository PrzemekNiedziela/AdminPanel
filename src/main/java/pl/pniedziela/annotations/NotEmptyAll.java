package pl.pniedziela.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * Validation annotation to validate that 2 fields have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 *
 * Example, compare 1 pair of fields:
 * 
 * @NotEmptyAll(first = "password", second = "confirmPassword", message =
 *                    "The password fields must match")
 * 
 *                    Example, compare more than 1 pair of
 *                    fields: @NotEmptyAll.List({
 * @NotEmptyAll(first = "password", second = "confirmPassword", message =
 *                    "The password fields must match"),
 * @NotEmptyAll(first = "email", second = "confirmEmail", message =
 *                    "The email fields must match")})
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = NotEmptyAllValidator.class)
@Documented
public @interface NotEmptyAll {
	String message() default "{constraints.NotEmptyAll}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return The first field
	 */
	String first();

	/**
	 * @return The second field
	 */
	String second();

	/**
	 * Defines several <code>@NotEmptyAll</code> annotations on the same element
	 *
	 * @see NotEmptyAll
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		NotEmptyAll[] value();
	}
}