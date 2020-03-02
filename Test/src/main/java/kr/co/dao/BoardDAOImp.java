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

	@Override
	public void BoardModify(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.update(NameSpace + ".BoardModify",dto);
	}

	@Override
	public void AnswerWrite(AnswerDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.insert(NameSpace + ".AnswerWrite",dto);
	}

	@Override
	public MemberDTO GetMemberInfo(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
		return Sql.selectOne(NameSpace + ".GetMemberInfo",dto);
	}

	@Override
	public void BoardDelete(String boardnum) throws Exception {
		// TODO Auto-generated method stub
		Sql.delete(NameSpace + ".BoardDelete",boardnum);
		Sql.delete(NameSpace + ".AnswerDelete",boardnum);
	}

	@Override
	public AnswerDTO GetAnswerOne(int num) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".GetAnswerOne",num);
	}

	@Override
	public void AnswerModify(AnswerDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.update(NameSpace + ".AnswerModify",dto);
	}

	@Override
	public void ViewCount(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.update(NameSpace + ".ViewCount" , dto);
	}

	@Override
	public void AnswerDelete(AnswerDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.delete(NameSpace + ".AnswerDelete",dto);
	}

	@Override
	public void AnswerDelete2(int num) throws Exception {
		// TODO Auto-generated method stub
		Sql.delete(NameSpace + ".AnswerDelete2",num);
	}

	@Override
	public void AdopAnswer(int num) throws Exception {
		// TODO Auto-generated method stub
		Sql.update(NameSpace + ".AdopAnswer",num);
	}

	@Override
	public void AdopBoard(int boardnum) throws Exception {
		// TODO Auto-generated method stub
		Sql.update(NameSpace + ".AdopBoard",boardnum);
	}

	@Override
	public BoardDTO GetManyBoard() throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".GetManyBoard");
	}

	@Override
	public BoardDTO GetRandomBoard() throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".GetRandomBoard");
	}

	@Override
	public BoardDTO GetFinishBoard() throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".GetFinishBoard");
	}

	

	

}
