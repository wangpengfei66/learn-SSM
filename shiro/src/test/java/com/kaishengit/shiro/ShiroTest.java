package com.kaishengit.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

public class ShiroTest {

    @Test
    public void hello() {
        //1. 读取classpath中的shiro.ini配置文件，并创建securityManagerFactory对象
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2. 获取SecurityManager
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //3.设置SecurityManager(仅设置一次)
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //5.根据账号和密码进行登录
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tom","123");
        //6.登录
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException ex) {
            ex.printStackTrace();
            System.out.println("无此账号");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            System.out.println("账号被锁定");
        } catch (IncorrectCredentialsException ex) {
            ex.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            System.out.println("账号或密码错误");
        }
        //7.安全退出
        subject.logout();
    }
    @Test
    public void useMyRealm() {
        //1. 读取classpath中的shiro.ini配置文件，并创建securityManagerFactory对象
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2. 获取SecurityManager
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //3.设置SecurityManager(仅设置一次)
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //5.根据账号和密码进行登录
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tom","123123");
        //6.登录
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException ex) {
            ex.printStackTrace();
            System.out.println("无此账号");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            System.out.println("账号被锁定");
        } catch (IncorrectCredentialsException ex) {
            ex.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            System.out.println("账号或密码错误");
        }
        //7.安全退出
        subject.logout();
    }
    @Test
    public void checkRoles() {
        //1. 读取classpath中的shiro.ini配置文件，并创建securityManagerFactory对象
        Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-roles.ini");
        //2. 获取SecurityManager
        SecurityManager securityManager = securityManagerFactory.getInstance();
        //3.设置SecurityManager(仅设置一次)
        SecurityUtils.setSecurityManager(securityManager);
        //4.获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //5.根据账号和密码进行登录
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("tom","123123");
        //6.登录
        try {
            subject.login(usernamePasswordToken);
            //判断是否是CTO hasRoles 判断是否有角色
            System.out.println("CTO?" + subject.hasRole("cto"));
            System.out.println(subject.hasAllRoles(Arrays.asList("cto","admin")));
            boolean [] res = subject.hasRoles(Arrays.asList("sales","admin"));
            for(boolean re : res) {
                System.out.println(re);
            }
            //校验subject是否有角色hr，如果没有，则抛出异常
            //subject.checkRole("hr");
            //8.判断用户是否有某个权限
            subject.isPermitted("user:update");
            //判断用户有没有该权限，没有则抛出异常
            subject.checkPermission("user:query");
        } catch (UnknownAccountException ex) {
            ex.printStackTrace();
            System.out.println("无此账号");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            System.out.println("账号被锁定");
        } catch (IncorrectCredentialsException ex) {
            ex.printStackTrace();
            System.out.println("密码错误");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            System.out.println("账号或密码错误");
        }
        //7.安全退出
        subject.logout();
    }


}
