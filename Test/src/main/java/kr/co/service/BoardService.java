package kr.co.service;

import java.util.List;

import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;

public interface BoardService {
	public MemberDTO CheckLogin(MemberDTO dto) throws Exception;
	public void Join(MemberDTO dto) throws Exception;
	public List<BoardDTO> GetMajorBoard() throws Exception;//�а����� �� �ҷ�����
	public List<BoardDTO> GetStudyBoard() throws Exception;//�������� �� �ҷ�����
	public List<BoardDTO> GetFreeBoard() throws Exception;//�������� �� �ҷ�����
	public List<BoardDTO> GetSubjectBoard() throws Exception;//�������� �� �ҷ�����
	public MemberDTO CheckJoin(String id) throws Exception;//ȸ������üũ
}
