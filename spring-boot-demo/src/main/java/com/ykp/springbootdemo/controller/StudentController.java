package com.ykp.springbootdemo.controller;

import com.ykp.service.user.model.Student;
import com.ykp.springbootdemo.manager.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping( "/student/" )
public class StudentController extends BaseController {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	private static final String prefix = "student/";
	
	@RequestMapping( "toPage/{pageUrl}" )
	public String toPage( @PathVariable( "pageUrl" ) String pageUrl ) {
		return prefix + pageUrl;
	}
	
	@RequestMapping( value = "save" )
	public String save( Student student ) throws Exception {
		ServiceManager.studentService.insert( student );
		return "redirect:/student/queryStudentList";
	}
	
	@RequestMapping( value = "queryStudentList" )
	public String queryStudentList( Model model ) {
		List<Student> studentList = ServiceManager.studentService.queryStudentList();
		model.addAttribute( "studentList", studentList );
		return prefix + "studentList";
	}
	
	@RequestMapping( value = "queryStudentById" )
	public String queryUserById( Model model, Integer id ) {
		Student student = ServiceManager.studentService.selectByPrimaryKey( id );
		logger.info( "user:{}", student );
		model.addAttribute( "vo", student );
		return prefix + "studentInfo";
	}
}
