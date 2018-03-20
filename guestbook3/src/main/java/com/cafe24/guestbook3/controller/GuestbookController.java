package com.cafe24.guestbook3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.guestbook.dao.GuestbookDao;
import com.cafe24.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	public GuestbookDao guestbookDao;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<GuestbookVo>list = guestbookDao.getList();
		
		model.addAttribute("list",list);
		
		return "/WEB-INF/views/list.jsp";
	}
	
	

	@RequestMapping(value = "/form")
	public String form(Long no, Model model) {
		
//		Long no1 = Long.parseLong(no);
		
		System.out.println("NO : " + no);
	
		 model.addAttribute("no",no);
		
		return "/WEB-INF/views/deleteform.jsp";
	}
	

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		guestbookDao.insert(vo);
		return "redirect:/list";
	}
	

	@RequestMapping(value="/delete")
	public String delete(@ModelAttribute GuestbookVo vo,@RequestParam Long no,String password) {
		
		System.out.println(" delete no : " + no);
		
		System.out.println(" delete password : " + password);
		
//		vo.setNo(no1);
//		vo.setPassword(password);
		
		guestbookDao.delete(vo);
		return "redirect:/list";
	}
	
	
}
