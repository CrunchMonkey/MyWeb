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
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		BoardDTO selectoption = new BoardDTO();

		selectoption.setTopic("����");
		List<BoardDTO> freeboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		System.out.println("������ : " + selectoption.getMajor());
		selectoption.setTopic("����");
		List<BoardDTO> subjectboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		selectoption.setTopic("����");
		List<BoardDTO> studyboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		selectoption.setTopic("����");
		List<BoardDTO> careerboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		
		selectoption.setTopic(null);
		selectoption.setMajor("��ǻ�Ͱ���");
		List<BoardDTO> comboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		selectoption.setMajor("���������");
		List<BoardDTO> infoboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		selectoption.setMajor("�濵��");
		List<BoardDTO> manageboard = Service.GetBoardList(selectoption);//�ҷ��� ���� �� ���
		
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
		model.addAttribute("comboard",comboard);
		System.out.println("ũ�� : " + comboard.size());
		model.addAttribute("infoboard",infoboard);
		model.addAttribute("manageboard",manageboard);
		
		
		
		
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
			httpsession.setAttribute("history", request.getHeader("referer").substring(21));// �α��� �� �ּҸ� �߶� ����
			return "/login";//�ٽ� �α���â ����
		}
		else {
			return "redirect:" + httpsession.getAttribute("history");//�α����ϱ� �� â���� �̵�
		}
		
	}
	
	/**�α������������� �α��� ��ư ������**/
	@ResponseBody
	@RequestMapping(value = "/logindo" , method = RequestMethod.POST)
	public String LoginDo(HttpServletResponse response,HttpServletRequest request) throws Exception{
		HttpSession httpsession = request.getSession();//���� ��� ����
		MemberDTO dto = new MemberDTO();//��� DTO ��ü ����
		dto.setNum(request.getParameter("num"));//���ǿ� ����� num
		dto.setPassword(request.getParameter("password"));//���ǿ� ����� password ����
		String CheckLogin = null;//�α��� üũ ���� �ʱ�ȭ

		
		if(Service.LoginDo(dto)==null){//���� DB�� �α��� ������ ���ٸ�
			CheckLogin = "0";
			
			return CheckLogin;//�α��� �Ұ�
		}
		else {//�ִٸ�
			CheckLogin = "1";//�α��� ����
			httpsession.setAttribute("LoginUser", request.getParameter("num"));//���ǿ� �α����� ���� ���� ����
			return CheckLogin;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/numcheck",method = RequestMethod.POST)
	public String CheckNum(HttpServletRequest request)throws Exception{
		MemberDTO dto = new MemberDTO();//��� DTO ��ü ����
		dto.setNum(request.getParameter("num"));//���ǿ� ����� num ����
		if(Service.numcheck(dto)==null) {//DB���� ���� ������ ���ٸ� 
			return "1";
		}
		else {//DB���� ���� ������ �ٸ��ٸ�
			return "0";
		}
		
		
	}
	
	/*-------------ListView-------------*/
	
	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public String ViewList(@RequestParam(required = false,defaultValue="%") String title,//�ּ�â�� �Ѿ�� ������ �޴´� ������ %�� �⺻������
			@RequestParam(required = false,defaultValue="%") String writer,//�ּ�â�� �Ѿ�� ������ �޴´� ������ %�� �⺻������
			@RequestParam(required = false,defaultValue="%") String topic,//�ּ�â�� �Ѿ�� ������ �޴´� ������ %�� �⺻������
			@RequestParam(required = false,defaultValue="%") String major,//�ּ�â�� �Ѿ�� ������ �޴´� ������ %�� �⺻������
			@RequestParam int page,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		List<BoardDTO> selectboard = null;
		BoardDTO selectoption = new BoardDTO();//���� DTO ��ü ����
		//( ( ���� ���� �ִ� ������ ��ȣ - 1) / �� ������ ������ ) * �� ������ ������ +1
		selectoption.setTopic(topic);//���� DTO ���� ����
		selectoption.setMajor(major);//���� DTO ���� ����
		selectoption.setWriter(writer);//���� DTO ���� ����
		selectoption.setTitle(title);//���� DTO ���� ����
		selectboard = Service.GetBoardList(selectoption);//�ҷ��� �� ���}
		////////////////////////////////////////////////////////////////////////////////////
		int pageslice=10;//10���� �� ������ ����
		int maxpage=0;// �ִ� ������ ����
			if(selectboard.size()>=pageslice) {//10���� ���� ���� �ְ�
				if(selectboard.size()%pageslice==0) {//10�� �����
					maxpage = selectboard.size()/pageslice;//�ҷ���������/10�� ����
				}
				else {
					maxpage = (selectboard.size()/pageslice)+1;//�ҷ���������/10 +1�� ����
				}
			}else {
				maxpage = 1;//�ҷ��������� ������
			}
		int start = ((page - 1) / 5)*5+1;//����������
		int end = start + 5 - 1;//������������
		if(end > maxpage) {
			end = maxpage;
		}
		
		model.addAttribute("startpage" , start);//��� �ѱ�� ���� ���������� ����
		model.addAttribute("endpage" , end);//��� �ѱ�� ���� �ִ������� ����
		model.addAttribute("topic",topic);//��� �ѱ�� ���� �ִ������� ����
		model.addAttribute("major",major);//��� �ѱ�� ���� �ִ������� ����
		model.addAttribute("title",title);//��� �ѱ�� ���� �ִ������� ����
		model.addAttribute("writer",writer);//��� �ѱ�� ���� �ִ������� ����
		model.addAttribute("maxpage",maxpage);//��� �ѱ�� ���� �ִ������� ����
		
		////////////////////////////////////////////////////////////////////////////////////
		//����¡
			if(selectboard.size()==page*pageslice  || selectboard.size()>page*pageslice) {//�ۼ��� 10�� �����  ��������
				selectboard = selectboard.subList((page*pageslice)-pageslice, (page*pageslice));
				//�������������� ~ ����������+10���� �ڸ��� �� ����� ����
			}
			else if(selectboard.size()<page*pageslice){//�ۼ��� 10���� ������
				selectboard = selectboard.subList((page*pageslice)-pageslice, selectboard.size());//���������� ~ ������
			}
		/////////////////////////////////////////////////////////////////////////////////////
		
		for(BoardDTO dto : selectboard) {//������ ��� ���̱�
			if(dto.getTitle().length() > 10) {//������ 10�̻��̸� 
				dto.setTitle(dto.getTitle().substring(0,8)+"...");//ũ�⸦ 8�� ����� �ڿ� ...���̱�
			}
		}
		model.addAttribute("selectboard",selectboard);//�信 �ҷ��� �� ��� ���� ����
		
		return "/list";//��
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
		Service.JoinDo(dto);
		return "redirect:/";
	}

	/**���� �̸��� ��ȣ Ȯ��**/
	@ResponseBody
	@RequestMapping(value = "/numbercheck" , method = RequestMethod.POST)
	public String SendEmail(Model model,final HttpServletRequest request,HttpServletResponse response) throws Exception{
		final String email = request.getParameter("email"); //ajax���� json���� ����� Ű�� ���� �� ����
		final int ran = new Random().nextInt(100000) + 10000;//���� ����
		model.addAttribute("checknumber",String.valueOf(ran));//��� ���� ���� ����
		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				helper.setFrom("dltmdfuf95");//�����»��
				helper.setTo(email);//�޴� �̸���
				helper.setSubject("��������"); //����
				helper.setText(String.valueOf(ran), true);//������ ����
				
			}
		};
		mailSender.send(preparator);//���� ������
		return String.valueOf(ran);

	}
	/*-------------ViewView-------------*/
	
	@RequestMapping(value = "/view" , method = RequestMethod.GET)
	public void ViewView(@RequestParam int boardnum,Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//���� ������
		Service.ViewCount(Service.GetBoard(boardnum));//�ۺ��⸦ �Ҷ����� ��ȸ���� �ø��� ����
		
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		model.addAttribute("SelectedBoard",Service.GetBoard(boardnum));//���õ� �� ��������
		model.addAttribute("SelectedBoardAnswerList",Service.GetAnswer(boardnum));//���õ� �� �� ��������
	}

	/*-------------WriteView-------------*/
	
	@RequestMapping(value = "/write" , method = RequestMethod.GET)
	public void ViewWrite(@RequestParam(required = false,defaultValue="0") int num,
			@RequestParam(required = false,defaultValue="0") int boardnum,
			String kind,Model model,HttpServletRequest request)throws Exception{
		HttpSession httpsession = request.getSession();//���� ������
		model.addAttribute("LoginUser" , httpsession.getAttribute("LoginUser"));//���� �α��ε� ���̵� ����
		
		BoardDTO dto = Service.GetBoard(boardnum);
		AnswerDTO adto = Service.GetAnswerOne(num);
		
		
		
		model.addAttribute("selectedboard",dto);
		model.addAttribute("kind",kind);
		model.addAttribute("boardnum",boardnum);
		model.addAttribute("selectedboardanswer",adto);
		
	}
	
	/*-------------���� �۾���------------*/
	@RequestMapping(value = "/boardwrite" , method = RequestMethod.POST)
	public String BoardWrite(String option,BoardDTO dto,Model model,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		HttpSession httpsession = request.getSession();//���� ������
		dto.setNum((String)httpsession.getAttribute("LoginUser"));//���ǿ� ����� �� �α����� �Ǿ��ִ°�?
		
			Service.BoardWrite(dto);//�� ���� DB�� �����ϴ� ����
			return "redirect:/list?topic=" + URLEncoder.encode(dto.getTopic(), "UTF-8") + "&page=1";
			//���� �� �� �� �ۿ� ������ ���� �۸�Ͽ� 1�������� �̤Ǥ�
			
			
		
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

