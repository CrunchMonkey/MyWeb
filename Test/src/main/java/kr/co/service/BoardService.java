package kr.co.service;

import java.util.List;

import kr.co.dto.AnswerDTO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;

public interface BoardService {
		//***게시판관련***
		public List<BoardDTO> GetBoardList(BoardDTO dto) throws Exception;
		
		//***회원가입관련***
		public void JoinDo(MemberDTO dto) throws Exception;//회원가입하기
		
		//***로그인 관련***
		public MemberDTO LoginDo(MemberDTO dto) throws Exception;//로그인하기
		
		//***조인 관련***
		public MemberDTO numcheck(MemberDTO dto) throws Exception;//로그인 학번 체크하기
		
		//***뷰 관련***
		public BoardDTO GetBoard(int boardnum) throws Exception;//선택된 글 보기
		public List<AnswerDTO> GetAnswer(int boardnum) throws Exception;//선택된 글에 답 보기
		
		//***롸이트 관련***
		public void BoardWrite(BoardDTO dto) throws Exception;//질문에 글쓰기
}
