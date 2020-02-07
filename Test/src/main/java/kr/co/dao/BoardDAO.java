package kr.co.dao;

import java.util.List;

import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;
import kr.co.dto.StudentDTO;

public interface BoardDAO {
	public MemberDTO CheckLogin(MemberDTO dto) throws Exception;//���̵�� ��й�ȣ�� ��ġ�ϴ°�
	public void Join(MemberDTO dto) throws Exception;//ȸ�������ϱ�
	public List<BoardDTO> GetMajorBoard() throws Exception;//�а����� �� �ҷ�����
	public List<BoardDTO> GetStudyBoard() throws Exception;//�������� �� �ҷ�����
	public List<BoardDTO> GetFreeBoard() throws Exception;//�������� �� �ҷ�����
	public List<BoardDTO> GetSubjectBoard() throws Exception;//�������� �� �ҷ�����
	public MemberDTO CheckJoin(String id) throws Exception;//ȸ������üũ
}
