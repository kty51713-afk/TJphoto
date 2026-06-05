package mappers;

import org.apache.ibatis.annotations.Mapper;

import com.example.tjphoto.vo.MemberVO;

@Mapper
public interface MemberMapper {
    MemberVO findByLoginId(String loginId);
    void insertMember(MemberVO membervo);
}
