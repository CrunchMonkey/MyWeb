package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.BoardDAO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;

@Service
public class BoardServiceImp implements BoardService {

	@Inject
	BoardDAO dao;

	@Override
	public MemberDTO CheckLogin(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.CheckLogin(dto);
	}

	@Override
	public void Join(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.Join(dto);
	}

	@Override
	public List<BoardDTO> GetMajorBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetMajorBoard();
	}

	@Override
	public List<BoardDTO> GetStudyBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetStudyBoard();
	}

	@Override
	public List<BoardDTO> GetFreeBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetFreeBoard();
	}

	@Override
	public List<BoardDTO> GetSubjectBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetSubjectBoard();
	}

	@Override
	public MemberDTO CheckJoin(String id) throws Exception {
		// TODO Auto-generated method stub
		return dao.CheckJoin(id);
	}

}
