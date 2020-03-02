package kr.co.service;

import java.util.List;

import kr.co.dto.AnswerDTO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;

public interface BoardService {
		//***�Խ��ǰ���***
		public List<BoardDTO> GetBoardList(BoardDTO dto) throws Exception;
		
		//***ȸ�����԰���***
		public void JoinDo(MemberDTO dto) throws Exception;//ȸ�������ϱ�
		
		//***�α��� ����***
		public MemberDTO LoginDo(MemberDTO dto) throws Exception;//�α����ϱ�
		
		//***���� ����***
		public MemberDTO numcheck(MemberDTO dto) throws Exception;//�α��� �й� üũ�ϱ�
		
		//***�� ����***
		public BoardDTO GetBoard(int boardnum) throws Exception;//���õ� �� ����
		public List<AnswerDTO> GetAnswer(int boardnum) throws Exception;//���õ� �ۿ� �� ����
		public AnswerDTO GetAnswerOne(int num) throws Exception;//���õ� �ۿ� ��� ����
		public void ViewCount(BoardDTO dto) throws Exception;//��ȸ��
		public void AnswerDelete(AnswerDTO dto) throws Exception;//�亭����
		public void AnswerDelete2(int num) throws Exception;//�亭����
		
		public void AdopAnswer(int num) throws Exception;//�亯ä��
		public void AdopBoard(int boardnum) throws Exception;//��������
		
		//***����Ʈ ����***
		public void BoardWrite(BoardDTO dto) throws Exception;//������ �۾���
		public void BoardModify(BoardDTO dto) throws Exception;//������ �����ϱ�
		public void AnswerWrite(AnswerDTO dto) throws Exception;//������ �亯�ޱ�
		public void AnswerModify(AnswerDTO dto) throws Exception;//�亯 �����ϱ�
		
		public BoardDTO GetManyBoard() throws Exception;//���帹��
		public BoardDTO GetRandomBoard() throws Exception;//����
		public BoardDTO GetFinishBoard() throws Exception;//�亯��
		
		public MemberDTO GetMemberInfo(MemberDTO dto) throws Exception;//�������� ������
		
		public void BoardDelete(String boardnum) throws Exception;
}
