package kr.co.controller;

import java.net.URLEncoder;
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
	@RequestMapping(value = "/mainoption" , method = RequestMethod.GET)
	public String MainOption(@RequestParam String option) throws Exception{
		System.out.println("ddddddddddddddd");
		if(option.equals("r")) {
			return "redirect:/view?boardnum=" + Service.GetRandomBoard().getBoardnum();
		}
		else if(option.equals("m")) {
			return "redirect:/view?boardnum=" + Service.GetManyBoard().getBoardnum();
		}
		else if(option.equals("f")) {
			return "redirect:/view?boardnum=" + Service.GetFinishBoard().getBoardnum();
		}
		else {
			return null;
		}
	}
	
	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public String ViewMain(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		
		BoardDTO selectoption = new BoardDTO();

		selectoption.setTopic("자유");
		List<BoardDTO> freeboard = Service.GetBoardList(selectoption);//불러온 자유 글 목록
		System.out.println("메이저 : " + selectoption.getMajor());
		selectoption.setTopic("수업");
		List<BoardDTO> subjectboard = Service.GetBoardList(selectoption);//불러온 수업 글 목록
		selectoption.setTopic("공부");
		List<BoardDTO> studyboard = Service.GetBoardList(selectoption);//불러온 공부 글 목록
		selectoption.setTopic("진로");
		List<BoardDTO> careerboard = Service.GetBoardList(selectoption);//불러온 진로 글 목록
		
		selectoption.setTopic(null);
		selectoption.setMajor("컴퓨터공학");
		List<BoardDTO> comboard = Service.GetBoardList(selectoption);//불러온 진로 글 목록
		selectoption.setMajor("정보통신학");
		List<BoardDTO> infoboard = Service.GetBoardList(selectoption);//불러온 진로 글 목록
		selectoption.setMajor("경영학");
		List<BoardDTO> manageboard = Service.GetBoardList(selectoption);//불러온 진로 글 목록
		
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
		model.addAttribute("comboard",comboard);
		System.out.println("크기 : " + comboard.size());
		model.addAttribute("infoboard",infoboard);
		model.addAttribute("manageboard",manageboard);
		
		
		
		
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
			httpsession.setAttribute("history", request.getHeader("referer").substring(21));// 로그인 전 주소를 잘라 저장
			return "/login";//다시 로그인창 띄우기
		}
		else {
			return "redirect:" + httpsession.getAttribute("history");//로그인하기 전 창으로 이동
		}
		
	}
	
	/**로그인페이지에서 로그인 버튼 누를시**/
	@ResponseBody
	@RequestMapping(value = "/logindo" , method = RequestMethod.POST)
	public String LoginDo(HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어 오기
		MemberDTO dto = new MemberDTO();//멤버 DTO 객체 생성
		dto.setNum(request.getParameter("num"));//세션에 저장된 num
		dto.setPassword(request.getParameter("password"));//세션에 저장된 password 설정
		String CheckLogin = null;//로그인 체크 변수 초기화

		
		if(Service.LoginDo(dto)==null){//만약 DB에 로그인 정보가 없다면
			CheckLogin = "0";
			
			return CheckLogin;//로그인 불가
		}
		else {//있다면
			CheckLogin = "1";//로그인 가능
			httpsession.setAttribute("LoginUser", request.getParameter("num"));//세션에 로그인한 유저 정보 저장
			return CheckLogin;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/numcheck",method = RequestMethod.POST)
	public String CheckNum(HttpServletRequest request)throws Exception{
		MemberDTO dto = new MemberDTO();//멤버 DTO 객체 생성
		dto.setNum(request.getParameter("num"));//세션에 저장된 num 설정
		if(Service.numcheck(dto)==null) {//DB에서 얻어온 정보와 같다면 
			return "1";
		}
		else {//DB에서 얻어온 정보와 다르다면
			return "0";
		}
		
		
	}
	
	/*-------------ListView-------------*/
	
	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public String ViewList(@RequestParam(required = false,defaultValue="%") String title,//주소창에 넘어온 변수를 받는다 없을시 %를 기본값으로
			@RequestParam(required = false,defaultValue="%") String writer,//주소창에 넘어온 변수를 받는다 없을시 %를 기본값으로
			@RequestParam(required = false,defaultValue="%") String topic,//주소창에 넘어온 변수를 받는다 없을시 %를 기본값으로
			@RequestParam(required = false,defaultValue="%") String major,//주소창에 넘어온 변수를 받는다 없을시 %를 기본값으로
			@RequestParam int page,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		
		List<BoardDTO> selectboard = null;
		BoardDTO selectoption = new BoardDTO();//보드 DTO 객체 생성
		//( ( 현재 보고 있는 페이지 번호 - 1) / 총 페이지 사이즈 ) * 총 페이지 사이즈 +1
		selectoption.setTopic(topic);//보드 DTO 변수 설정
		selectoption.setMajor(major);//보드 DTO 변수 설정
		selectoption.setWriter(writer);//보드 DTO 변수 설정
		selectoption.setTitle(title);//보드 DTO 변수 설정
		selectboard = Service.GetBoardList(selectoption);//불러온 글 목록}
		////////////////////////////////////////////////////////////////////////////////////
		int pageslice=10;//10개의 글 단위로 끊기
		int maxpage=0;// 최대 페이지 변수
			if(selectboard.size()>=pageslice) {//10보다 많은 글이 있고
				if(selectboard.size()%pageslice==0) {//10의 배수다
					maxpage = selectboard.size()/pageslice;//불러온페이지/10을 저장
				}
				else {
					maxpage = (selectboard.size()/pageslice)+1;//불러온페이지/10 +1을 저장
				}
			}else {
				maxpage = 1;//불러온페이지 없을시
			}
		int start = ((page - 1) / 5)*5+1;//시작페이지
		int end = start + 5 - 1;//마지막페이지
		if(end > maxpage) {
			end = maxpage;
		}
		
		model.addAttribute("startpage" , start);//뷰로 넘기기 위한 시작페이지 변수
		model.addAttribute("endpage" , end);//뷰로 넘기기 위한 최대페이지 변수
		model.addAttribute("topic",topic);//뷰로 넘기기 위한 최대페이지 변수
		model.addAttribute("major",major);//뷰로 넘기기 위한 최대페이지 변수
		model.addAttribute("title",title);//뷰로 넘기기 위한 최대페이지 변수
		model.addAttribute("writer",writer);//뷰로 넘기기 위한 최대페이지 변수
		model.addAttribute("maxpage",maxpage);//뷰로 넘기기 위한 최대페이지 변수
		
		////////////////////////////////////////////////////////////////////////////////////
		//페이징
			if(selectboard.size()==page*pageslice  || selectboard.size()>page*pageslice) {//글수가 10의 배수로  떨어질때
				selectboard = selectboard.subList((page*pageslice)-pageslice, (page*pageslice));
				//시작페이지부터 ~ 시작페이지+10까지 자르고 그 결과를 저장
			}
			else if(selectboard.size()<page*pageslice){//글수가 10보다 적을때
				selectboard = selectboard.subList((page*pageslice)-pageslice, selectboard.size());//시작페이지 ~ 끝까지
			}
		/////////////////////////////////////////////////////////////////////////////////////
		
		for(BoardDTO dto : selectboard) {//제목이 길면 줄이기
			if(dto.getTitle().length() > 10) {//제목이 10이상이면 
				dto.setTitle(dto.getTitle().substring(0,8)+"...");//크기를 8로 만들고 뒤에 ...붙이기
			}
		}
		model.addAttribute("selectboard",selectboard);//뷰에 불러온 글 목록 변수 저장
		
		return "/list";//뷰
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
		Service.JoinDo(dto);
		return "redirect:/";
	}

	/**보낸 이메일 번호 확인**/
	@ResponseBody
	@RequestMapping(value = "/numbercheck" , method = RequestMethod.POST)
	public String SendEmail(Model model,final HttpServletRequest request,HttpServletResponse response) throws Exception{
		final String email = request.getParameter("email"); //ajax통해 json으로 저장된 키에 대한 값 저장
		final int ran = new Random().nextInt(100000) + 10000;//랜덤 변수
		model.addAttribute("checknumber",String.valueOf(ran));//뷰로 보낼 랜덤 숫자
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				helper.setFrom("dltmdfuf95");//보내는사람
				helper.setTo(email);//받는 이메일
				helper.setSubject("인증메일"); //제목
				helper.setText(String.valueOf(ran), true);//보내는 내용
				
			}
		};
		mailSender.send(preparator);//메일 보내기
		return String.valueOf(ran);

	}
	/*-------------ViewView-------------*/
	
	@RequestMapping(value = "/view" , method = RequestMethod.GET)
	public void ViewView(@RequestParam int boardnum,Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		Service.ViewCount(Service.GetBoard(boardnum));//글보기를 할때마다 조회수를 늘리는 서비스
		
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		
		model.addAttribute("SelectedBoard",Service.GetBoard(boardnum));//선택된 글 가져오기
		model.addAttribute("SelectedBoardAnswerList",Service.GetAnswer(boardnum));//선택된 글 답 가져오기
	}

	/*-------------WriteView-------------*/
	
	@RequestMapping(value = "/write" , method = RequestMethod.GET)
	public void ViewWrite(@RequestParam(required = false,defaultValue="0") int num,
			@RequestParam(required = false,defaultValue="0") int boardnum,
			String kind,Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//현재 로그인된 아이디를 저장
		
		BoardDTO dto = Service.GetBoard(boardnum);
		AnswerDTO adto = Service.GetAnswerOne(num);
		
		
		
		model.addAttribute("selectedboard",dto);
		model.addAttribute("kind",kind);
		model.addAttribute("boardnum",boardnum);
		model.addAttribute("selectedboardanswer",adto);
		
	}
	
	/*-------------새로 글쓰기------------*/
	@RequestMapping(value = "/boardwrite" , method = RequestMethod.POST)
	public String BoardWrite(String option,BoardDTO dto,Model model,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		HttpSession httpsession = request.getSession();//세션 얻어오기
		dto.setNum((String)httpsession.getAttribute("LoginUser"));//세션에 저장된 즉 로그인이 되어있는가?
		
			Service.BoardWrite(dto);//쓴 글을 DB에 저장하는 서비스
			return "redirect:/list?topic=" + URLEncoder.encode(dto.getTopic(), "UTF-8") + "&page=1";
			//글을 쓴 후 그 글에 주제와 같은 글목록에 1페이지로 이ㅗㅇ
			
			
		
	}
	
	@RequestMapping(value = "/boardmodify" , method = RequestMethod.POST)
	public String BoardModify(BoardDTO dto,Model model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		
			Service.BoardModify(dto);
			return "redirect:/view?boardnum=" + dto.getBoardnum();
	}
	
	@RequestMapping(value = "/answerdelete" , method = RequestMethod.GET)
	public String AnswerDelete(AnswerDTO dto,Model model,HttpServletRequest request,HttpServletResponse response)throws Exception{
			System.out.println(dto.getNum());
			Service.AnswerDelete2(dto.getNum());
			return "redirect:/view?boardnum=" + dto.getBoardnum();
	}
	
	@RequestMapping(value = "/answermodify" , method = RequestMethod.POST)
	public String AnswerModify(AnswerDTO dto,Model model,HttpServletRequest request,HttpServletResponse response)throws Exception{
			
			Service.AnswerModify(dto);
			return "redirect:/view?boardnum=" + dto.getBoardnum();
	}
	
	@RequestMapping(value = "/answerwrite" , method = RequestMethod.POST)
	public String AnswerWrite(AnswerDTO dto,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();
		MemberDTO dto2 = new MemberDTO();
		
		dto2.setNum((String) httpsession.getAttribute("LoginUser"));
		
		dto.setWriter(Service.GetMemberInfo(dto2).getName());
		dto.setWriternum((String) httpsession.getAttribute("LoginUser"));
		Service.AnswerWrite(dto);
		return "redirect:/view?boardnum=" + dto.getBoardnum();
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.GET)
	public String BoardDelete(@RequestParam String boardnum,@RequestParam String topic) throws Exception{
		System.out.println(boardnum);
		Service.BoardDelete(boardnum);
		return "redirect:/list?topic=" + URLEncoder.encode(topic, "UTF-8") + "&page=1";
	}
	
	@RequestMapping(value = "/adop",method = RequestMethod.GET)
	public String Adop(@RequestParam int num,@RequestParam int boardnum) throws Exception{
		System.out.println(num);
		Service.AdopAnswer(num);
		Service.AdopBoard(boardnum);
		
		return "redirect:/view?boardnum="+ boardnum;
		
	}
	
}

