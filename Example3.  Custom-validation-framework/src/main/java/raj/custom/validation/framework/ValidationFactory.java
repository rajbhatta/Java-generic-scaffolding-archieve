package raj.custom.validation.framework;

public interface ValidationFactory<T,R> {
	
	Validator<T> getValidator();
	Resolver<T,R> getResolver(ValidationResult validationResult);
}
