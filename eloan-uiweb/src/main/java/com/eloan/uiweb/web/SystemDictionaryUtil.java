package com.eloan.uiweb.web;

import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-03 下午 2:14
 **/
@Component
public class SystemDictionaryUtil {
    @Autowired
    private ISystemDictionaryService systemDictionaryService;
    /**
     * 加载某个sn 字典下的明细
     * @param sn
     * @return
     */
    public List<SystemDictionaryItem> loadItems(String sn) {
        return systemDictionaryService.listOption(sn);
    }
}
