package kr.co.dao;

import java.util.List;

import kr.co.dto.AnswerDTO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;

public interface BoardDAO {
	//***�Խ��ǰ���***
	public List<BoardDTO> GetCareerBoard() throws Exception;//�������� �� �ҷ�����
	public List<BoardDTO> GetStudyBoard() throws Exception;//�������� �� �ҷ�����
	public List<BoardDTO> GetFreeBoard() throws Exception;//�������� �� �ҷ�����
	public List<BoardDTO> GetSubjectBoard() throws Exception;//�������� �� �ҷ�����
	
	public List<BoardDTO> GetComBoard() throws Exception;//�İ� �� �ҷ�����
	public List<BoardDTO> GetManageBoard() throws Exception;//�濵 �� �ҷ�����
	public List<BoardDTO> GetInfoBoard() throws Exception;//���� �� �ҷ�����
	
	//***ȸ�����԰���***
	public void JoinDo(MemberDTO dto) throws Exception;//ȸ�������ϱ�
	
	//***�α��� ����***
	public MemberDTO LoginDo(MemberDTO dto) throws Exception;//�α����ϱ�
	
	//***���� ����***
	public MemberDTO numcheck(MemberDTO dto) throws Exception;//�α��� �й� üũ�ϱ�
	
	//***�� ����***
	public BoardDTO GetBoard(int boardnum) throws Exception;//���õ� �� ����
	public List<AnswerDTO> GetAnswer(int boardnum) throws Exception;//���õ� �ۿ� �� ����
	
	//***����Ʈ ����***
	public void BoardWrite(BoardDTO dto) throws Exception;//������ �۾���
}
