package com.eloan.uiweb.web;

import com.eloan.base.util.UserContext;
import com.eloan.business.domain.Userfile;
import com.eloan.business.service.IUserFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Writer;
import java.util.List;

/**
 * <p>
 * 风控资料
 * </p>
 *
 * @author Dingying
 * @version 1.0
 * @create 2017-01-05 上午 10:15
 **/
@Controller
public class UserFileController extends BaseController {

    @Autowired
    private IUserFileService userFileService;

    @RequestMapping("userFile")
    public String userFileList(Model model, HttpServletRequest request) {
        List<Userfile> noTypeUserFiles =
                this.userFileService.getUserFileOfCurrentUser(UserContext.getLogininfo().getId(), true);
        if (noTypeUserFiles.size() <= 0) {
            noTypeUserFiles =
                    this.userFileService.getUserFileOfCurrentUser(UserContext.getLogininfo().getId(), false);
            model.addAttribute("sessionid", request.getSession().getId());
            model.addAttribute("userFiles", noTypeUserFiles);
            return "userFiles";
        } else {
            model.addAttribute("userFiles", noTypeUserFiles);
            return "userFiles_commit";
        }
    }

    @RequestMapping("userFileUpload")
    public void userFileUpload(MultipartFile file, Writer out){
        try {
            userFileService.uploadFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("userFile_selectType")
    public String userFileTypeSelect(Long[] id,Long[] fileType){
        if(id!=null && fileType!=null && id.length==fileType.length){
            this.userFileService.updateFileTypes(id,fileType);
        }
        return "redirect:userFile.do";
    }

}
