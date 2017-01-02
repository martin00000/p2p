package com.eloan.mgrtool.web;

import com.eloan.base.domain.SystemDictionary;
import com.eloan.base.domain.SystemDictionaryItem;
import com.eloan.base.query.PageResult;
import com.eloan.base.query.SystemDictionaryQueryObject;
import com.eloan.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 数据字典分分类
 *
 * @author Dingying
 * @create 2017-01-02 16:15
 **/
@Controller
public class SystemDictionaryController extends BaseController {

    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequestMapping("systemDictionary_list")
    public String list(SystemDictionaryQueryObject qo, Model model) {
        PageResult pageResult = this.systemDictionaryService.queryDic(qo);
        model.addAttribute("pageResult", pageResult);
        return "systemdic/systemDictionary_list";
    }

    @RequestMapping("systemDictionary_update")
    public String update(SystemDictionary dictionary) {
        this.systemDictionaryService.saveOrUpdateDic(dictionary);
        return "redirect:systemDictionary_list.do";
    }

    @RequestMapping("systemDictionaryItem_list")
    public String listItem(SystemDictionaryQueryObject queryObject, Model model) {
        PageResult pageResult = this.systemDictionaryService
                .queryDicItem(queryObject);
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("systemDictionaryGroups",
                this.systemDictionaryService.listAllDics());
        model.addAttribute("qo", queryObject);
        return "systemDic/systemDictionaryItem_list";
    }

    @RequestMapping("systemDictionaryItem_update")
    public String updateItem(SystemDictionaryItem item, Model model) {
        this.systemDictionaryService.saveOrUpdateItem(item);
        return "redirect:systemDictionaryItem_list.do?parentId="+item.getParentId();
    }
}
