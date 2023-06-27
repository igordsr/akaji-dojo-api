package br.com.akaji.dojo.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface CrudAkaji<T> {

    public DataTransferObject create(Authentication authentication, T obj) throws Exception;

    public DataTransferObject read(Authentication authentication, Long id);

    public DataTransferObject update(Authentication authentication, Long id, T vo);

    public Boolean delete(Authentication authentication, Long id);

    public List<DataTransferObject> listAll(Authentication authentication);

    public Page<DataTransferObject> listAll(Authentication authentication, Pageable pageable);

}
