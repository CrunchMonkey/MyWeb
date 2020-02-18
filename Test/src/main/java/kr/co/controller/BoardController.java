package kr.co.controller;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.dao.BoardDAO;
import kr.co.dto.AnswerDTO;
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
		
		BoardDTO selectoption = new BoardDTO();

		selectoption.setTopic("자유");
		List<BoardDTO> freeboard = Service.GetBoardList(selectoption);//불러온 자유 글 목록
		selectoption.setTopic("수업");
		List<BoardDTO> subjectboard = Service.GetBoardList(selectoption);//불러온 수업 글 목록
		selectoption.setTopic("공부");
		List<BoardDTO> studyboard = Service.GetBoardList(selectoption);//불러온 공부 글 목록
		selectoption.setTopic("진로");
		List<BoardDTO> careerboard = Service.GetBoardList(selectoption);//불러온 진로 글 목록
		
		for(BoardDTO dto : freeboard) {//제목이 길면 줄이기
			if(dto.getTitle().length() > 7) {
				dto.setTitle(dto.getTitle().substring(0,7)+"...");
			}
		}
		for(BoardDTO dto : subjectboard) {//제목이 길면 줄이기
			if(dto.getTitle().length() > 7) {
				dto.setTitle(dto.getTitle().substring(0,7)+"...");
			}
		}
		for(BoardDTO dto : studyboard) {//제목이 길면 줄이기
			if(dto.getTitle().length() > 7) {
				dto.setTitle(dto.getTitle().substring(0,7)+"...");
			}
		}
		for(BoardDTO dto : careerboard) {//제목이 길면 줄이기
			if(dto.getTitle().length() > 7) {
				dto.setTitle(dto.getTitle().substring(0,7)+"...");
			}
		}
		
		model.addAttribute("freeboard",freeboard);
		model.addAttribute("subjectboard",subjectboard);
		model.addAttribute("studyboard",studyboard);
		model.addAttribute("careerboard",careerboard);
		
		return "/main";
	}
	
	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
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
			return "/login";//다시 로그인창 띄우기
		}
		else {
			return "redirect:/";//메인창으로 이동
		}
		
	}
	
	/**로그인페이지에서 로그인 버튼 누를시**/
	@ResponseBody
	@RequestMapping(value = "/logindo" , method = RequestMethod.POST)
	public String LoginDo(HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();
		MemberDTO dto = new MemberDTO();
		dto.setNum(request.getParameter("num"));
		dto.setPassword(request.getParameter("password"));
		String CheckLogin = null;
		
		if(Service.LoginDo(dto)==null){
			CheckLogin = "0";
			
			return CheckLogin;
		}
		else {
			CheckLogin = "1";
			httpsession.setAttribute("LoginUser", request.getParameter("num"));
			return CheckLogin;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/numcheck",method = RequestMethod.POST)
	public String CheckNum(HttpServletRequest request)throws Exception{
		System.out.println("numcheck");
		MemberDTO dto = new MemberDTO();
		dto.setNum(request.getParameter("num"));
		if(Service.numcheck(dto)==null) {
			return "1";
		}
		else {
			return "0";
		}
		
		
	}
	
	/*-------------ListView-------------*/
	
	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public String ViewList(@RequestParam(required = false,defaultValue="%") String title,@RequestParam(required = false,defaultValue="%") String writer,@RequestParam(required = false,defaultValue="%") String topic,@RequestParam int page,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		
		List<BoardDTO> selectboard = null;
		BoardDTO selectoption = new BoardDTO();
		//( ( 현재 보고 있는 페이지 번호 - 1) / 총 페이지 사이즈 ) * 총 페이지 사이즈 +1
		System.out.println(writer + "," + topic);
		selectoption.setTopic(topic);
		selectoption.setWriter(writer);
		selectoption.setTitle(title);
		selectboard = Service.GetBoardList(selectoption);//불러온 글 목록}

		////////////////////////////////////////////////////////////////////////////////////
		int pageslice=10;
		int maxpage=0;
			if(selectboard.size()>=pageslice) {//15보다 많은 글이 있고
				if(selectboard.size()%pageslice==0) {//15의 배수다
					maxpage = selectboard.size()/pageslice;
				}
				else {
					maxpage = (selectboard.size()/pageslice)+1;
				}
			}else {
				maxpage = 1;
			}
		int start = ((page - 1) / 5)*5+1;//시작페이지
		int end = start + 5 - 1;//마지막페이지
		if(end > maxpage) {
			end = maxpage;
		}
		
		model.addAttribute("startpage" , start);
		model.addAttribute("endpage" , end);
		model.addAttribute("topic",topic);
		model.addAttribute("writer",writer);
		model.addAttribute("maxpage",maxpage);
		
		////////////////////////////////////////////////////////////////////////////////////
		//페이징
			if(selectboard.size()==page*pageslice) {//글수가 15의 배수로  떨어질때
				selectboard = selectboard.subList((page*pageslice)-pageslice, (page*pageslice));
			}
			else if(selectboard.size()>page*pageslice){
				selectboard = selectboard.subList((page*pageslice)-pageslice, (page*pageslice));
			}
			else if(selectboard.size()<page*pageslice){
				selectboard = selectboard.subList((page*pageslice)-pageslice, selectboard.size());
			}
		/////////////////////////////////////////////////////////////////////////////////////
		
		for(BoardDTO dto : selectboard) {//제목이 길면 줄이기
			if(dto.getTitle().length() > 10) {
				dto.setTitle(dto.getTitle().substring(0,10)+"...");
			}
		}
		////////////////////////////////////////////////////////////
		
		model.addAttribute("selectboard",selectboard);
		
		return "/list";
	}
	
	/*-------------JoinView-------------*/
	/**조인페이지 보여주기**/
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String ViewJoin(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		return "/join";
	}
	
	/**회원가입하기 **/
	@RequestMapping(value = "/joinDo" , method = RequestMethod.POST)
	public String JoinDo(MemberDTO dto) throws Exception{
		System.out.println("JoinDo");
		Service.JoinDo(dto);
		return "redirect:/";
	}

	/**보낸 이메일 번호 확인**/
	@ResponseBody
	@RequestMapping(value = "/numbercheck" , method = RequestMethod.POST)
	public String SendEmail(Model model,final HttpServletRequest request,HttpServletResponse response) throws Exception{
		final String email = request.getParameter("email"); //ajax통해 json으로 저장된 키에 대한 값 저장
		final int ran = new Random().nextInt(100000) + 10000;//랜덤 변수
		model.addAttribute("checknumber",String.valueOf(ran));
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				helper.setFrom("dltmdfuf95");//보내는사람
				helper.setTo(email);//
				helper.setSubject("인증메일"); //제목
				helper.setText(String.valueOf(ran), true);//보내는 내용
				
			}
		};
		mailSender.send(preparator);
		return String.valueOf(ran);

	}
	/*-------------ViewView-------------*/
	
	@RequestMapping(value = "/view" , method = RequestMethod.GET)
	public void ViewView(@RequestParam int boardnum,Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		model.addAttribute("SelectedBoard",Service.GetBoard(boardnum));//선택된 글 가져오기
		model.addAttribute("SelectedBoardAnswerList",Service.GetAnswer(boardnum));//선택된 글 답 가져오기
	}	
	
	/*-------------WriteView-------------*/
	
	@RequestMapping(value = "/write" , method = RequestMethod.GET)
	public void ViewWrite(Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		
		
	}
	
	@RequestMapping(value = "/boardwrite" , method = RequestMethod.POST)
	public String BoardWrite(BoardDTO dto,Model model,HttpServletRequest request)throws Exception{
		System.out.println("boardwrite");
		Service.BoardWrite(dto);
		return "redirect:/list?topic=ooo";
	}
}

