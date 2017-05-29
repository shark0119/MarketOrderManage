package test;

import org.junit.Test;

import com.shark.sql.GenerateSql;
import com.shark.sql.UserSql;
import com.shark.util.CommonUtil;

public class UtilTest {

	@Test
	public void testGetRoleName() {
		System.out.println(CommonUtil.getRoleName(1));
	}
	@Test
	public void testGetTotalCount (){
		String sql = "select count(1) from mk_user";
		GenerateSql gs = new UserSql(sql);
		int i = CommonUtil.getCount(gs);
		System.out.println(i);
	}

}
