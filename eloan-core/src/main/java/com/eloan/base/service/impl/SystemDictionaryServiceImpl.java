package com.eloan.base.service.impl;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.mapper.SystemdictionaryMapper;
import com.eloan.base.mapper.SystemdictionaryitemMapper;
import com.eloan.base.query.PageResult;
import com.eloan.base.query.SystemDictionaryQueryObject;
import com.eloan.base.service.ISystemDictionaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dingying
 * @create 2017-01-02 15:55
 **/
@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemdictionaryMapper systemdictionaryMapper;

    @Autowired
    private SystemdictionaryitemMapper systemdictionaryitemMapper;

    @Override
    public PageResult queryDic(SystemDictionaryQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<SystemDictionary> systemDictionaries = systemdictionaryMapper.selectBySnOrTitle(qo);
        PageInfo<SystemDictionary> info = new PageInfo<>(systemDictionaries);
        long total = info.getTotal();
        PageResult pageResult = new PageResult((int)total, qo.getPageSize(), qo.getCurrentPage(), systemDictionaries);
        return pageResult;
    }

    @Override
    public void saveOrUpdateDic(SystemDictionary dic) {
        if (dic.getId() != null) {
            systemdictionaryMapper.updateByPrimaryKey(dic);
        } else {
            systemdictionaryMapper.insert(dic);
        }
    }

    @Override
    public PageResult queryDicItem(SystemDictionaryQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<SystemDictionaryItem> systemDictionaryItems = systemdictionaryitemMapper.selectByTitleAndParentId(qo);
        PageInfo<SystemDictionaryItem> info = new PageInfo<>(systemDictionaryItems);
        long total = info.getTotal();
        PageResult pageResult = new PageResult((int)total, qo.getPageSize(), qo.getCurrentPage(), systemDictionaryItems);
        return pageResult;
    }

    @Override
    public List<SystemDictionary> listAllDics() {
        return systemdictionaryMapper.selectAll();
    }

    @Override
    public void saveOrUpdateItem(SystemDictionaryItem dicItem) {
        if (dicItem.getId() != null) {
            systemdictionaryitemMapper.updateByPrimaryKey(dicItem);
        } else {
            systemdictionaryitemMapper.insert(dicItem);
        }
    }

    @Override
    public List<SystemDictionaryItem> listOption(String sn) {
        return  systemdictionaryitemMapper.selectBySnOfSdic(sn);
    }
}
