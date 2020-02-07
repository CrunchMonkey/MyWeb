package kr.co.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.dto.BoardDTO;
import kr.co.dto.MajorDTO;
import kr.co.dto.MemberDTO;
import kr.co.dto.StudentDTO;

@Repository
public class BoardDAOImp implements BoardDAO{
	
	@Inject
	private SqlSession Sql;
	
	private static String NameSpace = "Mapper";

	@Override
	public MemberDTO CheckLogin(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".CheckLogin",dto);
	}

	@Override
	public void Join(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Sql.insert(NameSpace + ".Join",dto);
	}

	@Override
	public List<BoardDTO> GetMajorBoard() throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectList(NameSpace + ".GetMajorBoard");
	}

	@Override
	public List<BoardDTO> GetStudyBoard() throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectList(NameSpace + ".GetStudyBoard");
	}

	@Override
	public List<BoardDTO> GetFreeBoard() throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectList(NameSpace + ".GetFreeBoard");
	}

	@Override
	public List<BoardDTO> GetSubjectBoard() throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectList(NameSpace + ".GetSubjectBoard");
	}

	@Override
	public MemberDTO CheckJoin(String id) throws Exception {
		// TODO Auto-generated method stub
		return Sql.selectOne(NameSpace + ".CheckJoin",id);
	}



}
