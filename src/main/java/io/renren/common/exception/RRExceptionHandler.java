package io.renren.common.exception;

import io.renren.common.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@ControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(RRException.class)
	@ResponseBody
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	@ResponseBody
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	@ResponseBody
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}

	/*@ExceptionHandler(BizException.class)
	public ModelAndView handleBizException(BizException e){
		System.out.println("进来了");
		//向前台返回错误信息
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception",e.getMsg());
		logger.error(e.getMsg(),e);
		modelAndView.setViewName("/error");
		return modelAndView;
	}*/

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}

}
