package com.ykp.springbootdemo.config;

import com.ykp.springbootdemo.handler.MyAuthenticationFailureHandler;
import com.ykp.springbootdemo.handler.MyAuthenticationSuccessHandler;
import com.ykp.springbootdemo.service.impl.SecurityProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProvider securityProvider;
	
	@Autowired
	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
	MyAuthenticationFailureHandler myAuthenticationFailureHandler;
	
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		/**
		 * form-login属性详解
		 * form-login是spring security命名空间配置登录相关信息的标签,它包含如下属性：
		 * 1. login-page 自定义登录页url,默认为/login
		 * 2. login-processing-url 登录请求拦截的url,也就是form表单提交时指定的action
		 * 3. default-target-url 默认登录成功后跳转的url
		 * 4. always-use-default-target 是否总是使用默认的登录成功后跳转url
		 * 5. authentication-failure-url 登录失败后跳转的url
		 * 6. username-parameter 用户名的请求字段 默认为userName
		 * 7. password-parameter 密码的请求字段 默认为password
		 * 8. authentication-success-handler-ref
		 * 指向一个AuthenticationSuccessHandler用于处理认证成功的请求
		 * ,不能和default-target-url还有always-use-default-target同时使用
		 * 9. authentication-success-forward-url
		 * 用于authentication-failure-handler-ref
		 * 10. authentication-failure-handler-ref
		 * 指向一个AuthenticationFailureHandler用于处理失败的认证请求
		 * 11. authentication-failure-forward-url
		 * 用于authentication-failure-handler-ref
		 * 12. authentication-details-source-ref
		 * 指向一个AuthenticationDetailsSource,在认证过滤器中使用
		 */
		
		// 定制登录行为
		configFormLogin( http );
		// 请求权限配置
		configAuthorizeRequests( http );
		// csrf配置
		configCsrf( http );
		// 配置provider
		configProvider( http );
	}
	
	/**
	 * 定制登录行为
	 */
	private void configFormLogin( HttpSecurity http ) throws Exception {
		http.formLogin().// 定制登录行为
				loginPage( "/login.html" ).// 设置登录页面
				defaultSuccessUrl( "/index.html" ).loginProcessingUrl( "/user/login" ).// 自定义的登录接口
				successHandler( myAuthenticationSuccessHandler ).// 自定义登录成功处理
				failureHandler( myAuthenticationFailureHandler );// 自定义登录失败处理
	}
	
	/**
	 * 请求权限配置
	 * 授权简介:一般的人会认为，不同的角色登录进同一个系统，根据角色权限的不同，看到的菜单不同就是控制授权。其实并不是的，
	 * 菜单的是否显示只是前端交互上的一个设计而已，真正需要授权的地方是接口的访问。
	 * 普通的系统通常会有两个端，一个是给用户用的业务系统（比如购物商城的买家端），一个是给公司运营人员用的管理端（可以统计销售量，用户量等信息）。
	 * 业务端的权限通常比较简单，可以区分为是否登录，或者简单的角色区分（比如普通用户，VIP用户）。管理端就相对复杂了，公司越大角色就越多，
	 * 权限就分得越细致。
	 */
	private void configAuthorizeRequests( HttpSecurity http ) throws Exception {
		// 注意角色名字要和loadUserByUsername里设置的角色名字保持一致，对应规则是ADMIN<->ROLE_ADMIN
		// AuthorityUtils.commaSeparatedStringToAuthorityList接受一个字符串数组，
		// 里面就是用户的角色，比如在hasRole里配置的是”ADMIN”，则AuthorityUtils.commaSeparatedStringToAuthorityLis
		// 需要传入”ROLE_ADMIN”，ADMIN是要区分大小写的
		
		// 方法一：权限配置写在代码里
		http.authorizeRequests()
				.// 请求权限配置
				antMatchers( "/login.html", "/user/queryQRCode", "/user/toPage/**", "/css/**", "/images/**",
						"/favicon.ico", "/jms/**", "/student/**" ).permitAll().// 设置所有人都可以访问登录页面
				anyRequest().authenticated();// 其他任何请求认证后（登录后）可以访问，需要注意的是，anyRequest()需要放在最后
	}
	
	/**
	 * csrf配置
	 */
	private void configCsrf( HttpSecurity http ) throws Exception {
		http.csrf().// 配置csrf
				disable();// 关闭csrf防护
	}
	
	/**
	 * provider配置
	 */
	private void configProvider( HttpSecurity http ) throws Exception {
		http.authenticationProvider( securityProvider );
	}
}
