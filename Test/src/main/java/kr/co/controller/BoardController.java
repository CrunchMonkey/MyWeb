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
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		List<BoardDTO> majorboard = Service.GetMajorBoard();//�ҷ��� �а� �� ���
		List<BoardDTO> freeboard = Service.GetFreeBoard();//�ҷ��� ���� �� ���
		List<BoardDTO> subjectboard = Service.GetSubjectBoard();//�ҷ��� ���� �� ���
		List<BoardDTO> studyboard = Service.GetStudyBoard();//�ҷ��� ���� �� ���
		
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
		System.out.println("�α׾ƿ��Ǿ����ϴ�");
		return "redirect:/";
	}
	
	
	/*-------------LoginView-------------*/
	/**�α��������� �����ֱ�**/
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String ViewLogin(HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();
		if(httpsession.getAttribute("LoginUser")==null) {//�α����� ������ ���ٸ�
			System.out.println("�α��� �Ǿ����� �ʳ׿�");
			return "/login";//�ٽ� �α���â ����
		}
		else {
			System.out.println("�α��� �Ǿ��ֱ���");
			return "redirect:/";//����â���� �̵�
		}
		
	}
	
	/**�α������������� �α��� ��ư ������**/
	@RequestMapping(value =  "/login/login" , method = RequestMethod.POST)
	public String CheckLogin(HttpServletResponse response,HttpServletRequest request,MemberDTO dto) throws Exception{
		HttpSession httpsession = request.getSession();
		if(Service.CheckLogin(dto)!=null) {
			httpsession.setAttribute("LoginUser", dto.getId());//�α����� ������ ���ǿ� ID�� ���� ����
			System.out.println("�α��μ���");//�α��� ���� �޽���
			return "redirect:/";//�α��λ��·� ��������
		}
		else {
			System.out.println("�α��κҰ�");//�α��� �Ұ� �޽���
			return "/login";//�ٽ� �α�����������
		}
	}
	
	/*-------------ListView-------------*/
	
	/*-------------JoinView-------------*/
	/**���������� �����ֱ�**/
	@RequestMapping(value = "/join" , method = RequestMethod.GET)
	public String ViewJoin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.setAttribute("checknumber",null);
		return "/join";
	}
	
	/**ȸ�����Դ���**/
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
				helper.setSubject("��������"); 
				helper.setText(String.valueOf(ran), true);
				
			}
		};
		System.out.println(session.getAttribute("checknumber"));
		mailSender.send(preparator);
		return String.valueOf(ran);

	}
	/**���̵��ߺ�üũ**/
	/**�й��ߺ�üũ**/
	/*-------------ViewView-------------*/
}

