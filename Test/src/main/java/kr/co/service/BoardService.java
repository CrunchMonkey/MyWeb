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
		public AnswerDTO GetAnswerOne(int num) throws Exception;//선택된 글에 답들 보기
		public void ViewCount(BoardDTO dto) throws Exception;//조회수
		public void AnswerDelete(AnswerDTO dto) throws Exception;//답벼삭제
		public void AnswerDelete2(int num) throws Exception;//답벼삭제
		
		public void AdopAnswer(int num) throws Exception;//답변채택
		public void AdopBoard(int boardnum) throws Exception;//질문마감
		
		//***롸이트 관련***
		public void BoardWrite(BoardDTO dto) throws Exception;//질문에 글쓰기
		public void BoardModify(BoardDTO dto) throws Exception;//질문에 수정하기
		public void AnswerWrite(AnswerDTO dto) throws Exception;//질문에 답변달기
		public void AnswerModify(AnswerDTO dto) throws Exception;//답변 수정하기
		
		public BoardDTO GetManyBoard() throws Exception;//가장많은
		public BoardDTO GetRandomBoard() throws Exception;//랜덤
		public BoardDTO GetFinishBoard() throws Exception;//답변끝
		
		public MemberDTO GetMemberInfo(MemberDTO dto) throws Exception;//유저정보 얻어오기
		
		public void BoardDelete(String boardnum) throws Exception;
}
