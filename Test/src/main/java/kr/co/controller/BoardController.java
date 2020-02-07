package kr.co.controller;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dao.BoardDAO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;
import kr.co.service.BoardService;
import org.springframework.mail.javamail.JavaMailSenderImpl;	

@Controller
public class BoardController {
	
	@Inject
	BoardService Service;
	
	@Autowired 
	private JavaMailSenderImpl mailSender;

	
	/*-------------MainView-------------*/
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String ViewMain(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		
		List<BoardDTO> majorboard = Service.GetMajorBoard();//불러온 학과 글 목록
		List<BoardDTO> freeboard = Service.GetFreeBoard();//불러온 자유 글 목록
		List<BoardDTO> subjectboard = Service.GetSubjectBoard();//불러온 과목 글 목록
		List<BoardDTO> studyboard = Service.GetStudyBoard();//불러온 성적 글 목록
		
		model.addAttribute("majorboard",majorboard);
		model.addAttribute("freeboard",freeboard);
		model.addAttribute("subjectboard",subjectboard);
		model.addAttribute("studyboard",studyboard);
		
		System.out.println(studyboard);
		
		return "/main";
	}
	
	@RequestMapping(value = "/main/loggout" , method = RequestMethod.GET)
	public String MainLogOut(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();
		httpsession.removeAttribute("LoginUser");
		System.out.println("로그아웃되었습니다");
		return "redirect:/";
	}
	
	
	/*-------------LoginView-------------*/
	/**로그인페이지 보여주기**/
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String ViewLogin(HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();
		if(httpsession.getAttribute("LoginUser")==null) {//로그인한 유저가 없다면
			System.out.println("로그인 되어있지 않네요");
			return "/login";//다시 로그인창 띄우기
		}
		else {
			System.out.println("로그인 되어있군요");
			return "redirect:/";//메인창으로 이동
		}
		
	}
	
	/**로그인페이지에서 로그인 버튼 누를시**/
	@RequestMapping(value =  "/login/login" , method = RequestMethod.POST)
	public String CheckLogin(HttpServletResponse response,HttpServletRequest request,MemberDTO dto) throws Exception{
		HttpSession httpsession = request.getSession();
		if(Service.CheckLogin(dto)!=null) {
			httpsession.setAttribute("LoginUser", dto.getId());//로그인한 유저를 세션에 ID을 얻어와 저장
			System.out.println("로그인성공");//로그인 성공 메시지
			return "redirect:/";//로그인상태로 메인으로
		}
		else {
			System.out.println("로그인불가");//로그인 불가 메시지
			return "/login";//다시 로그인페이지로
		}
	}
	
	/*-------------ListView-------------*/
	
	/*-------------JoinView-------------*/
	/**조인페이지 보여주기**/
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String ViewJoin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("checknumber",null);
		return "/join";
	}
	
	/**회원가입누름**/
	@RequestMapping(value = "/join/join" , method = RequestMethod.POST)
	public String Join(MemberDTO dto) throws Exception{
		Service.Join(dto);
		return "/login";
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/idcheck" , method = RequestMethod.POST)
	public String CheckId(HttpServletRequest request) throws Exception{
		String id = request.getParameter("joinid");
		
		if(Service.CheckJoin(id)==null) {
			return  "0";
		}
		else {
			return  "1";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/numbercheck" , method = RequestMethod.POST)
	public String SendEmail(final HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		final String email = request.getParameter("email"); 
		final int ran = new Random().nextInt(100000) + 10000;
		session.setAttribute("checknumber", String.valueOf(ran));
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				helper.setFrom("dltmdfuf95"); 
				helper.setTo(email); 
				helper.setSubject("인증메일"); 
				helper.setText(String.valueOf(ran), true);
				
			}
		};
		System.out.println(session.getAttribute("checknumber"));
		mailSender.send(preparator);
		return String.valueOf(ran);

	}
	/**아이디중복체크**/
	/**학번중복체크**/
	/*-------------ViewView-------------*/
}

