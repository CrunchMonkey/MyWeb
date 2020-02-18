package kr.co.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.dto.AnswerDTO;
import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;

@Repository
public class BoardDAOImp implements BoardDAO{
	
	@Inject
	private SqlSession Sql;
	
	private static String NameSpace = "Mapper";

	
	@Override
	public void JoinDo(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.insert(NameSpace + ".Join",dto);
	}

	@Override
	public MemberDTO LoginDo(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".LoginDO",dto);
		
	}

	@Override
	public MemberDTO numcheck(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".numcheck",dto);
	}
	@Override
	public BoardDTO GetBoard(int boardnum) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".GetBoard",boardnum);
	}
	@Override
	public List<AnswerDTO> GetAnswer(int boardnum) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectList(NameSpace + ".GetAnswer",boardnum);
	}
	@Override
	public void BoardWrite(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.insert(NameSpace + ".BoardWrite",dto);
	}

	@Override
	public List<BoardDTO> GetBoardList(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectList(NameSpace + ".GetBoardList",dto);
	}

	@Override
	public List<BoardDTO> GetSearchList(BoardDTO dtoard) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
