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

	@Override
	public List<BoardDTO> GetBoardList(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.GetBoardList(dto);
	}

	@Override
	public void BoardModify(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.BoardModify(dto);
	}

	@Override
	public void AnswerWrite(AnswerDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.AnswerWrite(dto);
	}

	@Override
	public MemberDTO GetMemberInfo(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.GetMemberInfo(dto);
	}

	@Override
	public void BoardDelete(String boardnum) throws Exception {
		// TODO Auto-generated method stub
		dao.BoardDelete(boardnum);
	}

	@Override
	public AnswerDTO GetAnswerOne(int num) throws Exception {
		// TODO Auto-generated method stub
		return dao.GetAnswerOne(num);
	}

	@Override
	public void AnswerModify(AnswerDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.AnswerModify(dto);
	}

	@Override
	public void ViewCount(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.ViewCount(dto);
	}

	@Override
	public void AnswerDelete(AnswerDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.AnswerDelete(dto);
	}

	@Override
	public void AnswerDelete2(int num) throws Exception {
		// TODO Auto-generated method stub
		dao.AnswerDelete2(num);
	}

	@Override
	public void AdopAnswer(int num) throws Exception {
		// TODO Auto-generated method stub
		dao.AdopAnswer(num);
	}

	@Override
	public void AdopBoard(int boardnum) throws Exception {
		// TODO Auto-generated method stub
		dao.AdopBoard(boardnum);
	}

	@Override
	public BoardDTO GetManyBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetManyBoard();
	}

	@Override
	public BoardDTO GetRandomBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetRandomBoard();
	}

	@Override
	public BoardDTO GetFinishBoard() throws Exception {
		// TODO Auto-generated method stub
		return dao.GetFinishBoard();
	}


}
