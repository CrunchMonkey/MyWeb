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
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		BoardDTO selectoption = new BoardDTO();

		selectoption.setTopic("����");
		List<BoardDTO> freeboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		selectoption.setTopic("����");
		List<BoardDTO> subjectboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		selectoption.setTopic("����");
		List<BoardDTO> studyboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		selectoption.setTopic("����");
		List<BoardDTO> careerboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		
		for(BoardDTO dto : freeboard) {//������ ��� ���̱�
			if(dto.getTitle().length() > 7) {
				dto.setTitle(dto.getTitle().substring(0,7)+"...");
			}
		}
		for(BoardDTO dto : subjectboard) {//������ ��� ���̱�
			if(dto.getTitle().length() > 7) {
				dto.setTitle(dto.getTitle().substring(0,7)+"...");
			}
		}
		for(BoardDTO dto : studyboard) {//������ ��� ���̱�
			if(dto.getTitle().length() > 7) {
				dto.setTitle(dto.getTitle().substring(0,7)+"...");
			}
		}
		for(BoardDTO dto : careerboard) {//������ ��� ���̱�
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
		System.out.println("�α׾ƿ��Ǿ����ϴ�");
		return "redirect:/";
	}
	
	
	/*-------------LoginView-------------*/
	/**�α��������� �����ֱ�**/
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String ViewLogin(HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();
		
		if(httpsession.getAttribute("LoginUser")==null) {//�α����� ������ ���ٸ�
			return "/login";//�ٽ� �α���â ����
		}
		else {
			return "redirect:/";//����â���� �̵�
		}
		
	}
	
	/**�α������������� �α��� ��ư ������**/
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
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		List<BoardDTO> selectboard = null;
		BoardDTO selectoption = new BoardDTO();
		//( ( ���� ���� �ִ� ������ ��ȣ - 1) / �� ������ ������ ) * �� ������ ������ +1
		System.out.println(writer + "," + topic);
		selectoption.setTopic(topic);
		selectoption.setWriter(writer);
		selectoption.setTitle(title);
		selectboard = Service.GetBoardList(selectoption);//�ҷ��� �� ���}

		////////////////////////////////////////////////////////////////////////////////////
		int pageslice=10;
		int maxpage=0;
			if(selectboard.size()>=pageslice) {//15���� ���� ���� �ְ�
				if(selectboard.size()%pageslice==0) {//15�� �����
					maxpage = selectboard.size()/pageslice;
				}
				else {
					maxpage = (selectboard.size()/pageslice)+1;
				}
			}else {
				maxpage = 1;
			}
		int start = ((page - 1) / 5)*5+1;//����������
		int end = start + 5 - 1;//������������
		if(end > maxpage) {
			end = maxpage;
		}
		
		model.addAttribute("startpage" , start);
		model.addAttribute("endpage" , end);
		model.addAttribute("topic",topic);
		model.addAttribute("writer",writer);
		model.addAttribute("maxpage",maxpage);
		
		////////////////////////////////////////////////////////////////////////////////////
		//����¡
			if(selectboard.size()==page*pageslice) {//�ۼ��� 15�� �����  ��������
				selectboard = selectboard.subList((page*pageslice)-pageslice, (page*pageslice));
			}
			else if(selectboard.size()>page*pageslice){
				selectboard = selectboard.subList((page*pageslice)-pageslice, (page*pageslice));
			}
			else if(selectboard.size()<page*pageslice){
				selectboard = selectboard.subList((page*pageslice)-pageslice, selectboard.size());
			}
		/////////////////////////////////////////////////////////////////////////////////////
		
		for(BoardDTO dto : selectboard) {//������ ��� ���̱�
			if(dto.getTitle().length() > 10) {
				dto.setTitle(dto.getTitle().substring(0,10)+"...");
			}
		}
		////////////////////////////////////////////////////////////
		
		model.addAttribute("selectboard",selectboard);
		
		return "/list";
	}
	
	/*-------------JoinView-------------*/
	/**���������� �����ֱ�**/
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String ViewJoin(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		return "/join";
	}
	
	/**ȸ�������ϱ� **/
	@RequestMapping(value = "/joinDo" , method = RequestMethod.POST)
	public String JoinDo(MemberDTO dto) throws Exception{
		System.out.println("JoinDo");
		Service.JoinDo(dto);
		return "redirect:/";
	}

	/**���� �̸��� ��ȣ Ȯ��**/
	@ResponseBody
	@RequestMapping(value = "/numbercheck" , method = RequestMethod.POST)
	public String SendEmail(Model model,final HttpServletRequest request,HttpServletResponse response) throws Exception{
		final String email = request.getParameter("email"); //ajax���� json���� ����� Ű�� ���� �� ����
		final int ran = new Random().nextInt(100000) + 10000;//���� ����
		model.addAttribute("checknumber",String.valueOf(ran));
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				helper.setFrom("dltmdfuf95");//�����»��
				helper.setTo(email);//
				helper.setSubject("��������"); //����
				helper.setText(String.valueOf(ran), true);//������ ����
				
			}
		};
		mailSender.send(preparator);
		return String.valueOf(ran);

	}
	/*-------------ViewView-------------*/
	
	@RequestMapping(value = "/view" , method = RequestMethod.GET)
	public void ViewView(@RequestParam int boardnum,Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//���� ������
		
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		model.addAttribute("SelectedBoard",Service.GetBoard(boardnum));//���õ� �� ��������
		model.addAttribute("SelectedBoardAnswerList",Service.GetAnswer(boardnum));//���õ� �� �� ��������
	}	
	
	/*-------------WriteView-------------*/
	
	@RequestMapping(value = "/write" , method = RequestMethod.GET)
	public void ViewWrite(Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		
	}
	
	@RequestMapping(value = "/boardwrite" , method = RequestMethod.POST)
	public String BoardWrite(BoardDTO dto,Model model,HttpServletRequest request)throws Exception{
		System.out.println("boardwrite");
		Service.BoardWrite(dto);
		return "redirect:/list?topic=ooo";
	}
}

