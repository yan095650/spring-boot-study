package com.ykp.springbootdemo.controller;

import com.ykp.service.user.model.User;
import com.ykp.springbootdemo.manager.ServiceManager;
import com.ykp.springbootdemo.utils.QrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping( "/user/" )
public class UserController extends BaseController {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@RequestMapping( "toPage/{pageUrl}" )
	public String toPage( @PathVariable( "pageUrl" ) String pageUrl ) throws IOException, InterruptedException {
		return "/" + pageUrl;
	}
	
	@RequestMapping( value = "loginByQRCode", method = RequestMethod.POST )
	public String login( User user, String token ) {
		logger.info( "登录用户信息为：{},token:{}", user, token );
		setUser( token, user );
		return "/index";
	}
	
	@RequestMapping( value = "save", method = RequestMethod.POST )
	public String save( User user ) {
		ServiceManager.userService.save( user );
		return "redirect:/user/queryUserList";
	}
	
	@RequestMapping( value = "queryUserList", method = RequestMethod.GET )
	public String queryUserList( Model model, User user ) {
		List<User> userList = ServiceManager.userService.queryUsers( user );
		model.addAttribute( "userList", userList );
		return "/userList";
	}
	
	/**
	 * 生成二维码
	 */
	@RequestMapping( value = "queryQRCode", method = RequestMethod.GET )
	public void queryUsersByMobile( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		response.setDateHeader( "Expires", 0 );
		response.setHeader( "Cache-Control", "no-store, no-cache, must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0, pre-check=0" );
		response.setHeader( "Pragma", "no-cache" );
		response.setContentType( "image/jpeg" );
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			String url = "http://10.10.56.83/user/toPage/qrLogin?token=" + getToken();
			logger.info( "url:{}", url );
			QrUtil.generateQrCode( url, out );
			out.flush();
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			if ( null != out ) {
				out.close();
			}
		}
		
	}
}
