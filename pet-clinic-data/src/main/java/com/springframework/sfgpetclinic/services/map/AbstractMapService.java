package com.springframework.sfgpetclinic.services.map;

import com.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService <T extends BaseEntity,ID extends Long>{
    protected Map<Long,T> map=new HashMap<>();
    Set<T> findAll(){
        return new HashSet<>(map.values());
    }
    T findById(ID id){
        return map.get(id);
    }
    T save(T obj){
        if(obj!=null){
            if(obj.getId()==null)
                obj.setId(getNextId());
            map.put(obj.getId(),obj);
        }else{
            throw new RuntimeException("Object cannot be null!");
        }
        return obj;
    }
    void delete(T obj){
        map.entrySet().removeIf(entry->entry.getValue().equals(obj));
    }
    void deleteById(ID id){
        map.remove(id);
    }

    private Long getNextId(){
        Long nextId=null;
        try{nextId=Collections.max(map.keySet())+1;}
        catch (NoSuchElementException noSuchElementException){
            nextId=1L;
        }
        return nextId;
    }
}
