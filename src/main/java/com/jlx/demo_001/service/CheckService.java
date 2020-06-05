package com.jlx.demo_001.service;

import com.jlx.demo_001.DAO.HomeworkRepository;
import com.jlx.demo_001.DAO.PaperMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CheckService {
    @Autowired
    HomeworkRepository homeworkRepository;
    @Autowired
    PaperMarketRepository paperMarketRepository;

    public void checkOne(int classId,String openId, int paperId,String answer){
        Map<String, String> m1 = new HashMap<String, String>();
        Map<String, String> m2 = new HashMap<String, String>();

        for(Map.Entry<String, String> entry1:m1.entrySet()){
            String m1value = entry1.getValue() == null?"":entry1.getValue();
            String m2value = m2.get(entry1.getKey())==null?"":m2.get(entry1.getKey());
            if (!m1value.equals(m2value)) {//若两个map中相同key对应的value不相等
                //其他操作...
            }
        }
    }
}
