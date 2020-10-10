package raj.custom.validation.framework;

public interface Validator<T> {
	
	ValidationResult validate(T t);
}
