package br.com.akaji.dojo.exception;

import java.util.Map;

public interface MessageException {
	public String getExceptionKey();
    public Map<String, Object> getMapDetails();
}
