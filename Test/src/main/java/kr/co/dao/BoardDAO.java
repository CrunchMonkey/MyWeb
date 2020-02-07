package kr.co.dao;

import java.util.List;

import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;
import kr.co.dto.StudentDTO;

public interface BoardDAO {
	public MemberDTO CheckLogin(MemberDTO dto) throws Exception;//아이디와 비밀번호가 일치하는가
	public void Join(MemberDTO dto) throws Exception;//회원가입하기
	public List<BoardDTO> GetMajorBoard() throws Exception;//학과주제 글 불러오기
	public List<BoardDTO> GetStudyBoard() throws Exception;//성적주제 글 불러오기
	public List<BoardDTO> GetFreeBoard() throws Exception;//자유주제 글 불러오기
	public List<BoardDTO> GetSubjectBoard() throws Exception;//과목주제 글 불러오기
	public MemberDTO CheckJoin(String id) throws Exception;//회원가입체크
}
