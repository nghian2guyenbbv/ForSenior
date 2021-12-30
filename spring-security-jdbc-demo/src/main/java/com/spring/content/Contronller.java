package com.spring.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Contronller {
 @RequestMapping("/")
 public String getDemo() {
	return "home"; 
 }
 @RequestMapping("/showMyLoginForm")
 public String getShowMyLoginForm() {
	 return "MyLoginForm";
 }
 @RequestMapping("/logout")
 public String getShowLogoutForm() {
	 return "fancy-logout";
 }
 @RequestMapping("/adminMetting")
 public String getAdminMetting() {
	 return "adminMetting";
 }
 @RequestMapping("/myWife")
 public String letMywife() {
	 return "myWife";
 }
 @RequestMapping("/systemMetting")
 public String getSystemeeting() {
	 return "systemMetting";
 }
}
