package raj.custom.validation.framework;

public interface Resolver<T,R> {
	R resolve(T invalidInput);
}
