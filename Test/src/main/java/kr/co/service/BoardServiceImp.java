package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.BoardDAO;
import kr.co.dto.AnswerDTO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MemberDTO;

@Service
public class BoardServiceImp implements BoardService {

	@Inject
	BoardDAO dao;

	@Override
	public List<BoardDTO> GetComBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetComBoard();
	}
	@Override
	public List<BoardDTO> GetManageBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetManageBoard();
	}
	@Override
	public List<BoardDTO> GetInfoBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetInfoBoard();
	}
	@Override
	public List<BoardDTO> GetCareerBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetCareerBoard();
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
	public void JoinDo(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.JoinDo(dto);
	}

	@Override
	public MemberDTO LoginDo(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.LoginDo(dto);
	}

	@Override
	public MemberDTO numcheck(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.numcheck(dto);
	}
	@Override
	public BoardDTO GetBoard(int boardnum) throws Exception {
		// TODO Auto-generated method stub
		return dao.GetBoard(boardnum);
	}
	@Override
	public List<AnswerDTO> GetAnswer(int boardnum) throws Exception {
		// TODO Auto-generated method stub
		return dao.GetAnswer(boardnum);
	}
	@Override
	public void BoardWrite(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.BoardWrite(dto);
	}
	

	

}
