package jp.co.internous.kingdom.model.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.kingdom.model.domain.MstUser;

@Mapper
public interface MstUserMapper {
	
	//MstUserから findByUserNameAndPasswordでuserName、password取得
	@Select("SELECT * FROM mst_user WHERE user_name = #{userName} AND password = #{password}")
	MstUser findByUserNameAndPassword(
		@Param("userName") String userName,
		@Param("password") String password);
	
		
	//重複化確認で使用
	@Select("SELECT count(id) FROM mst_user WHERE user_name = #{userName}")
	int findCountByUserName(String userName);
		
	
	@Insert ("INSERT INTO mst_user" 
		+ " ( family_name, first_name, family_name_kana, first_name_kana, gender, user_name, password)" 
		+"VALUES" 
		+ "( #{familyName}, #{firstName}, #{familyNameKana}, #{firstNameKana}, #{gender}, #{userName}, #{password})" )
	 @Options(useGeneratedKeys = true, keyProperty = "id")//AutoIncrementのidつかう
	int insert(MstUser user);
	
	
	//再設定用
	@Update("UPDATE mst_user SET password = #{password}, updated_at = now() WHERE user_name = #{userName}")
	int updatePassword(
		@Param("userName") String userName,
		@Param("password") String password);
	
}
