/**
 * 
 */
package com.github.thg.bankapi.services;

import java.util.List;

/**
 * @author <a href="malito:gleisondeandradeesilva@gmail.com">Gleison Andrade</a>
 *
 */
public interface GenericService<E,K> {
    public E save(E entity) ;
    public E update(E entity) ;
    public void remove(K key);
    public E search(K key);
    public List<E> listAll();
}
