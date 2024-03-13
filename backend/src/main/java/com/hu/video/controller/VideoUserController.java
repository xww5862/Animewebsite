package com.hu.video.controller;

import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.ResultDTO;
import com.hu.video.service.IMessageService;
import com.hu.video.service.IUserService;
import com.hu.video.util.MsgResponse;
import com.hu.video.util.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("user")
@Controller
public class VideoUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    /**
     * 实现路由跳转
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("toPay")
    public String toPay() {
        return "pay";
    }

    @RequestMapping("toForget")
    public String toForget() {
        return "forget";
    }

    @RequestMapping("editPassword")
    public String editPassword(){return "ueditPassword";}

    @RequestMapping("ueditencryptedProblem")
    public String editencryptedProblem(){return "ueditencryptedProblem";}

    @RequestMapping("toIndexOther")
    public String toIndexOther() {
        return "indexother";
    }

    @RequestMapping("toMyIndex")
    public String toMyIndex() {
        return "indexmyself";
    }

    @RequestMapping("toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("toUEditorUser")
    public String toUEditorUser() {
        return "ueditUser";
    }


    @RequestMapping("toMessage")
    public String toMessage(){
        return "message";
    }


    @RequestMapping("tUserByuserId")
    @ResponseBody
    public TUser tUserByuserId(Long userId){
        return userService.getUserByUserId(userId);
    }

    /*---------检验手机号是否已存在--------*/
    @RequestMapping("checkTel")
    @ResponseBody
    public Map<Object, Boolean> checkTel(String userTel) {
        boolean flag = userService.queryByuserTel(userTel);
        Map<Object, Boolean> map = new HashMap<Object, Boolean>();
        System.out.println("flag" + flag);
        if (flag) {
            map.put("res", true);
        } else {
            map.put("res", false);
        }
        return map;
    }
    //---------修改用户信息--------
    @RequestMapping("UEditorUser")
    public String UEditorUser(HttpSession session, TUser tUser,Model model) {

        tUser.setIconUrl(userService.getUserByUserId(tUser.getUserId()).getIconUrl());
        userService.updateUserByUserId(tUser);
        TUser tUser1 = (TUser) session.getAttribute("user");
        //把之前的信息给过来
        tUser.setPassword(tUser1.getPassword());
        tUser.setEncryptedProblem(tUser1.getEncryptedProblem());
        tUser.setFanNum(tUser1.getFanNum());
        tUser.setUserTel(tUser1.getUserTel());
        tUser.setRegisterDate(tUser1.getRegisterDate());
        tUser.setStateId(tUser1.getStateId());
        //更新session操作
        session.removeAttribute("user");
        session.setAttribute("user", tUser);

        model.addAttribute("successMsg", "修改成功");
        return "redirect:/user/toIndex";
    }

    /**
     *修改密码
     * @param session
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param password  确认一遍新密码
     * @param model
     * @return
     */
    @RequestMapping("UEditorUserPass")
    public String UEditorUserPass(HttpSession session, String oldPassword,String newPassword,String password,Model model) {
        TUser tUser = (TUser) session.getAttribute("user");

        if (oldPassword==null){
            model.addAttribute("errorMsg", "旧密码不能为空");
            return "ueditPassword";
        }
        if (newPassword==null){
            model.addAttribute("errorMsg", "新密码不能为空");
            return "ueditPassword";
        }

        if(!tUser.getPassword().equals(oldPassword)) {
            model.addAttribute("errorMsg", "旧密码错误");
            return "ueditPassword";
        }
        if (!password.equals(newPassword)){
            model.addAttribute("errorMsg", "前后密码不一致");
            return "ueditPassword";
        }
        session.removeAttribute("user");
        tUser.setPassword(newPassword);
        session.setAttribute("user", tUser);

        userService.updateUserByUserId(tUser);
        return "redirect:/user/toIndex";
    }

    //----------通过密保来修改密码--------------------------
    @RequestMapping("UEditorUserPassByEP")
    public String UEditorUserPassByEP(String iphone,String EncryptedProblem,String newPassword,String password,Model model) {
        TUser tUser = userService.getUserByUserIphone(iphone);
        if (iphone==null){
            model.addAttribute("errorMsg", "手机号不能为空");
            return "forget";
        }
        if (EncryptedProblem==null){
            model.addAttribute("errorMsg", "密保不能为空");
            return "forget";
        }
        if (newPassword==null){
            model.addAttribute("errorMsg", "新密保不能为空");
            return "forget";
        }
        if(!tUser.getEncryptedProblem().equals(EncryptedProblem)) {
            model.addAttribute("errorMsg", "密保错误");
            return "forget";
        }
        if (!password.equals(newPassword)){
            model.addAttribute("errorMsg", "前后密码不一致");
            return "forget";
        }
        tUser.setPassword(newPassword);
        System.out.println("tuser" + tUser);
        userService.updateUserByUserId(tUser);
        return "login";
    }



    //修改密保
    @RequestMapping("UEditorUserEncryptedProblem")
    public String UEditorUserEncryptedProblem(HttpSession session, String oldEncryptedProblem,String newEncryptedProblem,String encryptedProblem,Model model) {
        TUser tUser = (TUser) session.getAttribute("user");
        if (oldEncryptedProblem==null){
            model.addAttribute("errorMsg", "旧密保不能为空");
            return "ueditencryptedProblem";
        }
        if (newEncryptedProblem==null){
            model.addAttribute("errorMsg", "新密保不能为空");
            return "ueditencryptedProblem";
        }
        if(!tUser.getEncryptedProblem().equals(oldEncryptedProblem)) {
            model.addAttribute("errorMsg", "旧密保错误");
            return "ueditencryptedProblem";
        }
        if (!encryptedProblem.equals(newEncryptedProblem)){
            model.addAttribute("errorMsg", "前后密保不一致");
            return "ueditencryptedProblem";
        }
        session.removeAttribute("user");
        tUser.setEncryptedProblem(newEncryptedProblem);
        session.setAttribute("user", tUser);
        System.out.println("tuser" + tUser);
        userService.updateUserByUserId(tUser);
        return "redirect:/user/toIndex";
    }

    /*---------上传头像--------*/
    @ResponseBody
    @RequestMapping("upload")
    public MsgResponse upload(@RequestParam MultipartFile file, HttpSession session) {
        String workplace = System.getProperty("user.dir");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        TUser user = (TUser) session.getAttribute("user");
        String fileName = "icon" + String.valueOf(user.getUserId()) + suffix;
        File newFile = new File(workplace + "/backend/src/main/resources/static/uimages/" + fileName);
        userService.updateUserIcon("/user/getIcon/" + fileName,user.getUserId());
        try {
            file.transferTo(newFile);
            System.out.println("success");
            return MsgResponse.success("上传成功",null);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }

    /*---------src getIcon--------*/
    @RequestMapping("/getIcon/{iconPath}")
    public void getIcon(HttpServletResponse response, @PathVariable String iconPath) throws IOException {
        String workpace = System.getProperty("user.dir");
        System.out.println(iconPath);

        File file = new File(workpace + "/backend/src/main/resources/static/uimages/" + iconPath);

        InputStream in = new FileInputStream(file);

        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    /*----------用户注册-----------*/
    @RequestMapping("userRegister")
    @ResponseBody
    public ResultDTO register(TUser tUser) {
        tUser.setStateId(1);
        tUser.setFanNum(0);
        tUser.setIconUrl("/user/getIcon/default.png");
        ResultDTO res = userService.register(tUser);
        return res;
    }

    /*----------用户登录-----------*/
    @RequestMapping("userLogin")
    @ResponseBody
    public ResultDTO login(String userTel, String password, String aCode, HttpSession session,Model model) {

        ResultDTO<TUser> res = userService.login(userTel, password);

        if (res.getResult()) {
            String randomCode = (String) session.getAttribute(ValidateCode.RANDOMCODEKEY);
            if (!aCode.equalsIgnoreCase(randomCode)) {
                //equalsIgnoreCase方法忽略大小写判断

                res.setMessage("验证码错误");
                res.setResult(false);
                return res;
            }
            if (res.getData().getStateId()==3){
                res.setMessage("您账号涉嫌违规操作，现已封禁");
                res.setResult(false);
                return res;
            }
            int messageCount = messageService.msgCount(res.getData().getUserId());
            System.out.println(messageCount);
            session.setAttribute("messageCount", "(" + messageCount + ")");
            session.setAttribute("user", res.getData());
//            TUser user  = session.getAttribute("user")
            res.setUserId(res.getData().getUserId());
            session.setAttribute("userId",res.getUserId());
            return res;
        }else {

            res.setMessage("账号或密码错误");
            return res;
        }

    }



    /*----------用户验证码验证-----------*/
    @RequestMapping("Img")
    public void Img(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("image/jpeg");
        //设置响应头，阻止浏览器缓存此响应的内容(节约内存)
        resp.setHeader("Pragma", "No-cache");
        //引用工具类中的方法，绘制验证码图片
        ValidateCode code = new ValidateCode();
        code.getValidateCode(req, resp);//调用绘制验证码的方法，绘制图片
    }



    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "index";
    }

}
