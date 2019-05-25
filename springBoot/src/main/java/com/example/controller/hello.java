package com.example.controller;

import com.example.pojo.Code;
import com.example.pojo.Config;
import com.example.pojo.User;
import com.example.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/24.
 */
@RestController
@Slf4j
public class hello {
    @Value("${blog.name}")
    private  String name;

    @Value("${blog.password}")
    private  String password;

    private  final  Config config;

    @Autowired
    private JavaMailSender mailSender;

    //自动注入
    @Autowired
    freemarker.template.Configuration freemarkerConfig;

    @Autowired
    TaskScheduler taskScheduler;

    @Constant(message = "verson只能为1.0",value="1.0")
    String version;
    @Autowired
    public hello(Config config) {
        this.config = config;
    }

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public  String say(){
        return "Hello World";
    }


    @RequestMapping(value = "/getcode", method = RequestMethod.GET)
    public Code getCode(){
        Code co = new Code();
        co.setCode("111");
        co.setName("往后");
        log.info("getcode"+co.getCode());
        int a=5/0;
        return  co;
    }

    @RequestMapping(value = "/getName", method = RequestMethod.GET)
    public  String getName(){
        return name;
    }

    @RequestMapping(value = "/getConfig", method = RequestMethod.GET)
    public Config getConfig(){
        System.out.print(config.toString());
        return  config;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public String getUser(@Valid User user){
        return  user.getUserName()+","+user.getPassWord();
    }

    @GetMapping("/poolTask")
    public String threadPoolTaskScheduler() {
        taskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("ThreadPoolTaskScheduler定时任务：" + new Date());
            }
        },new CronTrigger("0/3 * * * * ?"));
        return  "ThreadPoolTaskScheduler!";
    }

    @GetMapping("/simple")
    public String simpleSend() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1073651057@qq.com");
        message.setTo("1073651057@qq.com");
        message.setSubject("主题：来自oKong邮件");
        message.setText("公众号：一枚趔趄的猿(lqdevOps)，作者：oKong");
        mailSender.send(message);
        return "发送成功!";
    }

    @GetMapping("/attachSend")
    public String attachSend() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("1073651057@qq.com");
        helper.setTo("1073651057@qq.com");
        helper.setSubject("主题：来自oKong邮件(带附件)");
        helper.setText("(含附件)公众号：一枚趔趄的猿(lqdevOps)，作者：oKong");
        File qrCode = new File("wxgzh8cm.jpg");
        //建议文件带上后缀，可支持在线预览
        helper.addAttachment("公众号二维码.jpg", qrCode);
        mailSender.send(mimeMessage);
        return "附件邮件发送成功!";
    }



    @GetMapping("/template/{userName}")
    public String template(@PathVariable(value = "userName") String userName) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("1073651057@qq.com");
        helper.setTo("1073651057@qq.com");
        helper.setSubject("主题：" + userName + ",你有一封来自oKong邮件(From模版)");
        //设置替换的参数对象
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userName", StringUtils.isEmpty(userName) ? "oKong" : userName);
        String templateString = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerConfig.getTemplate("mail.html"), model);
        helper.setText(templateString,true);
        //抄送人
//		helper.setCc("");
        //密送人
//		helper.setBcc("");
        //添加附件

        mailSender.send(mimeMessage);

        return "模版文件发送成功!";
    }
}
