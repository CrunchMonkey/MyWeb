package kr.co.dao;

import java.util.List;

import kr.co.dto.AnswerDTO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;

public interface BoardDAO {
	//***게시판관련***
	public List<BoardDTO> GetCareerBoard() throws Exception;//진로주제 글 불러오기
	public List<BoardDTO> GetStudyBoard() throws Exception;//공부주제 글 불러오기
	public List<BoardDTO> GetFreeBoard() throws Exception;//자유주제 글 불러오기
	public List<BoardDTO> GetSubjectBoard() throws Exception;//수업주제 글 불러오기
	
	public List<BoardDTO> GetComBoard() throws Exception;//컴공 글 불러오기
	public List<BoardDTO> GetManageBoard() throws Exception;//경영 글 불러오기
	public List<BoardDTO> GetInfoBoard() throws Exception;//정통 글 불러오기
	
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
