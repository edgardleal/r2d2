package br.com.template;

import java.sql.SQLException;
import java.util.List;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

/**
 * 
 * 
 * @author Edgard Leal
 * @since 05/07/2013
 */
public abstract class DefaultController {
	public static final String DATA_BASE_ERROR_MESSAGE = "Ocorreu um erro com o banco de dados";
	public static final String DELETED_MESSAGE = "O registro foi excluso com sucesso";
	public static final String SAVED_MESSAGE = "O registro foi salvo com sucesso";
	public static final int HTTP_SERVER_ERROR_RESPONSE_CODE = 500;
	public static final int HTTP_OK_RESPONSE_CODE = 200;
	public static final int HTTP_CLIENT_ERRO_RESPONSE_CODE = 400;
	public static final int HTTP_SERVER_RESPONSE_PAGE_NOT_FOUND = 404;
	protected Result result;
	protected Validator validator;

	public DefaultController(Result result, Validator validator) {
		this.result = result;
		this.validator = validator;
	}

	public abstract void view(String codigo);

	public abstract List<?> list();

	public abstract void ajaxList();

	public abstract void ajaxGet(String codigo);

	public abstract void delete(String codigo)
 throws SQLException;

	protected void ajaxResponse(String message) {
		ajaxResponse(message, 0);
	}

	/**
	 * Retorna uma mensagem ajax para o cdigo javascript
	 * 
	 * @param message
	 * @param codigo
	 */
	protected void ajaxResponse(String message, int codigo) {
		result.use(Results.http()).setStatusCode(codigo);
		result.use(Results.json()).from(message).serialize();
	}

	protected void ajaxValidationException(String message) {
		ajaxResponse(message, HTTP_CLIENT_ERRO_RESPONSE_CODE);
	}

	protected void ajaxDeletedResponse() {
		ajaxResponse(DELETED_MESSAGE, HTTP_OK_RESPONSE_CODE);
	}

	protected void ajaxDataBaseErrorResponse() {
		ajaxResponse(DATA_BASE_ERROR_MESSAGE, HTTP_SERVER_ERROR_RESPONSE_CODE);
	}

	protected void ajaxSavedResponse() {
		ajaxResponse(SAVED_MESSAGE, HTTP_OK_RESPONSE_CODE);
	}

	protected void includeSavedResponse() {
		result.include("message", SAVED_MESSAGE);
	}

	protected void includeDeletedMessage() {
		result.include("message", DELETED_MESSAGE);
	}

	protected void includeDataBaseErrorMessage() {
		result.include("message", DATA_BASE_ERROR_MESSAGE);
	}

	/**
	 * 
	 */
	protected void validationMessage(String message) {
		validator.add(new ValidationMessage("error", message));
	}

	public abstract void ajaxCombo();
}
