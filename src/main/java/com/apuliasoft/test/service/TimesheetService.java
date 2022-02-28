package com.apuliasoft.test.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.apuliasoft.test.model.Slot;
import org.springframework.stereotype.Service;

@Service
public class TimesheetService {

    public List<Object[]> aggregate(List<Slot> slots, List<String> by) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        HashMap<String,Object[]>  result = new HashMap<>();
        Object[] entry;
        String key;
        Iterator<Slot> valueIterator = slots.iterator();
        while(valueIterator.hasNext()) {
            Slot slot = valueIterator.next();
            key = slot.getHashBy(by);
            entry = new Object[by.size()+1];
            if(result.containsKey(key)) {
                result.get(key)[by.size()] =  (Double)result.get(key)[by.size()] + slot.getHours();
            } else {
                for(int i=0; i<by.size(); i++ ) {
                    String byElem = by.get(i);
                    switch(byElem) {
                        case "date":
                            entry[i] = format.format(slot.getDate());
                            break;
                        case "project":
                            entry[i] = slot.getProject().getName();
                            break;
                        default:
                            entry[i] = slot.getEmployee().getName();
                            break;
                    }
                }
                entry[by.size()] = slot.getHours();
                result.put(key, entry);
            }
        }
        Collection<Object[]> resList = result.values();


    return new ArrayList<>(resList);
    }
}
