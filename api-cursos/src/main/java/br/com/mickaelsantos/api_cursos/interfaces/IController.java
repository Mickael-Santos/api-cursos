
package br.com.mickaelsantos.api_cursos.interfaces;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

public interface IController<T,D,H> 
{
    public ResponseEntity<Object> create(T objeto, H request);

    public ResponseEntity<Object> update(UUID  uuid, D requestDto);

    public ResponseEntity<Object> delete(UUID uuid);
}
