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
		
		//***����Ʈ ����***
		public void BoardWrite(BoardDTO dto) throws Exception;//������ �۾���
}
