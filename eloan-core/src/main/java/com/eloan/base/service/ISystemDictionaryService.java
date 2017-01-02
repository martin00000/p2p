package com.eloan.base.service;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.query.PageResult;
import com.eloan.base.query.SystemDictionaryQueryObject;

import java.util.List;

/**
 * 数据字典
 *
 * @author Dingying
 * @create 2017-01-02 15:50
 **/
public interface ISystemDictionaryService {
    /**
     * 分页查询数据字典的分类
     * @param qo
     * @return
     */
    PageResult queryDic(SystemDictionaryQueryObject qo);

    /**
     * 新增和修改数据字典分类
     * @param dic
     */
    void saveOrUpdateDic(SystemDictionary dic);

    /**
     * 分页查询数据字典
     * @param qo
     * @return
     */
    PageResult queryDicItem(SystemDictionaryQueryObject qo);

    /**
     * 列出所有的SystemDictionaryItem
     * @return
     */
    List<SystemDictionary> listAllDics();

    /**
     * 新增和修改数据字典
     * @param item
     */
    void saveOrUpdateItem(SystemDictionaryItem item);
}
